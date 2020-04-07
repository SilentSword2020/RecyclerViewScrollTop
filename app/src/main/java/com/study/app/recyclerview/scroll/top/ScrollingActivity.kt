package com.study.app.recyclerview.scroll.top

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import kotlinx.android.synthetic.main.activity_scrolling.*

class ScrollingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scrolling)
        setSupportActionBar(toolbar)

        //TODO 这里要折叠，不然RecyclerView滚动不到最后一项：总是滚动到对应位置-2的位置
        app_bar.setExpanded(false)
        fab.setOnClickListener { _ ->
            //滚动到顶部
            //scrollToTop()

            //平滑滚动到指定位置，并置顶
            //TODO 没有问题:后面还有69个item，有滚动距离
            //smoothScrollToPosition(30)

            // TODO 如果下面没有滚动的距离了，不能置顶
            // TODO 最后一项有点问题：下面没有滚动的距离了，不能置顶
            smoothScrollToPosition(99)

            //闪到指定位置（不是平滑的）
            //(rv_list.layoutManager as? LinearLayoutManager)?.scrollToPositionWithOffset(99, 0)

            //这个只能使用一次，要把标志位重置，才能重新使用
            //(rv_list.layoutManager as? LinearLayoutManager)?.stackFromEnd = true
        }

        initView()
    }


    /**
     * 滚动到顶部
     */
    private fun scrollToTop() {
        smoothScrollToPosition(0)
    }

    /**
     * TODO 平滑滚动到指定位置，并置顶
     */
    private fun smoothScrollToPosition(position: Int) {
        val scroller = TopSmoothScroller(this)
        scroller.targetPosition = position
        rv_list.layoutManager?.startSmoothScroll(scroller)
    }

    private fun initView() {
        rv_list?.apply {
            layoutManager = LinearLayoutManager(this@ScrollingActivity, LinearLayoutManager.VERTICAL, false)
            adapter = object : Adapter<ViewHolder>() {
                override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                    return ViewHolder(layoutInflater.inflate(R.layout.item_list, parent, false))
                }

                override fun getItemCount(): Int = 100

                override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                    holder.TvContent?.apply {
                        text = "list  : $position"
                    }
                }
            }
        }
    }


    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        public var TvContent: TextView?

        init {
            TvContent = view.findViewById<TextView>(R.id.tv_content);
        }
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_scrolling, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
