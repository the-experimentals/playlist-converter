package com.playlist.converter.features.converter.api.v1.controllers

import com.playlist.converter.config.properties.ConverterProperties
import okhttp3.OkHttpClient
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
        return "Working"
    }
}