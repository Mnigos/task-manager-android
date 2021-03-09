package com.example.task_manager_android

import okhttp3.* // ktlint-disable no-wildcard-imports
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

class AuthFetch(private val path: String) {

    private val url = "http://localhost:3000"
    private val request = Request.Builder().url(url + path).build()

    @Throws(IOException::class)
    fun login(name: String, pass: String): String {
        val body = """{
            "name": $name,
            "pass": $pass
        }""".trimMargin()

        val request = Request.Builder().url(url + path).post(body.toRequestBody()).build()
        OkHttpClient().newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Err: $response")

            return response.body!!.string()
        }
    }
}
