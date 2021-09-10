package com.example.registration

import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.models.Course
import com.example.registration.models.EnrolmentRequest
import com.example.registration.viewModel.EnrolmentViewHolder


class CoursesRvAdapter(var courseList: List<Course>): RecyclerView.Adapter<CoursesViewHolder>() {
    private lateinit var enrolViewModel: EnrolmentViewHolder
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        var  itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.course_list_item,parent,false)
        return CoursesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
       var currentCourse = courseList.get(position)
        holder.tvCourseName.text= currentCourse.courseName
        holder.tvDescription.text= currentCourse.description
        holder.tvCourseCode.text= currentCourse.courseCode
        holder.tvInstructor.text= currentCourse.instructor
        holder.btnEnrolr.setOnClickListener {
            sharedPreferences = sharedPreferences
            var studentId = sharedPreferences.edit()
            var courseId = sharedPreferences.edit()
            var enrolRequest = EnrolmentRequest(
                student_id = studentId.toString(),
                course_id = courseId.toString()
            )
            enrolViewModel.enrol("ACCESS_TOKEN")
        }
    }

override fun getItemCount(): Int {
       return courseList.size
    }
}

class CoursesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    var tvCourseName= itemView.findViewById<TextView>(R.id.tvCourseName)
    var tvDescription= itemView.findViewById<TextView>(R.id.tvDescription)
    var tvCourseCode= itemView.findViewById<TextView>(R.id.tvCourseCode)
    var tvInstructor= itemView.findViewById<TextView>(R.id.tvInstructor)
    var btnEnrolr= itemView.findViewById<Button>(R.id.btnEnrol)
}