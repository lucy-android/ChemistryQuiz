package by.budanitskaya.l.chemistryquiz.ui.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.min


class CountdownClockView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private fun calculateStrokePaint(width: Float) = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = width
    }

    private fun calculateArcPaint() = Paint().apply {
        color = Color.BLUE
        strokeWidth = 5f
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        val centreX = (right - left).toDouble() / 2
        val centreY = (bottom - top).toDouble() / 2
        val rimWidth = min(centreX, centreY).toFloat() / 10
        val radius = (min(right - left, bottom - top).toFloat()) / 2
        val radiusSmall = radius - rimWidth

        canvas?.drawCircle(
            centreX.toFloat(),
            centreY.toFloat(),
            radius - rimWidth / 2,
            calculateStrokePaint(rimWidth)
        )


        val oval = RectF()
        oval[(centreX - radiusSmall).toFloat(), (centreY - radiusSmall).toFloat(), (centreX + radiusSmall).toFloat()] =
            (centreY + radiusSmall).toFloat()
        canvas?.drawArc(oval, 0F, 270F, true, calculateArcPaint())

    }
}