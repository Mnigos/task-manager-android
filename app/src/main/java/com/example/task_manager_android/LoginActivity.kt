package com.example.task_manager_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.task_manager_android.databinding.ActivityLoginBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {

            if (binding.editEmail.text.toString() == "" || binding.editPassword.text.toString() == "")
                return@setOnClickListener

            val response = AuthFetch("/auth/login").login(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )

            val gson = GsonBuilder().create()
            val token = gson.fromJson(response, LoginResponse::class.java)
        }

        binding.btnRegister.setOnClickListener {
            val registerActivity = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(registerActivity)
        }
    }
}

class LoginResponse(val token: String)
