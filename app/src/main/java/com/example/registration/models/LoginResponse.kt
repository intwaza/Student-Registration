package com.example.registration.models

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    var message: String,
    @SerializedName("access_token") var accesToken: String,
    @SerializedName("student_id") var studentId:String
)
