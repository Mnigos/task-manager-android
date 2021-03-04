package com.example.task_manager_android

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.task_manager_android.databinding.ActivityLoginBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        binding.btnLogin.setOnClickListener {
            binding = ActivityLoginBinding.inflate(layoutInflater)

            val job: Job = CoroutineScope(Dispatchers.IO).launch {
                Log.d("HTTP_JSON", FetchApi("").getJSONString())
            }

            job.start()

            if (job.isCompleted) job.cancel()
        }

        binding.btnRegister.setOnClickListener {
            val registerActivity = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(registerActivity)
        }
    }
}
