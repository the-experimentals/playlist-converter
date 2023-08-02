package com.playlist.converter

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class ConverterApplication

fun main(args: Array<String>) {
    runApplication<ConverterApplication>(*args)
}