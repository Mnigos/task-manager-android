package com.example.task_manager_android

import android.util.Log
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class FetchApi {
    private val apiUrl: String = ""

    private fun getUrlBytes(urlSpec: String): ByteArray {
        val url = URL(urlSpec)
        val connection: HttpsURLConnection = url.openConnection() as HttpsURLConnection

        try {
            val out = ByteArrayOutputStream()
            val input: InputStream = connection.inputStream

            if (connection.responseCode != HttpsURLConnection.HTTP_OK)
                throw IOException(connection.responseMessage)

            var bytesRead: Int
            val buffer = ByteArray(1024)

            do {
                bytesRead = input.read(buffer)

                out.write(buffer, 0, bytesRead)
            } while (input.read(buffer) > 0)
            out.close()

            return out.toByteArray()
        } catch (e: IOException) {
            Log.e("Http connection error", e.message.toString())
            return ByteArray(0)
        } finally {
            connection.disconnect()
        }
    }

    private fun getUrlString(urlSpec: String): String {
        return String(getUrlBytes(urlSpec))
    }
}
