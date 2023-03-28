package com.bqliang.drawbasics.basicshapes

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class CanvasBasicsView : View {
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

    private val mPaint = Paint()
    private val canvasBackgroundColor = Color.rgb(238, 238, 238)

    init {
        mPaint.style = Paint.Style.FILL
        mPaint.strokeWidth = 10f
        mPaint.color = Color.rgb(3, 155, 229)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         * 设置画布背景
         * 1. [Canvas.drawColor] (color: Int)：设置画布背景颜色
         * 2. [Canvas.drawRGB] (r: Int, g: Int, b: Int)：设置画布背景颜色
         * 3. [Canvas.drawARGB] (a: Int, r: Int, g: Int, b: Int)：设置画布背景颜色
         */
        canvas.drawColor(canvasBackgroundColor)


        /**
         * 画直线
         * [Canvas.drawLine] (startX: Float, startY: Float, stopX: Float, stopY: Float, paint: Paint)
         * startX：起点X坐标
         * startY：起点Y坐标
         * stopX：终点X坐标
         * stopY：终点Y坐标
         * paint：画笔
         *
         * 直线的粗细由画笔的 [Paint.getStrokeWidth] 属性决定
         */
        mPaint.strokeWidth = 10f
        canvas.drawLine(100f, 100f, 700f, 100f, mPaint)


        /**
         * 画多条直线
         * [Canvas.drawLines] (pts: FloatArray, paint: Paint)
         * [Canvas.drawLines] (pts: FloatArray, offset: Int, count: Int, paint: Paint)
         * pts：点的集合，每两个点确定一条直线
         * offset：跳过的点数
         * count：需要画的点数
         * paint：画笔
         */
        mPaint.strokeWidth = 5f
        val lines = floatArrayOf(
            100f, 200f, 200f, 200f,
            300f, 200f, 500f, 200f
        )
        canvas.drawLines(lines, mPaint)


        /**
         * 绘制点
         * [Canvas.drawPoint] (x: Float, y: Float, paint: Paint)
         * [Canvas.drawPoints] (pts: FloatArray, paint: Paint)
         * [Canvas.drawPoints] (pts: FloatArray, offset: Int, count: Int, paint: Paint)
         * pts：点的集合
         * offset：跳过的点数
         * count：需要画的点数
         */
        //mPaint.color = Color.BLUE
        mPaint.color = Color.RED
        mPaint.strokeWidth = 20f
        val points = floatArrayOf(
            600f, 250f,
            700f, 250f,
            800f, 250f,
        )
        canvas.drawPoints(points, 2, 4, mPaint)


        /**
         * 矩形
         * 1.Rect: 用于绘制矩形
         *  1.1 Rect() 创建一个空矩形
         *  1.2 Rect(left: Int, top: Int, right: Int, bottom: Int) 创建一个矩形
         *  1.3 Rect(rect: Rect) 创建一个矩形
         *
         * 2.RectF: 用于绘制矩形，支持浮点数
         *  2.1 RectF() 创建一个空矩形
         *  2.2 RectF(left: Float, top: Float, right: Float, bottom: Float) 创建一个矩形
         *  2.3 RectF(rect: RectF) 创建一个矩形
         *  2.4 RectF(rect: Rect) 创建一个矩形
         */
        val rect = Rect(100, 300, 400, 400)
        mPaint.color = Color.rgb(255, 196, 0)
        canvas.drawRect(rect, mPaint)
        mPaint.style = Paint.Style.STROKE
        canvas.drawRect(500f, 300f, 800f, 400f, mPaint)


        /**
         * 圆角矩形
         * [Canvas.drawRoundRect] (rect: RectF, rx: Float, ry: Float, paint: Paint)
         * rx：x方向上的圆角半径
         * ry：y方向上的圆角半径
         */
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.rgb(104, 159, 56)
        val rectF = RectF(100f, 500f, 400f, 600f)
        canvas.drawRoundRect(rectF, 25f, 25f, mPaint)


        // 圆形
        mPaint.color = Color.rgb(186, 104, 200)
        canvas.drawCircle(650f, 600f, 100f, mPaint)


        /**
         * 椭圆
         * 1.[Canvas.drawOval] (oval: RectF, paint: Paint)
         * 2.[Canvas.drawOval] (left: Float, top: Float, right: Float, bottom: Float, paint: Paint)
         */
        canvas.drawOval(100f, 700f, 400f, 800f, mPaint)


        /**
         * 弧
         * 1.[Canvas.drawArc] (oval: RectF, startAngle: Float, sweepAngle: Float, useCenter: Boolean, paint: Paint)
         * 2.[Canvas.drawArc] (left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, useCenter: Boolean, paint: Paint)
         * startAngle：起始角度
         * sweepAngle：扫过的角度
         * useCenter：是否连接到圆心
         */
        canvas.drawArc(100f, 800f, 400f, 900f, 0f, 90f, true, mPaint)
        canvas.drawArc(100f, 800f, 400f, 900f, 90f, 90f, false, mPaint)

        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 3f
        mPaint.color = Color.RED
        val rectF2 = RectF(500f, 800f, 900f, 1000f)
        canvas.drawRect(rectF2, mPaint)
        mPaint.color = Color.BLUE
        canvas.drawArc(rectF2, 0f, 90f, true, mPaint)
        mPaint.color = Color.GREEN
        canvas.drawArc(rectF2, 180f, 90f, false, mPaint)



    }
}