package com.playlist.converter

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext

@SpringBootTest
class ServerAppKtTest {
    @Test
    fun contextLoad(context: ApplicationContext){
        assertNotNull(context)
    }
}

