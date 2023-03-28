package com.bqliang.drawbasics.basicshapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class PaintBasicsView: View {
    constructor(context: Context):super(context)
    constructor(context: Context, attrs: AttributeSet):super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int):super(context, attrs, defStyleAttr)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int, defStyleRes: Int):super(context, attrs, defStyleAttr, defStyleRes)

    private val mPaint = Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 设置画笔颜色
        mPaint.color = Color.BLUE

        /**
         * 设置画笔填充样式
         * [Paint.setStyle] (style: Paint.Style)：设置画笔填充样式
         * 1. [Paint.Style.FILL]: 仅内部填充
         * 2. [Paint.Style.FILL_AND_STROKE]: 填充和描边
         * 3. [Paint.Style.STROKE]: 描边
         */
        mPaint.style = Paint.Style.FILL

        // 设置描边宽度，仅当画笔样式为描边或填充描边时有效
        mPaint.strokeWidth = 15f

        /**
         * 抗锯齿
         * [Paint.setAntiAlias] (boolean: Boolean)：设置是否抗锯齿
         * 一般在绘制不规则的图形时使用，比如圆形、文字等。
         * 在绘制棱角分明的图像时，比如一个矩形、一张位图，不需要打开抗锯齿功能
         */

        // 开始抗锯齿画一个实心圆
        mPaint.isAntiAlias = true
        canvas.drawCircle(200f, 200f, 100f, mPaint)
        // 关闭抗锯齿画一个空心圆
        mPaint.isAntiAlias = false
        mPaint.style = Paint.Style.STROKE
        canvas.drawCircle(500f, 200f, 100f, mPaint)


        /**
         * 设置画笔的透明度
         * [Paint.setAlpha] (a: Int)：设置画笔的透明度
         * 0：完全透明
         * 255：完全不透明
         */
        mPaint.alpha = 100
        mPaint.style = Paint.Style.FILL_AND_STROKE
        canvas.drawCircle(200f, 500f, 100f, mPaint)


        /**
         * 设置画笔的阴影
         * [Paint.setShadowLayer] (radius: Float, dx: Float, dy: Float, color: Int)：设置画笔的阴影
         * radius：阴影的模糊程度
         * dx：阴影的横向偏移量
         * dy：阴影的纵向偏移量
         * color：阴影的颜色
         */
        mPaint.alpha = 255
        mPaint.setShadowLayer(10f, 15f, 15f, Color.GRAY)
        canvas.drawCircle(500f, 500f, 100f, mPaint)
    }
}