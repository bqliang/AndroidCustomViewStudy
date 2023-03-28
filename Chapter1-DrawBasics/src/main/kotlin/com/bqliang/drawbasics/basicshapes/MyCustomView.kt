package com.bqliang.drawbasics.basicshapes

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class MyCustomView : View {
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

    private var mPaint: Paint = Paint()

    init {
        mPaint.apply {
            // 设置画笔颜色
            color = Color.parseColor("#F8BBD0")

            /**
             * // 设置画笔填充样式
             * [Paint.setStyle] (style: Paint.Style)
             * 1.[Paint.Style.FILL] 仅内部填充
             * 2.[Paint.Style.FILL_AND_STROKE] 填充和描边
             * 3.[Paint.Style.STROKE] 描边
             */
            style = Paint.Style.FILL

            // 设置描边宽度，仅当画笔样式为描边或填充描边时有效
            strokeWidth = 10f
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 画一个圆
        canvas.drawCircle(200f, 200f, 100f, mPaint)
    }
}