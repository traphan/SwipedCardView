package com.traphan

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.traphan.recycler.PagerSnapHelper
import com.traphan.recycler.RecyclerItemClickListener
import com.traphan.recycler.RecyclerSnapItemListener
import com.traphan.recycler.RecyclerViewPaginator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerItemClickListener.OnRecyclerViewItemClickListener {


    override fun onItemClick(parentView: View, childView: View, position: Int) {

    }

    private lateinit var itemListAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemListAdapter = ItemListAdapter(this, 30)
        recycler_view.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recycler_view.adapter = itemListAdapter
        recycler_view.addOnItemTouchListener(
            RecyclerItemClickListener(
                this,
                this
            )
        )
        val startSnapHelper =
            PagerSnapHelper(object : RecyclerSnapItemListener {
                override fun onItemSnap(position: Int) {
                    background.setBackgroundColor(Color.GREEN)
                }
            })
                startSnapHelper.attachToRecyclerView(recycler_view)
        recycler_view.addOnScrollListener(object : RecyclerViewPaginator(recycler_view) {
            override fun isLastPage(): Boolean {
                return true
            }


            override fun loadMore(page: Long?) {

            }

            override fun loadFirstData(page: Long?) {

            }
        })
    }
}
