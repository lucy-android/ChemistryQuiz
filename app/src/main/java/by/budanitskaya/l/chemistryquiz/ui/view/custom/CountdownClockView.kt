package by.budanitskaya.l.chemistryquiz.ui.view.custom

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import kotlin.math.min

class CountdownClockView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private fun calculatePaint(width: Float) = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = width
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centreX = (right - left).toDouble() / 2
        val centreY = (bottom - top).toDouble() / 2
        val strokeWidth = min(centreX, centreY).toFloat() / 10
        canvas?.drawCircle(
            centreX.toFloat(),
            centreY.toFloat(),
            (min(right - left, bottom - top).toFloat()) / 2 - strokeWidth,
            calculatePaint(strokeWidth)
        )
    }
}