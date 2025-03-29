package com.example.androiduicomponents.molecules.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.androiduicomponents.R
import com.example.androiduicomponents.databinding.FragmentView2ButtonToggleGroupListBinding
import com.example.androiduicomponents.molecules.models.ButtonGroupListModel


class View2ButtonToggleGroupListFragment : Fragment(R.layout.fragment_view2_button_toggle_group_list) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentView2ButtonToggleGroupListBinding.inflate(inflater)

        binding.toggleMaterialButton.run {
            setUpToggle(
                listOf(
                    ButtonGroupListModel("Year to date", "", items = listOf("1st","Second")),
                    ButtonGroupListModel("Most recent", "", items = listOf("Third", "Fourth"))
                )
            )
            selectToggleButton(0)
            setUpClickListener {
                Toast.makeText(context, "Item $it selected!!!", Toast.LENGTH_SHORT).show()
            }
        }
        return binding.root
    }
}