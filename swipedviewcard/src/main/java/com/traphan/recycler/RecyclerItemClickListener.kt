package com.traphan.recycler

import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerItemClickListener(context: Context, recyclerViewItemClickListener: OnRecyclerViewItemClickListener): RecyclerView.OnItemTouchListener {

    private val gestureDetector: GestureDetector = GestureDetector(context,
        object : GestureDetector.SimpleOnGestureListener() {
            override fun onSingleTapUp(e: MotionEvent): Boolean {
                return true
            }
        })
    private val recyclerViewItemClickListener: OnRecyclerViewItemClickListener = recyclerViewItemClickListener


    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {

    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        var childView =  rv.findChildViewUnder(e.x, e.y)
        if (childView != null && recyclerViewItemClickListener != null && gestureDetector.onTouchEvent(e)) {
            recyclerViewItemClickListener.onItemClick(rv, childView, rv.getChildLayoutPosition(childView))
        }
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {

    }

    interface OnRecyclerViewItemClickListener {
        fun onItemClick(parentView: View, childView: View, position: Int)
    }
}