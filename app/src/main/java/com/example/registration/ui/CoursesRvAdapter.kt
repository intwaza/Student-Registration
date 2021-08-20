package com.example.registration

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.registration.models.Course


class CoursesRvAdapter(var courseList: List<Course>): RecyclerView.Adapter<CoursesViewHolder>() {
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
}