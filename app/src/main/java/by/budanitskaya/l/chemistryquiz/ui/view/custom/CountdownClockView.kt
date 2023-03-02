package by.budanitskaya.l.chemistryquiz.ui.view.custom

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import by.budanitskaya.l.chemistryquiz.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class CountdownClockView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private var setAngle = 0f

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.CountdownClockView,
            0, 0
        )
        try {
            setAngle = typedArray.getFloat(R.styleable.CountdownClockView_angle, DEFAULT_ANGLE)
        } finally {
            typedArray.recycle()
        }
    }

    private fun drawAngle(
        angle: Float,
        canvas: Canvas?,
        centerX: Double,
        centerY: Double,
        rimWidth: Double,
        radius: Double,
        innerRadius: Double
    ) {
        val path = Path()
        val oval = RectF()
        oval[(centerX - innerRadius).toFloat(), (centerY - innerRadius).toFloat(), (centerX + innerRadius).toFloat()] =
            (centerY + innerRadius).toFloat()
        canvas?.drawArc(oval, -90F, angle, true, calculateArcPaint())

        path.moveTo(
            (centerX - rimWidth * cos(ONE_DEGREE * angle)).toFloat(),
            (centerY - rimWidth * sin(ONE_DEGREE * angle).toFloat()).toFloat()
        )
        path.lineTo(
            (centerX.toFloat() + rimWidth * cos(ONE_DEGREE * angle).toFloat()).toFloat(),
            (centerY.toFloat() + rimWidth * sin(
                ONE_DEGREE * angle
            ).toFloat()).toFloat()
        )
        path.lineTo(
            (centerX.toFloat() + radius * sin(ONE_DEGREE * angle)).toFloat(),
            (centerY - radius * cos(ONE_DEGREE * angle)).toFloat()
        )

        path.close()
        canvas?.drawPath(path, Paint().apply {
            color = Color.BLACK
            style = Paint.Style.FILL
        })
    }

    private fun calculateStrokePaint(width: Float) = Paint().apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = width
    }

    private fun calculateArcPaint() = Paint().apply {
        color = Color.BLUE
        strokeWidth = ARC_WIDTH
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val centreX = (right - left).toDouble() / 2
        val centreY = (bottom - top).toDouble() / 2
        val rimWidth = (min(centreX, centreY).toFloat() / 10).toDouble()
        val radius = ((min(right - left, bottom - top).toFloat()) / 2).toDouble()
        val radiusSmall = radius - rimWidth

        drawAngle(setAngle, canvas, centreX, centreY, rimWidth, radius, radiusSmall)

        canvas?.drawCircle(
            centreX.toFloat(),
            centreY.toFloat(),
            (radius - rimWidth / 2).toFloat(),
            calculateStrokePaint(rimWidth.toFloat())
        )

        canvas?.drawCircle(
            centreX.toFloat(),
            centreY.toFloat(),
            (rimWidth / 2).toFloat(),
            calculateStrokePaint(rimWidth.toFloat())
        )
    }

    companion object {
        const val ONE_DEGREE = 3.1415 / 2 / 90
        const val ARC_WIDTH = 5f
        const val DEFAULT_ANGLE = 60f
    }
}