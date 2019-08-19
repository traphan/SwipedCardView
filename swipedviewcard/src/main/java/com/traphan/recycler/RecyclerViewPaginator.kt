package com.traphan.recycler

import androidx.annotation.NonNull
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE

abstract class RecyclerViewPaginator(recyclerView: RecyclerView): RecyclerView.OnScrollListener() {

    private var currentPage: Long? = 1L
    private val threshold = 2
    private var endWithAuto = false
    private val recyclerView: RecyclerView = recyclerView
    private lateinit var layoutManager: RecyclerView.LayoutManager


    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager!!
        loadFirstData(currentPage)
    }

    override fun onScrollStateChanged(@NonNull recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == SCROLL_STATE_IDLE) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount

            var firstVisibleItemPosition = 0
            if (layoutManager is LinearLayoutManager) {
                firstVisibleItemPosition = (layoutManager as LinearLayoutManager).findLastVisibleItemPosition()

            } else if (layoutManager is GridLayoutManager) {
                firstVisibleItemPosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
            }

            if (isLastPage()) return

            if (visibleItemCount + firstVisibleItemPosition + threshold >= totalItemCount) {
                if (!endWithAuto) {
                    endWithAuto = true
                    loadMore(currentPage?.plus(1))
                }
            } else {
                endWithAuto = false
            }
        }
    }

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
    }

    abstract fun isLastPage(): Boolean
    abstract fun loadMore(page: Long?)
    abstract fun loadFirstData(page: Long?)
}