package com.playlist.converter.controllers

import com.playlist.converter.config.properties.PlaylistConverterProperties
import okhttp3.*
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController("")
class ConverterController(val properties:PlaylistConverterProperties) {

    private var client: OkHttpClient = OkHttpClient()

    @PostMapping()
    fun spotifyLogin(){

        val formBody: FormBody = FormBody.Builder()
                .add("grant_type","client_credentials")
                .add("client_id","")
                .add("client_secret","")
                .build()

        val request: Request = Request.Builder().post(formBody).header("Content-Type","application/x-www-form-urlencoded")
            .url("https://accounts.spotify.com/api/token")
            .build()

        val call: Call = client.newCall(request)
        val response: Response = call.execute()
        println(response.body?.string())
    }
}