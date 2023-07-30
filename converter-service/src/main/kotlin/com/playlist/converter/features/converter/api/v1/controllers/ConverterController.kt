package com.playlist.converter.features.converter.api.v1.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController()
@RequestMapping("/api/v1")
class ConverterController {

    @GetMapping("authorize")
    fun getAuthorizationToken(): String{
        return "Working"
    }
}