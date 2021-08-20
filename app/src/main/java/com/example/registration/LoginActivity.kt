package com.example.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.registration.api.ApiClient
import com.example.registration.api.ApiInterface
import com.example.registration.databinding.ActivityLoginBinding
import com.example.registration.models.LoginRequest
import com.example.registration.models.RegistrationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        clickLogin()
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
                var retrofit= ApiClient.buildApiClient(ApiInterface::class.java)
                var request= retrofit.loginStudent(lrgRequest)
                request.enqueue(object : Callback<RegistrationResponse?> {
                    override fun onResponse(
                        call: Call<RegistrationResponse?>,
                        response: Response<RegistrationResponse?>
                    ) {
                        binding.pbLogin.visibility= View.VISIBLE
                        if (response.isSuccessful){
                            Toast.makeText(baseContext, "Login is successful", Toast.LENGTH_LONG).show()
                        }
                        else{
                            Toast.makeText(baseContext, response.errorBody()?.string(), Toast.LENGTH_LONG).show()
                        }
                    }

                    override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                        binding.pbLogin.visibility=View.GONE
                        Toast.makeText(baseContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
            }
        }
    }

}

