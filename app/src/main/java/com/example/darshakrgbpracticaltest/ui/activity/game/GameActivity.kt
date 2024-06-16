package com.example.darshakrgbpracticaltest.ui.activity.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.darshakrgbpracticaltest.databinding.ActivityGameBinding
import com.example.darshakrgbpracticaltest.ui.adapter.BoxAdapter
import com.example.darshakrgbpracticaltest.utils.NUMBER_OF_BOXES

class GameActivity : AppCompatActivity() {

    private val _binding by lazy {
        ActivityGameBinding.inflate(layoutInflater)
    }

    private val numberOfBoxes by lazy {
        intent.getIntExtra(NUMBER_OF_BOXES, 0)
    }

    private var boxAdapter: BoxAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(_binding.root)

        prepareRecyclerView()

    }

    private fun prepareRecyclerView() {
        _binding.rvBoxes.apply {
            boxAdapter = BoxAdapter(this@GameActivity, numberOfBoxes)
            layoutManager = GridLayoutManager(this@GameActivity, 5)
            adapter = boxAdapter
        }
    }
}