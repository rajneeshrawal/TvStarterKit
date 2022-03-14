package com.rr.tvstarterkit.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.rr.tvstarterkit.R

class TvCustomTextView : TextView {
    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init(attrs)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }

    constructor(context: Context) : super(context) {
        init(null)
    }

    @SuppressLint("CustomViewStyleable")
    private fun init(attrs: AttributeSet?) {
        if (attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.CustomTextView)
            /*set text font*/
            val fontName = a.getString(R.styleable.CustomTextView_fontName)
            val customTypeface: Typeface?
            if (fontName != null) {
                customTypeface = when (fontName) {
                    context.getString(R.string.font_regular) -> ResourcesCompat.getFont(
                        context,
                        R.font.montserrat_regular
                    )
                    context.getString(R.string.font_semibold) -> ResourcesCompat.getFont(
                        context,
                        R.font.montserrat_semi_bold
                    )
                    context.getString(R.string.font_medium) -> ResourcesCompat.getFont(
                        context,
                        R.font.montserrat_medium
                    )
                    context.getString(R.string.font_bold) -> ResourcesCompat.getFont(
                        context,
                        R.font.montserrat_bold
                    )

                    else -> ResourcesCompat.getFont(context, R.font.montserrat_regular)
                }

                typeface = customTypeface
            }
            a.recycle()
        }
    }
}
