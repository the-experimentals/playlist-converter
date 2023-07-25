package com.playlist.converter.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
@ConfigurationProperties(prefix = "playlist-converter.spotify")
data class PlaylistConverterProperties(

    var clientId:String ="",
    var clientSecret: String = ""
)
