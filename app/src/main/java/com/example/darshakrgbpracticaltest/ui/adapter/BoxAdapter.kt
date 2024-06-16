package com.example.darshakrgbpracticaltest.ui.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.darshakrgbpracticaltest.R
import com.example.darshakrgbpracticaltest.databinding.CellBoxItemBinding
import com.example.darshakrgbpracticaltest.helper.ColorPhase
import kotlin.random.Random

class BoxAdapter(
    private val activity: Activity,
    private val numberOfBoxes: Int
): RecyclerView.Adapter<BoxAdapter.BoxViewHolder>() {

    private var currentColorPhase = ColorPhase.RED
    private var initialSelectionIndex = -1
    private var currentSelectionIndex = -1
    private var copyInitialSelectionIndex = -1
    private var selectedIndexes = mutableListOf<Int>()
    private var previousSelectionIndexes = mutableListOf<Int>()

    init {
        initialSelection()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxViewHolder {
        return BoxViewHolder(
            CellBoxItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoxViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = numberOfBoxes

    inner class BoxViewHolder(private val _binding: CellBoxItemBinding): ViewHolder(_binding.root) {
        fun bind(position: Int) = with(_binding) {
            val number = position + 1
            tvNumber.text = number.toString()

            when(position) {
                initialSelectionIndex -> {
                    when(currentColorPhase) {
                        ColorPhase.RED -> tvNumber.setBackgroundResource(R.drawable.bg_red_border)
                        ColorPhase.GREEN -> tvNumber.setBackgroundResource(R.drawable.bg_green_border)
                        ColorPhase.BLUE -> tvNumber.setBackgroundResource(R.drawable.bg_blue_border)
                    }
                }

                currentSelectionIndex -> {
                    when(currentColorPhase) {
                        ColorPhase.RED -> tvNumber.setBackgroundResource(R.drawable.bg_red)
                        ColorPhase.GREEN -> tvNumber.setBackgroundResource(R.drawable.bg_green)
                        ColorPhase.BLUE -> tvNumber.setBackgroundResource(R.drawable.bg_blue)
                    }
                }

                in previousSelectionIndexes.filter { it != currentSelectionIndex } -> {
                    when(currentColorPhase) {
                        ColorPhase.RED -> tvNumber.setBackgroundResource(R.drawable.bg_red_border)
                        ColorPhase.GREEN -> tvNumber.setBackgroundResource(R.drawable.bg_green_border)
                        ColorPhase.BLUE -> tvNumber.setBackgroundResource(R.drawable.bg_blue_border)
                    }
                }
                else -> {
                    when(currentColorPhase){
                        ColorPhase.RED -> tvNumber.setBackgroundResource(R.drawable.bg_grey)
                        ColorPhase.GREEN -> tvNumber.setBackgroundResource(R.drawable.bg_red_border)
                        ColorPhase.BLUE -> tvNumber.setBackgroundResource(R.drawable.bg_green)
                    }
                }

            }

            tvNumber.setOnClickListener {
                handleBoxClick(position)
            }

        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun handleBoxClick(position: Int) {
        if (position == copyInitialSelectionIndex) {
            val remainingRandom = if (currentColorPhase == ColorPhase.RED) {
                (0 until numberOfBoxes).filter { it != copyInitialSelectionIndex && it !in selectedIndexes }
            } else {
                (0 until numberOfBoxes).filter { it !in selectedIndexes }
            }
            if (remainingRandom.isNotEmpty()) {
                val randomIndex = remainingRandom.random()
                currentSelectionIndex = randomIndex
                previousSelectionIndexes.add(randomIndex)
                selectedIndexes.add(randomIndex)
                notifyDataSetChanged()
            } else {
                when(currentColorPhase) {
                    ColorPhase.RED -> {
                        currentColorPhase = ColorPhase.GREEN
                        resetSelectionIndexes()
                    }
                    ColorPhase.GREEN -> {
                        currentColorPhase = ColorPhase.BLUE
                        resetSelectionIndexes()
                    }
                    ColorPhase.BLUE -> {
                        endGame()
                    }
                }
            }
        }
    }

    private fun initialSelection() {
        initialSelectionIndex = Random.nextInt(numberOfBoxes)
        copyInitialSelectionIndex = initialSelectionIndex
        selectedIndexes.add(initialSelectionIndex)
        previousSelectionIndexes.add(initialSelectionIndex)
        notifyItemChanged(initialSelectionIndex)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun resetSelectionIndexes() {
        selectedIndexes.clear()
        previousSelectionIndexes.clear()
        initialSelectionIndex = -1
        notifyDataSetChanged()
    }

    private fun endGame() {
        activity.finish()
    }


}