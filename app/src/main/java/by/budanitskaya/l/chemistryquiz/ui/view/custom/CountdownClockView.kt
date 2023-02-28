package by.budanitskaya.l.chemistryquiz.ui.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


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

        val angle = 123

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

        canvas?.drawCircle(
            centreX.toFloat(),
            centreY.toFloat(),
            rimWidth / 2,
            calculateStrokePaint(rimWidth)
        )

        val path = Path()


        val oval = RectF()
        oval[(centreX - radiusSmall).toFloat(), (centreY - radiusSmall).toFloat(), (centreX + radiusSmall).toFloat()] =
            (centreY + radiusSmall).toFloat()
        canvas?.drawArc(oval, -90F, angle.toFloat(), true, calculateArcPaint())

        path.moveTo(
            (centreX - rimWidth * cos(3.1415 / 2 / 90 * angle)).toFloat(),
            (centreY - rimWidth * sin(3.1415 / 2 / 90 * angle).toFloat()).toFloat()
        )
        path.lineTo(
            centreX.toFloat() + rimWidth * cos(3.1415 / 2 / 90 * angle).toFloat(),
            centreY.toFloat() + rimWidth * sin(
                3.1415 / 2 / 90 * angle
            ).toFloat()
        )
        path.lineTo(
            (centreX.toFloat() + radius * sin(3.1415 / 2 / 90 * angle)).toFloat(),
            (centreY - radius * cos(3.1415 / 2 / 90 * angle)).toFloat()
        )

        path.close()
        canvas?.drawPath(path, Paint().apply {
            color = Color.BLACK
            style = Paint.Style.FILL
        })




    }

}