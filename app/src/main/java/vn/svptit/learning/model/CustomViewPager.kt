package vn.svptit.learning.model

import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.MotionEvent

/**
 * Created by NVQuang on 27/12/2017.
 */
class CustomViewPager(context: Context?, attrs: AttributeSet?) : ViewPager(context, attrs) {
    var enable = true

    override fun onTouchEvent(ev: MotionEvent?): Boolean {
        if (this.enable) {
            return super.onTouchEvent(ev)
        }
        return false
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        if (this.enable) {
            return super.onInterceptTouchEvent(ev)
        }
        return false
    }

    fun setPagingEnable(enable: Boolean) {
        this.enable = enable
    }
}