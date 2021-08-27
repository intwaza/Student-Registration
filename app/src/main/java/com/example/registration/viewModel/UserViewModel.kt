package com.example.registration.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import com.example.registration.models.RegistrationRequest
import com.example.registration.models.RegistrationResponse
import com.example.registration.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel: ViewModel() {
    val userRepository= UserRepository()
    val registrationLiveData = MutableLiveData<RegistrationResponse>()
    val loginLiveData= MutableLiveData<LoginResponse>()
    var errorLiveData= MutableLiveData<String>()

    fun registerStudent(registrationRequest: RegistrationRequest){
        viewModelScope.launch {
            var response = userRepository.registerStudent(registrationRequest)
            if (response.isSuccessful){
                registrationLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
    fun loginStudent(loginRequest: LoginRequest){
        viewModelScope.launch {
            var response= userRepository.loginStudent(loginRequest)
            if (response.isSuccessful){
                loginLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue((response.errorBody()?.string()))
            }
        }
    }
}