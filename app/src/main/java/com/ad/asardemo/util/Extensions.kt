package com.ad.asardemo.util

import android.text.SpannableStringBuilder
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat

fun TextView.setSpannableTextView(
    fullText: String,
    textToSpan1: String,
    spanTextColor: Int,
    isUnderlineText: Boolean = false,
    onSpan1Click: () -> Unit
) {
    val spannableBuilder = SpannableStringBuilder(fullText)
    val start1Index = fullText.indexOf(textToSpan1)
    if (start1Index != -1) {
        val end1Index = start1Index + textToSpan1.length

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                onSpan1Click.invoke()
            }

            override fun updateDrawState(ds: TextPaint) {
                ds.isUnderlineText = isUnderlineText
            }
        }

        spannableBuilder.setSpan(
            clickableSpan, start1Index, end1Index, SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE
        )

        spannableBuilder.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, spanTextColor)),
            start1Index,
            end1Index,
            SpannableStringBuilder.SPAN_INCLUSIVE_INCLUSIVE
        )


        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = ContextCompat.getColor(context, android.R.color.transparent)
        text = spannableBuilder
    }
}