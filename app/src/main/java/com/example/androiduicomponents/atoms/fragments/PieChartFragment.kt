package com.example.androiduicomponents.atoms.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.example.androiduicomponents.atoms.DottedPieChartRenderer
import com.example.androiduicomponents.databinding.FragmentPieChartBinding
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class PieChartFragment : Fragment() {

    private lateinit var pieChart: PieChart
    private lateinit var binding: FragmentPieChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPieChartBinding.inflate(inflater)
        pieChart = binding.pieChart
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupPieChart()
    }

    private fun setupPieChart() {
        val entries = listOf(
            PieEntry(10f, "Guaranteed"),
            PieEntry(90f, "All other assets")
        )

        val dataSet = PieDataSet(entries, "").apply {
            colors = listOf(Color.TRANSPARENT, Color.parseColor("#99CCFF"))
            setDrawValues(false)
        }

        val data = PieData(dataSet)
        pieChart.data = data

        pieChart.setDrawEntryLabels(false)
        pieChart.description.isEnabled = false
//        pieChart.legend.isEnabled = true
        pieChart.setUsePercentValues(false)
        pieChart.setDrawSlicesUnderHole(false)
        pieChart.holeRadius = 85f
        pieChart.transparentCircleRadius = 85f

        pieChart.isHighlightPerTapEnabled = false  // On clicking the pie chart it gets highlighted if true
        pieChart.highlightValues(null) // Clear any existing highlights

        pieChart.renderer = DottedPieChartRenderer(
            pieChart,
            pieChart.animator,
            pieChart.viewPortHandler
        )

        pieChart.invalidate()


        // Disable built-in legend
        pieChart.legend.isEnabled = false

        // Setup custom legend
        val colors = listOf(Color.RED, Color.GREEN, Color.BLUE)
        val labels = listOf("Category A", "Category B", "Category C")

        val legendContainer = binding.legendContainer
        legendContainer.removeAllViews()

        labels.forEachIndexed { index, label ->
            val item = LinearLayout(requireContext()).apply {
                orientation = LinearLayout.HORIZONTAL
                val colorView = View(context).apply {
                    layoutParams = LinearLayout.LayoutParams(30, 30).apply {
                        marginEnd = 16
                    }
                    setBackgroundColor(colors[index])
                }

                val labelView = TextView(context).apply {
                    text = label
                    textSize = 16f
                }

                addView(colorView)
                addView(labelView)
            }

            legendContainer.addView(item)
        }

    }
}
