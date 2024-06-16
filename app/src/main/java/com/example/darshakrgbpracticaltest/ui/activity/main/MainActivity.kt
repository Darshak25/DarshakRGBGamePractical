package com.example.darshakrgbpracticaltest.ui.activity.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.darshakrgbpracticaltest.databinding.ActivityMainBinding
import com.example.darshakrgbpracticaltest.ui.activity.game.GameActivity
import com.example.darshakrgbpracticaltest.utils.NUMBER_OF_BOXES
import com.example.darshakrgbpracticaltest.utils.extensions.startIntent
import com.example.darshakrgbpracticaltest.utils.extensions.toast

class MainActivity : AppCompatActivity() {

    private val _binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        _binding.btnNext.setOnClickListener {
            val number = _binding.etNumber.text.toString().toIntOrNull() ?: 0
            if (number >= 5) {
                startIntent(NUMBER_OF_BOXES, number, GameActivity::class.java)
            } else {
                toast("Please enter minimum 5 number")
            }
        }

    }
}