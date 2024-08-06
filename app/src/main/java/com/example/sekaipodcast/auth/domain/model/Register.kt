package com.example.sekaipodcast.auth.domain.model

import com.google.gson.annotations.SerializedName

data class Register(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String,
    )
