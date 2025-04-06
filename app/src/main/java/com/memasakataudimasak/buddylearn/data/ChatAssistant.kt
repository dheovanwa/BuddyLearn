package com.memasakataudimasak.buddylearn.data

import com.google.gson.Gson
import com.google.gson.JsonParser
import okhttp3.Call
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.Response
import java.io.IOException

class ChatAssistant {
    fun chatForQuestions(requestBody: String = "", language: String = "indonesia", subject: String = "", imageLink: String = "") {
        val inputToken = """
            You are an expert at "$subject" (if empty than general subject), you are tasked to output in $language language.
            There may be an image in link: $imageLink
            If image link is empty than there is no image.
            
            You must answer the following question in concise manner:
            $requestBody
            
            if the question is empty, then output that you cannot process empty questions in aforementioned language.
        """.trimIndent()

        val client = OkHttpClient()

        val mediaType = "application/json".toMediaType()
        val jsonBody = Gson().toJson(
            mapOf(
                "model" to "qwen/qwen2.5-vl-72b-instruct:free",
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
//    val chatbot = ChatBot()
//    chatbot.chatForQuestions(requestBody = "Apa yang terjadi pada tahun 1945?")
//}