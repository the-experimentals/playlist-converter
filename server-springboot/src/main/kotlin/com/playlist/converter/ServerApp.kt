package com.playlist.converter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
open class ServerApp

fun main(args: Array<String>) {
    runApplication<ServerApp>(*args)
}

@RestController
class DummyController{

    @GetMapping
    fun test():String{
        return "test"
    }
}