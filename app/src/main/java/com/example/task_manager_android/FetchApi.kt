package com.example.task_manager_android

import android.net.Uri
import android.util.Log
import org.json.JSONException
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
            return getHttpsConnectionBytes(connection)
        } catch (ex: IOException) {
            Log.e("Http connection error", ex.message.toString())
            return ByteArray(0)
        } finally {
            connection.disconnect()
        }
    }

    @Throws(IOException::class)
    private fun getHttpsConnectionBytes(connection: HttpsURLConnection): ByteArray {
        try {
            val input: InputStream = connection.inputStream
            if (connection.responseCode != HttpsURLConnection.HTTP_OK)
                throw IOException(connection.responseMessage)
            return getOutputStreamByteArray(input)
        } catch (ex: IOException) {
            throw ex
        }
    }

    @Throws(IOException::class)
    private fun getOutputStreamByteArray(input: InputStream): ByteArray {
        val out = ByteArrayOutputStream()
        var bytesRead: Int
        val buffer = ByteArray(1024)
        try {
            do {
                bytesRead = input.read(buffer)
                out.write(buffer, 0, bytesRead)
            } while (input.read(buffer) > 0)
            out.close()
            return out.toByteArray()
        } catch (ex: IOException) {
            throw ex
        }
    }

    private fun getUrlString(uriSpec: String): String {
        return String(getUrlBytes(uriSpec))
    }

    fun getJSONString(): String {
        var jsonString = "Something went wrong :/"

        try {
            val uri: String = buildUri(apiUrl)
            jsonString = getUrlString(uri)
        } catch (ex: JSONException) {
            Log.e("Json Error", ex.message.toString())
        }

        return jsonString
    }

    @Throws(JSONException::class)
    private fun buildUri(url: String): String {
        try {
            return Uri.parse(url)
                .buildUpon()
                .appendQueryParameter("format", "json")
                .appendQueryParameter("nojsoncallback", "1")
                .appendQueryParameter("extras", "urls_s")
                .build()
                .toString()
        } catch (ex: JSONException) {
            throw ex;
        }
    }
}
