package com.playlist.converter.features.converter.api.v1.controllers

import com.playlist.converter.config.properties.ConverterProperties
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController()
@RequestMapping("/api/v1")
class ConverterController(
    val okHttpClient: OkHttpClient,
    val properties: ConverterProperties) {

    @GetMapping("authorize")
    fun getAuthorizationToken(): String{

        val url = "https://accounts.spotify.com/authorize".toHttpUrl().newBuilder().apply {
            addQueryParameter("response_type","token")
            addQueryParameter("scope", "user-read-private")
            addQueryParameter("redirect_uri", "http://localhost:3000")
            addQueryParameter("client_id", "03936c6dc3ac43d5beffc48dde7f0636")
        }.build()

        val request = Request.Builder()
            .url(url)
            .build()

        val call = okHttpClient.newCall(request)
        val response: Response = call.execute()

        return response.body?.string() ?: "error"
    }
}