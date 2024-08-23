package com.ad.asardemo.util

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.MotionEvent
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import com.ad.asardemo.R

class SwipeButton @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private lateinit var swipeButton: AppCompatImageView
    private lateinit var swipeText: TextView
    private var initialX = 0f

    init {
        initView()
    }

    private fun initView() {
        setBackgroundResource(R.drawable.bg_button)
        val padding = 0
        setPadding(padding, padding, padding, padding)

        // Initialize the swipe button
        swipeButton = AppCompatImageView(context)
        // swipeButton.setBackgroundColor(Color.BLACK)
        swipeButton.setImageResource(R.drawable.ic_fast_forward)
        swipeButton.setPadding(30, 30, 30, 30)

        // Initialize the swipe text
        swipeText = TextView(context)
        swipeText.text = "Swipe for Yes"
        swipeText.gravity = Gravity.CENTER
        swipeText.textSize = 18f
        swipeText.setTextColor(ContextCompat.getColor(context, R.color.white))

        // Add the text and button to the layout
        addView(swipeText)
        addView(swipeButton)

        // Set layout parameters
        val swipeButtonParams = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.MATCH_PARENT
        )
        swipeButton.layoutParams = swipeButtonParams
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                initialX = swipeButton.x
                return true
            }

            MotionEvent.ACTION_MOVE -> {
                val newX = event.rawX - swipeButton.width / 2
                if (newX > initialX && newX + swipeButton.width < width) {
                    swipeButton.x = newX
                }
                return true
            }

            MotionEvent.ACTION_UP -> {
                if (swipeButton.x + swipeButton.width > width * 0.8) {
                    // Action when the swipe is complete (e.g., triggering an event)
                    swipeButton.x = width - swipeButton.width.toFloat()
                    performSwipeAction()
                } else {
                    // Animate the button back to its original position
                    swipeButton.animate().x(initialX).setDuration(300).start()
                }
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun performSwipeAction() {
        swipeText.text = "Done"
        swipeText.setTextColor(Color.GREEN)
    }
}
