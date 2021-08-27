package com.example.registration.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import com.example.registration.databinding.ActivityLoginBinding
import com.example.registration.models.LoginRequest
import com.example.registration.viewModel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickLogin()
    }

    override fun onResume() {
        super.onResume()
        userViewModel.loginLiveData.observe(this, { lrgResponse->
            if (lrgResponse.studentId.isNullOrEmpty()){
                Toast.makeText(baseContext,"Login successful",Toast.LENGTH_LONG).show()

            }
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
            if(email.isEmpty()){
                error= true
                binding.tvEmail.setError("This field required")
            }
            var password= binding.tvPassword.text.toString()
            if(password.isEmpty()){
                error= true
                binding.tvPassword.setError("This field required")
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

