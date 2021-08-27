package com.example.registration.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.example.registration.databinding.ActivityMainBinding
import com.example.registration.models.RegistrationRequest
import com.example.registration.viewModel.UserViewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val userViewModel: UserViewModel by viewModels() //factory design patern

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupSpinner()
        clickRegister()

    }

    override fun onResume() {
        super.onResume()
        userViewModel.registrationLiveData.observe(this,{regResponse->
            if (regResponse.studentId.isNullOrEmpty()){
                Toast.makeText(baseContext,"Registration successful", Toast.LENGTH_LONG).show()

            }
            binding.pbRegistration.visibility= View.GONE
        })
        userViewModel.errorLiveData.observe(this,{error->
            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
            binding.pbRegistration.visibility= View.GONE
        })
    }

    fun setupSpinner() {
        var nationalities= arrayOf("select Nationality","KENYAN","RWANDAN","SOUTH SUDANESE","UGANDAN")
        var nationalityadapter = ArrayAdapter(baseContext,android.R.layout.simple_spinner_item,nationalities)
        nationalityadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spNationality.adapter = nationalityadapter
    }

    fun clickRegister() {
        binding.btnLogin.setOnClickListener {
            var intent= Intent(baseContext, LoginActivity::class.java)
            startActivity(intent)
        }
        var error = false
        binding.btnRegister.setOnClickListener {
            var name = binding.etName.text.toString()
            if (name.isEmpty()) {
                error = true
                binding.etName.setError("this field required")
            }
            var dob =binding.etDob.text.toString()
            if (dob.isEmpty()) {
                error = true
                binding.etDob.setError("this field required")
            }
            var nationality=""
            if (binding.spNationality.selectedItemPosition!=0){
               nationality = binding.spNationality.selectedItem.toString()
            }
            else{
                error = true
                Toast.makeText(baseContext,"select Nationality", Toast.LENGTH_LONG).show()
            }
            var phoneNumber = binding.etPhone.text.toString()
            if (phoneNumber.isEmpty()) {
                error = true
                binding.etPhone.setError("this field required")
            }
            var email = binding.etEmail.text.toString()
            if (email.isEmpty()) {
                error = true
               binding.etEmail.setError("this field required")
            }
            var password= binding.etPassword.text.toString()
            if (password.isEmpty()) {
                error = true
                binding.etPassword.setError("this field required")
            }

            if (!error){
                binding.pbRegistration.visibility=View.VISIBLE
                var regRequest= RegistrationRequest(
                    name = name,
                    PhoneNumber = phoneNumber,
                    email = email,
                    dateOfBirth = dob,
                    nationality = nationality,
                    password = password
                )
                userViewModel.registerStudent(regRequest)
            }
        }
    }
}
data class Student(var name: String, var dob: String, var idNo:String, var nationality:String, var phoneNumber:String, var email: String){

}
