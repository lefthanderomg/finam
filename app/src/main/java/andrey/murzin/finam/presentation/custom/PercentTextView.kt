package andrey.murzin.finam.presentation.custom

import andrey.murzin.finam.R
import andrey.murzin.finam.presentation.model.ParamItem
import andrey.murzin.finam.presentation.model.SateParamType
import andrey.murzin.finam.utils.toNumberWithSymbol
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView

class PercentTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatTextView(context, attrs, defStyleAttr) {

    companion object {
        private const val DURATION = 1000L
    }

    private val paint = Paint().apply {
        style = Paint.Style.FILL
        isAntiAlias = true
    }
    private val rectF = RectF()

    private val radius = resources.getDimension(R.dimen.radius)

    private var currentState: SateParamType = SateParamType.INIT
    private var currentValue: Double? = null

    override fun onDraw(canvas: Canvas) {
        when (currentState) {
            SateParamType.INIT -> {
                currentValue?.let {
                    if (it > 0.0) {
                        setTextColor(Color.GREEN)
                    } else {
                        setTextColor(Color.RED)
                    }
                } ?: setTextColor(Color.BLACK)
            }
            SateParamType.FALL -> {
                paint.color = Color.RED
                setTextColor(Color.WHITE)

                canvas.drawRoundRect(
                    rectF,
                    radius,
                    radius,
                    paint
                )
            }
            SateParamType.RISE -> {
                paint.color = Color.GREEN
                setTextColor(Color.WHITE)

                canvas.drawRoundRect(
                    rectF,
                    radius,
                    radius,
                    paint
                )
            }
        }
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        rectF.set(
            0F,
            0f,
            width.toFloat(),
            height.toFloat()
        )
    }

    fun setData(paramItem: ParamItem?) {
        paramItem?.data?.let {
            val currentData = "${it.toNumberWithSymbol()}%"
            text = currentData
            currentValue = it
            currentState = paramItem.state

            invalidate()
            postDelayed({
                currentState = SateParamType.INIT
                invalidate()
            }, DURATION)
        }
    }
}