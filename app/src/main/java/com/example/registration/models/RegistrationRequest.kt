package com.example.registration.models

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(
    var name: String,
    @SerializedName("phone_number")var PhoneNumber:String,
    var email: String,
    @SerializedName("date_of_birth")var dateOfBirth: String,
    var nationality: String,
    var password: String
)
