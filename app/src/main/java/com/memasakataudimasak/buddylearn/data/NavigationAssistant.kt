package com.memasakataudimasak.buddylearn.data

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class NavigationAssistant {
    fun navigateBySpeech(currentLocation: String, requestBody: String = "", ) {
        val listOfMapel = listOfMapel
        val kelas = 1
        val inputToken = """
            Kamu adalah model bahasa yang bertugas untuk mengklasifikasikan maksud navigasi dari input speech-to-text pengguna pada aplikasi Android edukasi. Aplikasi ini menyediakan materi pembelajaran untuk tingkat SD, SMP, dan SMA dengan fokus utama pada navigasi bagi pengguna tuna netra. Tugasmu adalah membaca input percakapan pengguna, mengidentifikasi maksud navigasi, dan mengembalikan satu atau lebih kata perintah yang spesifik untuk mengarahkan navigasi dalam aplikasi.
            Hasil klasifikasi yang kamu berikan harus selalu konsisten dan diambil dari daftar kata perintah yang telah ditentukan sebelumnya (predetermined list). Contoh daftar kata perintah meliputi (namun tidak terbatas pada):
    
            go-to-home-page
            go-to-list-of-mapel-page (halaman yang berisi mata pelajaran)
            go-to-profile-page
            go-to-leaderboard-page
            go-to-settings-page
            go-to-[mapel]-[kelas]-information-page
            go-to-[mapel]-[kelas]-page
            go-to-[mapel]-[kelas]-bab-[bab_berapa]-page
            go-to-[mapel]-[kelas]-bab-[bab_berapa]-subbab-[subbab_berapa]-page
            change-name (profile-page)
            change-date-of-birth (profile-page)
            change-class (profile-page)
            view-stats (profile-page)
            view-completed-module (profile-page)
            continue
            back
            chatbot ([mapel]-[kelas]-bab-[bab_berapa]-subbab-[subbab_berapa]-page) [jangan dialihkan ke sini jika berada pada page lain]
            ? [jika perintah yang dikirim user tidak dapat diklasifikasi]
                
            perintah khusus:
            1. Untuk perintah "chatbot", jika saat ini user tidak sedang berada di page yang telah ditentukan di list perintah, yaitu page subbab, maka jangan alihkan ke page tersebut, tetapi output akan berupa "?" disertai alasan, yaitu user harus berada di halaman yang pembelajaran mata pelajaran tersebut dengan format yang sesuai pada note-3
            2. Jika perintah untuk page khusus, tetapi saat ini user sedang berada di page yang berbeda, maka output akan menjadi go-to-[lokasi]-page diikuti oleh perintah yang diinginkan
            3. perintah "chatbot" haruslah menjadi perintah terakhir dari list perintah yang di-output dan output nya hanya berupa "chatbot"

            User juga dapat berada di salah satu halaman berikut:
            home-page atau list-of-mapel-page
            leaderboard-page
            profile-page
            settings-page
            about-mapel-page
            learning-page
            
            List mata pelajaran:
            $listOfMapel
            
            kelas user:
            $kelas
            
            Contoh:
            Input: "Saya ingin kembali ke menu utama. (saat ini user berada di settings-page)"
            Output yang diharapkan: "go-to-home"

            ---
            Output apa yang harus Anda hasilkan dari perintah user di bawah ini:
            "$requestBody"
            dengan lokasi user saat ini berada di:
            $currentLocation
            
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

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Request failed: ${e.message}")
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

                    println("$content")
                } else {
                    println("Request failed with code: ${response.code}")
                }
            }
        })
    }
}

//fun main() {
//    val navigation = Navigation()
//    navigation.navigateBySpeech("profile-page", "saya ingin bertanya lebih lanjut terkait materi bahasa Indonesia")
//}
