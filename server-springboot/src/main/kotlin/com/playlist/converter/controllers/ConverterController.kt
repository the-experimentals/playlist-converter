package com.playlist.converter.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.playlist.converter.config.properties.PlaylistConverterProperties
import com.playlist.converter.models.SpotifyAuthorization
import okhttp3.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController("")
class ConverterController(
    val properties:PlaylistConverterProperties,
    val objectMapper: ObjectMapper,
    val okHttpClient: OkHttpClient) {

    @PostMapping()
    fun spotifyLogin(): ResponseEntity<SpotifyAuthorization>{

        val formBody: FormBody = FormBody.Builder()
                .add("grant_type","client_credentials")
                .add("client_id",properties.spotify.clientId)
                .add("client_secret",properties.spotify.clientSecret)
                .build()

        val request: Request = Request.Builder().post(formBody).header("Content-Type","application/x-www-form-urlencoded")
            .url("https://accounts.spotify.com/api/token")
            .build()

        val call: Call = okHttpClient.newCall(request)
        val response: Response = call.execute()
        val authorization =  objectMapper.readValue(response.body?.string(), SpotifyAuthorization::class.java)

        return ResponseEntity.accepted().body(authorization)
    }

    @GetMapping()
    fun getSpotifyPlaylist(){
        
    }
}