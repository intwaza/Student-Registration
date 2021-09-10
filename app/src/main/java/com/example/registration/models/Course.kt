package com.example.registration.models

import com.google.gson.annotations.SerializedName

data class Course(
    @SerializedName("course_id") var courseId: String,
    @SerializedName("course_name")var courseName:String,
    @SerializedName("course_code")var courseCode:String,
    var description: String,
    var instructor:String,
)
