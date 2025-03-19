package com.example.androiduicomponents.atoms

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.DashPathEffect
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.renderer.PieChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler
import kotlin.math.cos
import kotlin.math.sin

class DottedPieChartRenderer(
    chart: PieChart,
    animator: ChartAnimator,
    viewPortHandler: ViewPortHandler
) : PieChartRenderer(chart, animator, viewPortHandler) {

    override fun drawData(c: Canvas) {
        super.drawData(c)

        val pieData = mChart.data ?: return
        if (pieData.dataSetCount == 0) return

        val center = mChart.centerCircleBox
        val radius = mChart.radius
        val innerRadius = radius * (mChart.holeRadius / 100f)
        val rotationAngle = mChart.rotationAngle
        val phaseY = mAnimator.phaseY

        val dataSet = pieData.getDataSetByIndex(0)
        val entry = dataSet.getEntryForIndex(0)
        val sweepAngle = (entry.y / pieData.yValueSum) * 360f * phaseY

        if (sweepAngle == 0f) return

        // Outer arc paint (visually thinner)
        val outerArcPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 3f // slightly thinner
            color = Color.BLUE
            pathEffect = DashPathEffect(floatArrayOf(12f, 8f), 0f)
        }

        // Inner arc + radial lines paint
        val edgePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 4.5f // a bit thicker to visually match
            color = Color.BLUE
            pathEffect = DashPathEffect(floatArrayOf(12f, 8f), 0f)
        }

        val startAngle = rotationAngle
        val endAngle = startAngle + sweepAngle

        val outerRect = RectF(
            center.x - radius, center.y - radius,
            center.x + radius, center.y + radius
        )
        val innerRect = RectF(
            center.x - innerRadius, center.y - innerRadius,
            center.x + innerRadius, center.y + innerRadius
        )

        // Outer arc
        val outerArc = Path().apply {
            addArc(outerRect, startAngle, sweepAngle)
        }
        c.drawPath(outerArc, outerArcPaint)

        // Lines between outer and inner arcs
        val startOuterX = center.x + radius * cos(Math.toRadians(startAngle.toDouble())).toFloat()
        val startOuterY = center.y + radius * sin(Math.toRadians(startAngle.toDouble())).toFloat()
        val startInnerX = center.x + innerRadius * cos(Math.toRadians(startAngle.toDouble())).toFloat()
        val startInnerY = center.y + innerRadius * sin(Math.toRadians(startAngle.toDouble())).toFloat()

        val endOuterX = center.x + radius * cos(Math.toRadians(endAngle.toDouble())).toFloat()
        val endOuterY = center.y + radius * sin(Math.toRadians(endAngle.toDouble())).toFloat()
        val endInnerX = center.x + innerRadius * cos(Math.toRadians(endAngle.toDouble())).toFloat()
        val endInnerY = center.y + innerRadius * sin(Math.toRadians(endAngle.toDouble())).toFloat()

        val startLine = Path().apply {
            moveTo(startInnerX, startInnerY)
            lineTo(startOuterX, startOuterY)
        }
        c.drawPath(startLine, edgePaint)

        val endLine = Path().apply {
            moveTo(endOuterX, endOuterY)
            lineTo(endInnerX, endInnerY)
        }
        c.drawPath(endLine, edgePaint)

        // Inner arc (drawn in reverse)
        val innerArc = Path().apply {
            addArc(innerRect, endAngle, -sweepAngle)
        }
        c.drawPath(innerArc, edgePaint)
    }
}
