package com.example.androiduicomponents.molecules

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.transition.TransitionManager
import android.transition.AutoTransition
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.appcompat.content.res.AppCompatResources
import com.example.androiduicomponents.R
import com.example.androiduicomponents.databinding.ExpandableCardMoleculeLayoutBinding
import com.example.androiduicomponents.molecules.models.PicassoImageContent

class ExpandableCardMolecule @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes id: Int = 0
) : FrameLayout(context, attrs, id) {

    sealed class ToggleState {
        data object Expanded : ToggleState()
        data object Collapsed : ToggleState()

        // Simplify state as boolean
        private val asBoolean: Boolean
            get() = this is Expanded

        fun toggle(): ToggleState =
            if (asBoolean) Collapsed else Expanded
    }

    // declare binding
    private val binding =
        ExpandableCardMoleculeLayoutBinding.inflate(LayoutInflater.from(context), this, true)

    // expose dynamic layout
    var content = binding.cardContent

    // set properties
    var state: ToggleState = ToggleState.Collapsed
        set(value) {
            field = value

            when (value) {
                ToggleState.Expanded -> {
                    with(binding) {
                        toggle.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context,
                                R.drawable.baseline_arrow_drop_down_24 // TODO: update chevron
                            )
                        )
                        divider.visibility = View.VISIBLE
                        cardContent.visibility = View.VISIBLE
                    }
                }

                ToggleState.Collapsed -> {
                    with(binding) {
                        toggle.setImageDrawable(
                            AppCompatResources.getDrawable(
                                context,
                                R.drawable.baseline_arrow_drop_down_24
                            )
                        )
                        divider.visibility = View.INVISIBLE
                        cardContent.visibility = View.GONE
                    }
                }
            }
        }

    // header text
    var header: TextView? = null
        set(value) {
            value.let{
                binding.heading.text = it?.text
            }
            field = value
        }

    // logo
    var logo: PicassoImageContent? = null // by ViewDelegates.remoteImageLoad(binding.logo)
        set(value){
            value?.let {
//                binding.logo.loa
            }
            field = value
        }

    // toggle
    var toggle: Int? = null
        set(value){
            value.let {
                binding.toggle
            }
            field = value
        }

    // click listener
    var clickListener: (() -> Unit)? = null
        set(value) {
            field = value

            binding.cardHeader.setOnClickListener {
                // Animate child layout
                TransitionManager.beginDelayedTransition(binding.cardContent, AutoTransition())
                // toggle state
                state = state.toggle()
                value?.invoke()
            }
        }
}