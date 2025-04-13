package com.memasakataudimasak.buddylearn.data

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.memasakataudimasak.buddylearn.ViewModel
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object NavigationAssistant {
    fun navigateBySpeech(
        viewModel: ViewModel,
        currentScreen: Screen,
        stringBody: String = "",
        ) {
        val (grade, isEnglish) = viewModel.getGradeAndLanguage()
        val inputToken = """
            Kamu adalah model bahasa yang bertugas untuk mengklasifikasikan maksud navigasi dari input speech-to-text pengguna pada aplikasi Android edukasi. Aplikasi ini menyediakan materi pembelajaran untuk tingkat SD, SMP, dan SMA dengan fokus utama pada navigasi bagi pengguna tuna netra. Tugasmu adalah membaca input percakapan pengguna, mengidentifikasi maksud navigasi, dan mengembalikan satu atau lebih kata perintah yang spesifik untuk mengarahkan navigasi dalam aplikasi.
            Hasil klasifikasi yang kamu berikan harus selalu konsisten dan diambil dari daftar kata perintah yang telah ditentukan sebelumnya (predetermined list). Contoh daftar kata perintah meliputi (namun tidak terbatas pada):
    
            go-to-home-screen
            go-to-list-of-mapel-screen (halaman yang berisi mata pelajaran)
            go-to-profile-screen
            go-to-leaderboard-screen
            go-to-settings-screen
            go-to-[mapel]-[kelas]-information-screen
            go-to-[mapel]-[kelas]-screen
            go-to-[mapel]-[kelas]-bab-[bab_berapa]-screen
            go-to-[mapel]-[kelas]-bab-[bab_berapa]-subbab-[subbab_berapa]-screen
            change-name (profile-screen)
            change-date-of-birth (profile-screen)
            change-grade (settings-screen) [angka tanpa prefix seperti "th/st/nd/ etc."]
            change-language (settings-screen) [isEnglish = true / false]
            view-stats (profile-screen)
            view-completed-module (profile-screen)
            continue
            back
            chatbot ([mapel]-[kelas]-bab-[bab_berapa]-subbab-[subbab_berapa]-screen) [jangan dialihkan ke sini jika berada pada screen lain]
            ? [jika perintah yang dikirim user tidak dapat diklasifikasi]
                
            perintah khusus:
            1. Perintah yang dilabeli settings screen dapat diubah dari mana saja dan output yang dikeluarkan berupa "[command],[value]" dan tidak perlu menuju settings screen lagi

            User juga dapat berada di salah satu halaman berikut:
            home-screen atau list-of-mapel-screen
            leaderboard-screen
            profile-screen
            settings-screen
            about-mapel-screen
            learning-screen
            
            List mata pelajaran:
            $listOfMapel
            
            kelas user:
            $grade
            
            Contoh:
            Input: "Saya ingin kembali ke menu utama. (saat ini user berada di settings-screen)"
            Output yang diharapkan: "go-to-home"
            
            Bahasa yang harus dikeluarkan:
            ${if (isEnglish) "English" else "Indonesia"}

            ---
            Output apa yang harus Anda hasilkan dari perintah user di bawah ini:
            "$stringBody"
            dengan lokasi user saat ini berada di:
            ${currentScreen.title}
            
            Template Output:
            [string dari predetermined list]
            
            Note:
            1. Tidak perlu memberikan "Response:" dan "Rasional:" atau alasan lainnya, hanya perlu string dari template output saja. Dan juga, tidak perlu menyertakan "Response: [string]", hanya "[string]" dari template output saja
            2. Output dapat berisi lebih dari satu perintah dengan separator masing-masing perintah berupa comma (,) tanpa spasi setelah koma
            3. Jika output adalah ?, maka sertakan alasan kepada user mengapa tidak dapat diklasifikasikan dengan pemisahan menggunakan koma tanpa spasi setelah koma. Jangan gunakan koma dalam menyertakan konten alasan. Nada pembicaraan mensimulasikan seperti berbicara langsung ke user
            
            ---
            Dictionary:
            mapel = Mata Pelajaran
            mapel-[angka] = mata pelajaran untuk kelas [angka]
            
        """.trimIndent()

        val client = OkHttpClient()

        val mediaType = "application/json".toMediaType()
        val jsonBody = Gson().toJson(
            mapOf(
                "model" to "nvidia/llama-3.1-nemotron-70b-instruct:free",
                "messages" to listOf(
                    mapOf(
                        "role" to "user",
                        "content" to inputToken
                    )
                )
            )
        )

        val requestBody = jsonBody.toRequestBody(mediaType)

        val request = Request.Builder()
            .url("https://openrouter.ai/api/v1/chat/completions")
            .post(requestBody)
            .addHeader("Authorization", "Bearer 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")
            .addHeader("Content-Type", "application/json")
            .build()

        var result = ""

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                result = ""
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val responseBody = response.body?.string()
                    val jsonObject = JsonParser.parseString(responseBody).asJsonObject
                    val content = jsonObject
                        .getAsJsonArray("choices")[0]
                        .asJsonObject
                        .getAsJsonObject("message")
                        .get("content")
                        .asString
                    result = content
                    Log.d("voice command", result)
                    viewModel.setCommandProcessed(result)
                } else {
                    result = ""

                }
            }
        })
    }
}

//fun main() {
//    val navigation = Navigation()
//    navigation.navigateBySpeech("profile-screen", "saya ingin bertanya lebih lanjut terkait materi bahasa Indonesia")
//}
