package com.example.registration.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration.Constants
import com.example.registration.databinding.ActivityLoginBinding
import com.example.registration.models.LoginRequest
import com.example.registration.models.LoginResponse
import com.example.registration.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs: SharedPreferences
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sharedPrefs= getSharedPreferences(Constants.REGISTRATION_PREFS, Context.MODE_PRIVATE)
        clickLogin()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, { lrgResponse->
            var accessToken= lrgResponse.accesToken
            accessToken="Bearer $accessToken"
            var editor= sharedPrefs.edit()
            editor.putString(Constants.ACCESS_TOKEN, accessToken)
            editor.putString(Constants.STUDENT_ID,  lrgResponse.studentId)
            editor.apply()
            Toast.makeText(baseContext,lrgResponse.message,Toast.LENGTH_LONG).show()
            startActivity(Intent(baseContext,CoursesActivity::class.java))


            binding.pbLogin.visibility= View.GONE

            })
        userViewModel.errorLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            binding.pbLogin.visibility= View.GONE
        })
    }

    fun clickLogin(){
        var error= false
        binding.btnLogin2.setOnClickListener {
            var email= binding.tvEmail.text.toString()
            if(email.isEmpty() || email.isBlank()){
                binding.tilEmail.error="This field required"
                error= true
            }
            var password= binding.tvPassword.text.toString()
            if(password.isEmpty()){
                binding.tvPassword.error= "This field required"
                error= true
            }
            if (!error){
                binding.pbLogin.visibility= View.VISIBLE
                var lrgRequest= LoginRequest(
                    email= email,
                    password= password
                )
                userViewModel.loginStudent(lrgRequest)

            }
        }
    }

}

