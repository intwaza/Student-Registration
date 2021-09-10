package com.example.registration.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.models.EnrolmentResponse
import com.example.registration.repository.CourseRepository
import kotlinx.coroutines.launch

class EnrolmentViewHolder:ViewModel() {
        var enrolmentLiveData = MutableLiveData<EnrolmentResponse>()
        var errorLiveData = MutableLiveData<String>()
        var coursesRepository = CourseRepository()

        fun enrol(accessToken:String){
                viewModelScope.launch {
                        var response = coursesRepository.enrol(accessToken)
                        if (response.isSuccessful){
                                enrolmentLiveData.postValue(response.body())
                        }
                        else{
                                errorLiveData.postValue(response.errorBody()?.string())
                        }
                }
        }

}