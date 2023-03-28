package com.bqliang.drawbasics.basicshapes

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class RectangleView : View {
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
    private val mRectF = RectF()
    private var mX = -1f
    private var mY = -1f

    init {
        mPaint.color = Color.RED
        mRectF.set(100f, 100f, 600f, 400f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.style = Paint.Style.FILL
        mPaint.color = if (mRectF.contains(mX, mY)) Color.RED else Color.GREEN
        canvas.drawRect(mRectF, mPaint)

        /**
         * 判断是否包含
         * 1.Rect
         *  1.1 Rect.contains(x: Int, y: Int)
         *  1.2 Rect.contains(r: Rect)
         *  1.3 Rect.contains(left: Int, top: Int, right: Int, bottom: Int)
         *  1.4 Rect.contains(p: Point)
         *
         * 2.RectF
         * 2.1 RectF.contains(x: Float, y: Float)
         * 2.2 RectF.contains(r: RectF)
         * 2.3 RectF.contains(left: Float, top: Float, right: Float, bottom: Float)
         * 2.4 RectF.contains(p: PointF)
         */


        /**
         * 判断是否相交
         * 1.静态方法，判断两个矩形是否相交
         *  1.1 Rect.intersects(a: Rect, b: Rect): Boolean
         *  1.2 RectF.intersects(a: RectF, b: RectF): Boolean
         *
         * 2.成员方法，判断当前矩形是否与另一个矩形相交，如果相交，则将相交部分赋值给当前矩形
         *  2.1 Rect.intersect(r: Rect): Boolean
         *  2.2 Rect.intersect(left: Int, top: Int, right: Int, bottom: Int): Boolean
         *  2.3 RectF.intersect(r: RectF): Boolean
         *  2.4 RectF.intersect(left: Float, top: Float, right: Float, bottom: Float): Boolean
         */

        val rect1 = Rect(100, 450, 200, 550)
        val rect2 = Rect(150, 500, 250, 600)
        Log.d("相交前", "rect1: $rect1, rect2: $rect2")
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        mPaint.strokeWidth = 5f
        canvas.drawRect(rect1, mPaint)
        canvas.drawRect(rect2, mPaint)
        rect1.intersect(rect2)
        Log.d("相交后", "rect1: $rect1, rect2: $rect2")
        mPaint.style = Paint.Style.FILL
        canvas.drawRect(rect1, mPaint)

        /**
         * 合并
         * 1.Rect.union(r: Rect): Rect
         * 2.Rect.union(left: Int, top: Int, right: Int, bottom: Int): Rect
         * 3.Rect.union(x: Int, y: Int): Rect
         * 4.RectF.union(r: RectF): RectF
         * 5.RectF.union(left: Float, top: Float, right: Float, bottom: Float): RectF
         * 6.RectF.union(x: Float, y: Float): RectF
         *
         */
        val rect3 = Rect(300, 450, 400, 550)
        mPaint.style = Paint.Style.STROKE
        mPaint.color = Color.RED
        mPaint.strokeWidth = 10F
        canvas.drawRect(rect3, mPaint)
        canvas.drawPoint(450F, 600F, mPaint)
        rect3.union(450, 600)
        mPaint.style = Paint.Style.FILL
        mPaint.color = Color.argb(100, 0, 0, 255)
        canvas.drawRect(rect3, mPaint)

    }


    override fun onTouchEvent(event: MotionEvent): Boolean {
        mX = event.x
        mY = event.y

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                invalidate()
                return true
            }
            MotionEvent.ACTION_UP -> {
                mX = -1f
                mY = -1f
            }
        }
        /**
         * postInvalidate()和 invalidate()函数都是用来重绘控件的，
         * 区别是 invalidate()函数一定要在主线程中执行，否则就会报错；
         * 而 postInvalidate()函数则没有那么多讲究，它可以在任何线程中执行，而不必一定是主线程。
         * 因为在 postInvalidate()函数中就是利用 handler 给主线程发送刷新界面的消息来实现的，
         * 所以它可以在任何线程中执行而不会出错。而正因为它是通过发送消息来实现的，
         * 所以它的界面刷新速度可能没有直接调用 invalidate()函数那么快。
         * 因此，在确定当前线程是主线程的情况下，还是以 invalidate()函数为主。
         * 当不确定当前要刷新界面的位置所处的线程是不是主线程的时候，还是调用 postInvalidate()函数为好；
         * 这里故意调用的是 postInvalidate()函数，
         * 因为 onTouchEvent()函数本来就是在主线程中的，所以使用 invalidate()函数更合适
         */
        postInvalidate()
        return super.onTouchEvent(event)
    }
}