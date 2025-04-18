package com.example.androiduicomponents.molecules

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import com.example.androiduicomponents.R
import com.example.androiduicomponents.databinding.ButtonGroupListMoleculeLayoutBinding
import com.example.androiduicomponents.molecules.models.ButtonGroupListModel
import com.google.android.material.button.MaterialButton

class ButtonGroupListMolecule @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    private var currentSelection: Int = 0
    private var listOfButtons : List<ButtonGroupListModel> = mutableListOf()

    // Declare binding
    private var binding : ButtonGroupListMoleculeLayoutBinding =
        ButtonGroupListMoleculeLayoutBinding.inflate(
            LayoutInflater.from(context), this, true
        )

    // Initialize Buttons
    fun setUpToggle(toggleDataList: List<ButtonGroupListModel>){
        listOfButtons = toggleDataList
        toggleDataList.forEachIndexed { index, item ->
            val toggleButton = LayoutInflater.from(context)
                .inflate(
                    R.layout.button_group_list_item_layout,
                    binding.toggleGroup,
                    false
                ) as MaterialButton
            toggleButton.text = item.label
            toggleButton.id = index
            toggleButton.contentDescription = item.contentDescription
            binding.toggleGroup.addView(toggleButton)
            updateListData(item.items)
        }
    }

    // set the selected toggle button
    fun selectToggleButton(index: Int) {
        currentSelection = index
        binding.toggleGroup.check(currentSelection)
        updateListData(listOfButtons[index].items)
    }

    fun setUpClickListener(listener: (ButtonGroupListModel) -> Unit) {
        binding.toggleGroup.addOnButtonCheckedListener { _, checkedId, _ ->
            if (checkedId == -1) {
                // do not allow user to deselect the button
                selectToggleButton(currentSelection)
                updateListData(listOfButtons[currentSelection].items)
            } else if (currentSelection != checkedId) {
                // invoke listener when user selects different button
                currentSelection = checkedId
                listener.invoke(listOfButtons[checkedId])
                // animateSelection(currentSelection) // animate slide (Giving issues)
                updateListData(listOfButtons[currentSelection].items)
            }
        }
    }

    private fun animateSelection(currentSelection: Int){
        val button = findViewById<MaterialButton>(currentSelection)

        val targetX = button.x + (button.width/2)
        val targetWidth = button.width

        // Animate position change
        ValueAnimator.ofFloat(binding.slider.x, targetX).apply{
            duration = 500
            addUpdateListener { animator ->
                binding.slider.x = animator.animatedValue as Float
            }
            start()
        }

        // Animate width change
        ValueAnimator.ofInt(binding.slider.width, targetWidth).apply{
            duration = 500
            addUpdateListener { animator ->
                val newWidth = animator.animatedValue as Int
                val layoutParams = binding.slider.layoutParams
                layoutParams.width = newWidth
                binding.slider.layoutParams = layoutParams
            }
            start()
        }
    }

    fun updateListData(items: List<String>){
        binding.listItems.removeAllViews()
        items.forEach {
            val textView = TextView(context).apply{
                text = it
                textSize = 18f
                setTextColor(Color.BLACK)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    setMargins(16,16,16,16)
                }
            }
            binding.listItems.addView(textView)
        }

    }
}