package com.vvkdev.notte

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.vvkdev.notte.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }
    }
}