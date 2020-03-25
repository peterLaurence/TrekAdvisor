package com.peterlaurence.trekme.ui.mapview.components

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.peterlaurence.mapview.ReferentialData
import com.peterlaurence.mapview.ReferentialOwner
import com.peterlaurence.trekme.R
import kotlin.math.max

class CompassView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null,
                                            defStyleAttr: Int = 0) : FloatingActionButton(context, attrs, defStyleAttr), ReferentialOwner {

    private val compass = context.getDrawable(R.drawable.compass)!!
    private val squareDim = max(compass.intrinsicWidth, compass.intrinsicHeight)
    private var bitmap: Bitmap? = null
    private val defaultPaint = Paint().apply {
        isAntiAlias = true
        isFilterBitmap = true
    }

    init {
        prepareBitmap()
    }

    private fun prepareBitmap() {
        /* Draw the vector drawable into a square bitmap */
        bitmap = Bitmap.createBitmap(squareDim, squareDim, Bitmap.Config.ARGB_8888).also {
            val c = Canvas(it)
            compass.bounds = Rect(squareDim / 2 - compass.intrinsicWidth / 2,
                    squareDim / 2 - compass.intrinsicHeight / 2,
                    squareDim / 2 + compass.intrinsicWidth / 2,
                    squareDim / 2 + compass.intrinsicHeight / 2)
            compass.draw(c)
        }
    }

    override var referentialData: ReferentialData = ReferentialData(false, 0f, 1f, 0.0, 0.0)
        set(value) {
            field = value
            invalidate()
        }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (referentialData.rotationEnabled) {
            canvas?.rotate(referentialData.angle, width / 2f, height / 2f)
        }

        bitmap?.also {
            canvas?.drawBitmap(it, width / 2f - squareDim / 2f, height / 2f - squareDim / 2f, defaultPaint)
        }
    }
}