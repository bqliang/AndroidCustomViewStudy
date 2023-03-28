package com.bqliang.drawbasics.path

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class PathBasicsView : View {
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
    private val trianglePath = Path()
    private val arcPath = Path()
    private val rectF = RectF(500f, 100f, 700f, 250f)

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = 5f

        initTrianglePath()
        initArcPath()
    }

    private fun initTrianglePath() {
        /**
         * 画直线
         * [Path.moveTo] (x: Float, y: Float) 移动到指定坐标
         * [Path.lineTo] (x: Float, y: Float) 画直线到指定坐标
         * [Path.close] () 闭合路径
         */
        trianglePath.moveTo(100f, 100f)
        trianglePath.lineTo(200f, 100f)
        trianglePath.lineTo(150f, 200f)
        //  close()函数会将路径首尾点连接起来，形成闭环
        trianglePath.close()
    }

    private fun initArcPath() {
        /**
         * 画弧线
         * [Path.moveTo] (oval: RectF, startAngle: Float, sweepAngle: Float)
         * [Path.moveTo] (oval: RectF, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean)
         * [Path.moveTo] (left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float, forceMoveTo: Boolean)
         * oval: 指定弧线所在的椭圆
         * startAngle: 弧线开始角度
         * sweepAngle: 弧线扫过的角度
         * forceMoveTo: 是否强制移动到弧线起点
         */
        arcPath.moveTo(300f, 300f)
        // 默认情况下，arcTo() 函数不会移动到弧线起点，所以需要手动调用 moveTo() 函数，
        // 又或者将 forceMoveTo 参数设置为 true
        arcPath.arcTo(rectF, 180f, 180f, false)
        arcPath.close()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(trianglePath, mPaint)
        canvas.drawPath(arcPath, mPaint)
        mPaint.color = Color.BLUE
        mPaint.strokeWidth = 3f
        canvas.drawRect(rectF, mPaint)


        // ======================= addXXX系列函数 START ======================= //
        // 路径一般都是连贯的，而 addXXX 系列函数可以让我们直接往 Path 中 "添加" 一些曲线，而不必考虑连贯性
        /**
         * 添加圆弧
         * [Path.addArc] (oval: RectF, startAngle: Float, sweepAngle: Float)
         * [Path.addArc] (left: Float, top: Float, right: Float, bottom: Float, startAngle: Float, sweepAngle: Float)
         * oval: 指定弧线所在的椭圆
         * startAngle: 弧线开始角度
         * sweepAngle: 弧线扫过的角度
         */

        /**
         * 添加矩形
         * [Path.addRect] (rect: RectF, dir: Path.Direction)
         * [Path.addRect] (left: Float, top: Float, right: Float, bottom: Float, dir: Path.Direction)
         * rect: 指定矩形
         * dir: 指定矩形的方向 (顺时针/逆时针)
         *    Path.Direction.CW: 顺时针
         *    Path.Direction.CCW: 逆时针
         */
        val cwRectPath = Path()
        cwRectPath.addRect(100f, 400f, 500f, 550f, Path.Direction.CW)
        canvas.drawPath(cwRectPath, mPaint)
        val ccwRectPath = Path()
        ccwRectPath.addRect(550f, 400f, 950f, 550f, Path.Direction.CCW)
        canvas.drawPath(ccwRectPath, mPaint)
        val text = "Hello World!"
        mPaint.textSize = 60f
        canvas.drawTextOnPath(text, cwRectPath, 0f, 0f, mPaint)
        canvas.drawTextOnPath(text, ccwRectPath, 0f, 0f, mPaint)

        /**
         * 添加圆角矩形
         * [Path.addRoundRect] (rect: RectF, radii: FloatArray, dir: Path.Direction)
         * [Path.addRoundRect] (rect: RectF, rx: Float, ry: Float, dir: Path.Direction)
         * [Path.addRoundRect] (left: Float, top: Float, right: Float, bottom: Float, radii: FloatArray, dir: Path.Direction)
         * [Path.addRoundRect] (left: Float, top: Float, right: Float, bottom: Float, rx: Float, ry: Float, dir: Path.Direction)
         * radii: 指定每个角的圆角半径，顺序为左上、右上、右下、左下
         * rx: 指定圆角的横向半径
         * ry: 指定圆角的纵向半径
         * dir: 指定矩形的方向 (顺时针/逆时针)
         */
        val roundRectPath = Path()
        roundRectPath.addRoundRect(100f, 600f, 500f, 750f, 50f, 50f, Path.Direction.CW)
        mPaint.color = Color.MAGENTA
        canvas.drawPath(roundRectPath, mPaint)

        /**
         * 添加圆
         * [Path.addCircle] (x: Float, y: Float, radius: Float, dir: Path.Direction)
         * x: 圆心的 x 坐标
         * y: 圆心的 y 坐标
         * radius: 圆的半径
         * dir: 指定圆的方向 (顺时针/逆时针)
         */

        /**
         * 添加椭圆
         * [Path.addOval] (oval: RectF, dir: Path.Direction)
         * [Path.addOval] (left: Float, top: Float, right: Float, bottom: Float, dir: Path.Direction)
         * oval: 指定椭圆
         * dir: 指定椭圆的方向 (顺时针/逆时针)
         */
        // ======================= addXXX系列函数 START ======================= //


        /**
         * 重置 Path
         * 1.[Path.reset] ()
         * 函数类似于新建一个路径对象，它的所有数据空间都会被回收并重新分配，但不会清除 FillType
         *
         * 2.[Path.rewind] ()
         * rewind()函数会清除 FillType 及所有的直线、曲线、点的数据等，但是会保留数据结构。
         * 这样可以实现快速重用，提高一定的性能。
         * 例如，重复绘制一类线段，它们的点的数量都相等，那么使用 rewind()函数可以保留装载点数据的数据结构，效率会更高
         * 注意的是，只有在重复绘制相同的路径时，这些数据结构才是可以复用的
         *
         * 整体来讲，rewind()函数不会清除内存，但会清除 FillType；
         * 而 reset()函数则会清除内存，但不会清除 FillType
         */
    }
}