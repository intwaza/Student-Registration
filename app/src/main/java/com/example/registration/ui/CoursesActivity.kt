package com.example.registration.ui


import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.Constants
import com.example.registration.CoursesRvAdapter
import com.example.registration.CoursesViewHolder
import com.example.registration.R
import com.example.registration.databinding.ActivityCourseBinding
import com.example.registration.models.Course
import com.example.registration.viewModel.CourseViewModel

class CoursesActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseBinding
    val courseViewModel: CourseViewModel by viewModels()
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityCourseBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs= getSharedPreferences(Constants.REGISTRATION_PREFS, Context.MODE_PRIVATE)
        var accessToken = sharedPrefs.getString(Constants.ACCESS_TOKEN, Constants.EMPTY_STRING)!!
        if (accessToken.isNotEmpty())
            courseViewModel.getCourses(accessToken)
    }


    override fun onResume() {
        super.onResume()
        courseViewModel.courseLiveData.observe(this,{courses->
        var coursesList = listOf(
            Course("AND 101","Android","2","Native android development", "John Owuor"),
            Course("PY 101","Python","5","Backend development","James Mwai"),
            Course("JS 101","JavaScript","8","Frontend development","Purity Maina"),
            Course("IOT 101","IOT","9","IOT for connectivity of network","Baren"),
            Course("UX 101","UX research","10","MAke research before anything","Joy"),
            )
                    var coursesAdapter = CoursesRvAdapter(coursesList)
                    binding.rvCourses.layoutManager = LinearLayoutManager(baseContext)
                    binding.rvCourses.adapter = coursesAdapter


            Toast.makeText(this, "fetched ${courses.size} courses", Toast.LENGTH_LONG).show()
        })
        courseViewModel.errorLiveData.observe(this,{ error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        })
    }

}