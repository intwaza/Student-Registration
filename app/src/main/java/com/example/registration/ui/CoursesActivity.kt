package com.example.registration.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.CoursesRvAdapter
import com.example.registration.R
import com.example.registration.models.Course

class CoursesActivity : AppCompatActivity() {
    lateinit var rvCourses: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_course)
        displayCourses()
    }
    fun displayCourses(){
        var coursesList = listOf(
            Course("Android","AND 101","Native android development","John Owur"),
            Course("Python","PY 101","Backend development","James Mwai"),
            Course("JavaScript","JS 101","Frontend development","Purity Maina"),
            Course("IOT","IOT 101","IOT for connectivity of network","Baren"),
            Course("UX research","UX 101","MAke research before anything","Joy")
        )
        rvCourses = findViewById(R.id.rvCourses)
        var coursesAdapter = CoursesRvAdapter(coursesList)
        rvCourses.layoutManager = LinearLayoutManager(baseContext)
        rvCourses.adapter = coursesAdapter
    }

}