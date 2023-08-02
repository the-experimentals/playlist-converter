package com.playlist.converter.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "playlist-converter")
data class ConverterProperties(
    var spotify: Spotify = Spotify()
) {
    data class Spotify(
        var clientId: String = "",
        var clientSecret: String = ""
    )
}