package com.example.registration.repository

import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.Course
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class CourseRepository {
    var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun  fetchCourses(accesToken:String): Response<List<Course>> =
        withContext(Dispatchers.IO){
            return@withContext retrofit.fetchCourses(accesToken)
        }
}