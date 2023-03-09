package by.budanitskaya.l.chemistryquiz.ui.view.custom

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import by.budanitskaya.l.chemistryquiz.R
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin


class CountdownClockView(context: Context, attributeSet: AttributeSet) :
    View(context, attributeSet) {

    private var sweepColor: Int = Color.MAGENTA
    private var rimColor: Int = Color.BLACK
    private var timeSeconds: Int = 1
    private var ringRotationAnimator: ValueAnimator? = null
    var lambda: (() -> Unit)? = null

    private var setAngle = 0f
        set(value) {
            field = value
            postInvalidateOnAnimation()
        }

    init {
        val typedArray = context.theme.obtainStyledAttributes(
            attributeSet,
            R.styleable.CountdownClockView,
            0, 0
        )
        try {
            timeSeconds =
                typedArray.getInteger(
                    R.styleable.CountdownClockView_time_seconds,
                    DEFAULT_TIME_SECONDS
                )

            rimColor = typedArray.getColor(R.styleable.CountdownClockView_rim_color, Color.BLACK)
            sweepColor =
                typedArray.getColor(R.styleable.CountdownClockView_sweep_color, Color.MAGENTA)
        } finally {
            typedArray.recycle()
        }

        ringRotationAnimator = ValueAnimator.ofFloat(0f, 360F).apply {
            addUpdateListener {
                setAngle = it.animatedValue as Float
                if (setAngle == 360f) {
                    lambda?.invoke()
                }
            }
        }.setAnimator(timeSeconds.toLong() * 1000)
    }

    private fun calculateStrokePaint(width: Float) = Paint().apply {
        color = rimColor
        style = Paint.Style.STROKE
        strokeWidth = width
    }

    private fun calculateArcPaint() = Paint().apply {
        color = sweepColor
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
        val innerRadius = radius - rimWidth

        val path = Path()
        val oval = RectF()
        oval[(centreX - innerRadius).toFloat(), (centreY - innerRadius).toFloat(), (centreX + innerRadius).toFloat()] =
            (centreY + innerRadius).toFloat()
        canvas?.drawArc(oval, -90F, setAngle, true, calculateArcPaint())

        path.moveTo(
            (centreX - rimWidth * cos(ONE_DEGREE * setAngle)).toFloat(),
            (centreY - rimWidth * sin(ONE_DEGREE * setAngle).toFloat()).toFloat()
        )
        path.lineTo(
            (centreX.toFloat() + rimWidth * cos(ONE_DEGREE * setAngle).toFloat()).toFloat(),
            (centreY.toFloat() + rimWidth * sin(
                ONE_DEGREE * setAngle
            ).toFloat()).toFloat()
        )
        path.lineTo(
            (centreX.toFloat() + radius * sin(ONE_DEGREE * setAngle)).toFloat(),
            (centreY - radius * cos(ONE_DEGREE * setAngle)).toFloat()
        )

        path.close()
        canvas?.drawPath(path, Paint().apply {
            color = rimColor
            style = Paint.Style.FILL
        })

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
        const val DEFAULT_TIME_SECONDS = 1
    }
}


private fun ValueAnimator.setAnimator(time: Long): ValueAnimator {
    return apply {
        duration = time
        interpolator = LinearInterpolator()
        start()
    }
}