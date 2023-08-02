package com.playlist.converter.config

import com.playlist.converter.config.properties.ConverterProperties
import okhttp3.OkHttpClient
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ConverterProperties::class)
open class ConverterConfigs {

    @Bean
    open fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient()
    }
}