package com.rr.tvstarterkit.util

import android.graphics.Color
import android.os.Build
import android.text.Html
import android.text.SpannableString
import android.text.Spanned
import android.widget.ImageView
import androidx.annotation.ColorInt
import java.text.SimpleDateFormat
import java.util.*

private val TAG: String = TvUtils::class.java.simpleName

class TvUtils {

    companion object {

        fun convertDate(dateInMilliseconds: Long): String {
            val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            var dateString = format.format(dateInMilliseconds)
            return dateString
        }

        /**
         * function to adjust color alpha
         */
        fun adjustAlpha(@ColorInt color: Int, factor: Float): Int {
            val alpha = Math.round(Color.alpha(color) * factor)
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)
            return Color.argb(alpha, red, green, blue)
        }

        fun capitalizeFirstLetterOfWords(str: String): String {
            var result = ""
            str.split(" ").forEach {
                result += it.substring(0, 1).uppercase(Locale.getDefault()) + it.substring(
                    1,
                    it.length
                )
                    .lowercase(Locale.getDefault()) + " "
            }
            return result
        }

        /**
         * Get Hours and minutes
         */
        fun getHrsMins(seconds: Long): String {
            val minutes = seconds / 60
            val hours = minutes / 60
            return when {
                minutes == 0L -> "${seconds}s"
                hours == 0L -> "${minutes}m"
                else -> "${hours}h${(minutes % 60)}m"
            }
        }

        /**
         * function to format HTML text
         */
        fun fromHtml(textString: String?): Spanned {
            return when {
                textString == null -> // return an empty spannable if the html is null
                    SpannableString("")
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> // FROM_HTML_MODE_LEGACY is the behaviour that was used for versions below android N
                    // we are using this flag to give a consistent behaviour
                    Html.fromHtml(textString, Html.FROM_HTML_MODE_LEGACY)
                else -> Html.fromHtml(textString)
            }
        }

        fun loadImage(imageUrl: String?, imageView: ImageView) {
            imageUrl?.let {
                GlideImageLoader().loadImageFromUrl(
                    imageUrl, imageView
                )
            }
        }


    }

}