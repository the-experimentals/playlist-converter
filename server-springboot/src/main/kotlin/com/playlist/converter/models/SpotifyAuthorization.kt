package com.playlist.converter.models

import com.fasterxml.jackson.annotation.JsonProperty
import org.jetbrains.annotations.NotNull
import org.springframework.validation.annotation.Validated

@Validated
data class SpotifyAuthorization(

    @NotNull
    @JsonProperty("access_token")
    val accessToken: String,
    @NotNull
    @JsonProperty("token_type")
    val tokenType: String = "Bearer",

    @JsonProperty("expires_in")
    val expiresIn: Int,
)
