package io.flaterlab.tests.data.model

import com.google.gson.annotations.SerializedName

data class UserDetail(
    @SerializedName("username") val username : String,
    @SerializedName("email") val email : String
)