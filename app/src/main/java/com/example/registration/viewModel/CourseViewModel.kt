package com.example.registration.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registration.models.Course
import com.example.registration.repository.CourseRepository
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel(){
    var courseRespository= CourseRepository()
    var courseLiveData= MutableLiveData<List<Course>>()
    var errorLiveData= MutableLiveData<String>()

    fun getCourses(accessToken: String){
        viewModelScope.launch {
            var response= courseRespository.fetchCourses(accessToken)
            if (response.isSuccessful){
                courseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }
}