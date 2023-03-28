package com.bqliang.drawbasics.path

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class PathFillTypeView : View {
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
    private val mPath = Path()

    init {
        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL

        mPath.addRect(100f, 100f, 300f, 300f, Path.Direction.CW)
        mPath.addCircle(300f, 300f, 100f, Path.Direction.CW)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /**
         * Path 的填充模式与 Paint 的填充模式不同。Path 的填充模式是指填充 Path 的哪部分。
         * Path.FillType 表示 Path 的填充模式，它有 4 个枚举值:
         * 1. [Path.FillType.WINDING]：默认值，表示填充 Path 的所有区域，不管 Path 的几何图形是怎样的。
         * 2. [Path.FillType.EVEN_ODD]：取 path 所在并不相交的区域
         * 3. [Path.FillType.INVERSE_WINDING]：取 path 的外部区域
         * 4. [Path.FillType.INVERSE_EVEN_ODD]：取 path 的外部和相交区域
         */

//        mPath.fillType = Path.FillType.WINDING
        mPath.fillType = Path.FillType.EVEN_ODD
//        mPath.fillType = Path.FillType.INVERSE_WINDING
//        mPath.fillType = Path.FillType.INVERSE_EVEN_ODD
        canvas.drawPath(mPath, mPaint)
    }
}