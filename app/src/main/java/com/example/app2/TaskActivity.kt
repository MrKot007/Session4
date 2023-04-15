package com.example.app2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app2.databinding.ActivityTaskBinding

class TaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTaskBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.deadLine.text = intent.getStringExtra("date")
        binding.taskHeading.text = intent.getStringExtra("name")
        binding.descr.text = intent.getStringExtra("description")
        binding.back.setOnClickListener {
            finish()
        }
    }
}