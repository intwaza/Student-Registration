package com.example.registration.repository

import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)

    suspend fun registerStudent(registrationRequest: RegistrationRequest): Response<RegistrationResponse> =
        withContext(Dispatchers.IO){
            var response= retrofit.registerStudent(registrationRequest)
            return@withContext response
        }

    suspend fun loginStudent(loginRequest: LoginRequest): Response<LoginResponse> =
        withContext(Dispatchers.IO){
            var response= retrofit.loginStudent(loginRequest)
            return@withContext response
        }
}