package com.bqliang.drawbasics.text

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View

class TextBasicsView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val textPaint = Paint()

    init {
        //设置画笔宽度
        textPaint.strokeWidth = 5F
        //指定是否使用抗锯齿功能。如果使用，则会使绘图速度变慢
        textPaint.isAntiAlias = true
        //绘图样式，对于文字和几何图形都有效
        textPaint.style = Paint.Style.FILL

        // 设置文字对齐方式
        textPaint.textAlign = Paint.Align.LEFT

        // 设置文字大小
        textPaint.textSize = 36F
        //设置是否为粗体文字
        textPaint.isFakeBoldText = true
        // 设置下画线
        textPaint.isUnderlineText = true
        // 设置字体水平倾斜度，普通斜体字设为 -0.25
        textPaint.textSkewX = -0.25f
        // 设置带有删除线效果
        textPaint.isStrikeThruText = true
        // 将文字水平方向拉伸，高度不变
        textPaint.textScaleX = 2f
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawColor(Color.WHITE)

        val defaultTextPaint = Paint().apply {
            textSize = 40f
            isAntiAlias = true
        }

        /**
         * 绘制文字
         * [Canvas.drawText] (text: String, x: Float, y: Float, paint: Paint)
         * [Canvas.drawText] (text: String, start: Int, end: Int, x: Float, y: Float, paint: Paint)
         * [Canvas.drawText] (text: CharArray, index: Int, count: Int, x: Float, y: Float, paint: Paint)
         * [Canvas.drawText] (text: CharSequence, start: Int, end: Int, x: Float, y: Float, paint: Paint)
         */
        canvas.drawText("012345", 1, 3, 50F, 100F, defaultTextPaint)
        canvas.drawText("Hello World", 50F, 160F, defaultTextPaint)
        defaultTextPaint.style = Paint.Style.STROKE
        canvas.drawText("Hello World", 350F, 160F, defaultTextPaint)
        defaultTextPaint.style = Paint.Style.FILL


        // 设置文字对齐方式
        val textAlignPaint = Paint().apply {
            textSize = 40f
            textAlign = Paint.Align.LEFT
            isAntiAlias = true
        }
        canvas.drawText("左对齐", 250F, 220F, textAlignPaint)
        textAlignPaint.textAlign = Paint.Align.RIGHT
        canvas.drawText("右对齐", 250F, 270F, textAlignPaint)
        textAlignPaint.textAlign = Paint.Align.CENTER
        canvas.drawText("居中对齐", 250F, 320F, textAlignPaint)
        textAlignPaint.color = Color.RED
        canvas.drawLine(250F, 180F, 250F, 350F, textAlignPaint)


        // 设置字体样式
        val textStylePaint = Paint().apply {
            textSize = 40f
            isFakeBoldText = true
            isUnderlineText = true
            isStrikeThruText = true
            textSkewX = -0.25f
        }
        canvas.drawText("Hello?", 50F, 400F, textStylePaint)


        // 拉伸文字
        val textScaleXPaint = Paint().apply {
            textSize = 40f
            isAntiAlias = true
            textScaleX = 2f
            color = Color.rgb(61, 220, 132)
        }
        canvas.drawText("Android", 50F, 460F, textScaleXPaint)


        /**
         * 沿路径绘制文字
         * [Canvas.drawTextOnPath] (text: String, path: Path, hOffset: Float, vOffset: Float, paint: Paint)
         * [Canvas.drawTextOnPath] (text: CharArray, index: Int, count: Int, path: Path, hOffset: Float, vOffset: Float, paint: Paint)
         * hOffset: 水平偏移量
         * vOffset: 垂直偏移量
         */
        val pathTextPaint = Paint().apply {
            textSize = 40f
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
        }
        val circlePath = Path()
        circlePath.addCircle(800F, 300F, 100F, Path.Direction.CW)
        canvas.drawTextOnPath("ABCDEFGHIJK", circlePath, 0F, 0F, pathTextPaint)
        pathTextPaint.style = Paint.Style.STROKE
        pathTextPaint.color = Color.RED
        canvas.drawPath(circlePath, pathTextPaint)


        /**
         * 设置字体样式
         * [Paint.setTypeface] (typeface: Typeface)
         *
         * Typeface 自带样式：
         *  1.[Typeface.DEFAULT] 默认
         *  2.[Typeface.DEFAULT_BOLD] 默认粗体
         *  3.[Typeface.SANS_SERIF] 无衬线字体
         *  4.[Typeface.SERIF] 衬线字体
         *  5.[Typeface.MONOSPACE] 等宽字体
         *
         * [Typeface.defaultFromStyle] (style: Int): Typeface
         * 根据传入的 style 参数返回对应的 Typeface 对象，
         * 如果系统默认的字体是宋体，那么，当指定 defaultFromStyle(Typeface.BOLD_ITALIC)时，
         * 获取的将是粗斜体的宋体样式
         * style: 字体样式
         *  1.[Typeface.NORMAL] 正常
         *  2.[Typeface.BOLD] 粗体
         *  3.[Typeface.ITALIC] 斜体
         *  4.[Typeface.BOLD_ITALIC] 粗斜体
         *
         * [Typeface.create] (familyName: String, style: Int): Typeface
         * 直接通过指定字体名来加载系统中自带的字体样式。如果字体样式不存在，则会用系统样式替代并返回
         *
         *
         * [Typeface.createFromFile] (file: File?): Typeface
         * 从指定的文件中加载字体样式
         * [Typeface.createFromFile] (path: String?): Typeface
         * 从指定的文件中加载字体样式
         * [Typeface.createFromAsset] (mgr: AssetManager, path: String): Typeface
         * 从 assets 中加载字体样式
         */
        val typefacePaint = Paint().apply {
            textSize = 48f
            isAntiAlias = true
            color = Color.BLACK
            style = Paint.Style.FILL
        }
        val typeface = Typeface.createFromAsset(context.assets, "NotoSerifSC-Regular.otf")
        typefacePaint.typeface = typeface
        canvas.drawText("人人生而自由", 50F, 600F, typefacePaint)
    }
}