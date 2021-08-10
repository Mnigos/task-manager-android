package com.example.task_manager_android

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.task_manager_android.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        binding = ActivityRegisterBinding.inflate(layoutInflater)

        binding.btnRegister.setOnClickListener {
            if (binding.editEmail.text.toString() == "" ||
                binding.editUsername.text.toString() == "" ||
                binding.editPassword.text.toString() == "" ||
                binding.editPasswordConfirmation.text.toString() == ""
            ) return@setOnClickListener
        }
    }
}
