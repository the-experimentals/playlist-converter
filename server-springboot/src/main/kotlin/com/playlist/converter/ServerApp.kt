package com.playlist.converter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ServerApp

fun main(args: Array<String>) {
    runApplication<ServerApp>(*args)
}