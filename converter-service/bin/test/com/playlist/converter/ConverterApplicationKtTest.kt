package com.playlist.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class ConverterApplicationKtTest{

    @Test
    fun contextLoad(applicationContext: ApplicationContext){
        assertNotNull(applicationContext)
    }
}