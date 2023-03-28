package com.bqliang.drawbasics.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class RadarChartView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int) : super(
        context,
        attrs,
        defStyleAttr,
        defStyleRes
    )


    private val radarPaint = Paint()
    private val valuePaint = Paint()
    private var centerX = -1f
    private var centerY = -1f
    private val count = 6

    // 网格最大半径
    private var radius = -1f

    init {
        radarPaint.style = Paint.Style.STROKE
        radarPaint.color = Color.GREEN

        valuePaint.style = Paint.Style.FILL
        valuePaint.color = Color.BLUE
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //绘制蜘蛛网格
        drawPolygon(canvas)
        //画网格中线
        drawLines(canvas)
        //画数据图
        drawRegion(canvas)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        radius = (min(w, h) / 2 * 0.9).toFloat()
        centerX = (w / 2).toFloat()
        centerY = (h / 2).toFloat()
        postInvalidate()
    }


    private fun drawPolygon(canvas: Canvas) {
        // 每个正多边形之间的间距
        val margin = radius / count
        val path = Path()
        for (i in 1..count) { // 中心点不用绘制
            // 当前半径
            val curRadius = margin * i
            path.reset()
            for (j in 0 until count) {
                if (j == 0) {
                    path.moveTo(centerX + curRadius, centerY)
                } else {
                    val angle = j * 2 * Math.PI / count
                    val x = (centerX + curRadius * cos(angle)).toFloat()
                    val y = (centerY + curRadius * sin(angle)).toFloat()
                    path.lineTo(x, y)
                }
            }
            // 闭合路径
            path.close()
            canvas.drawPath(path, radarPaint)
        }
    }


    private fun drawLines(canvas: Canvas) {
        val path = Path()
        for (i in 0 until count) {
            path.reset()
            path.moveTo(centerX, centerY)
            val angle = i * 2 * Math.PI / count
            val x = (centerX + radius * cos(angle)).toFloat()
            val y = (centerY + radius * sin(angle)).toFloat()
            path.lineTo(x, y)
            canvas.drawPath(path, radarPaint)
        }
    }


    private fun drawRegion(canvas: Canvas) {
        val data = floatArrayOf(2f, 5f, 1f, 6f, 4f, 5f)
        val max = 6f
        val path = Path()
        valuePaint.alpha = 127
        for (i in data.indices) {
            val percent = data[i] / max
            val angle = i * 2 * Math.PI / count
            val x = (centerX + radius * cos(angle) * percent).toFloat()
            val y = (centerY + radius * sin(angle) * percent).toFloat()
            if (i == 0) {
                path.moveTo(x, centerY)
            } else {
                path.lineTo(x, y)
            }
            // 绘制小圆点
            canvas.drawCircle(x, y, 10f, valuePaint)
        }
        // 绘制填充区域
        valuePaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawPath(path, valuePaint)
    }
}