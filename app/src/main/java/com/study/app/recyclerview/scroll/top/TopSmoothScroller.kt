package com.study.app.recyclerview.scroll.top

import android.content.Context
import androidx.recyclerview.widget.LinearSmoothScroller

/**
 * 对应位置的item滚动到置顶
 */
class TopSmoothScroller(context: Context?) : LinearSmoothScroller(context) {
    override fun getHorizontalSnapPreference(): Int {
        return SNAP_TO_START
    }

    override fun getVerticalSnapPreference(): Int {
        return SNAP_TO_START
    }
}