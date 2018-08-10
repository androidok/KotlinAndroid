package com.gfd.home.ui.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.gfd.common.ext.noScroll
import com.gfd.common.ui.activity.BaseActivity
import com.gfd.home.R
import com.gfd.home.adapter.CategoryPagerAdapter
import com.gfd.home.common.Concant
import com.gfd.home.ext.init
import com.gfd.home.ui.fragment.CategoryFragment
import kotlinx.android.synthetic.main.activity_category.*


/**
 * @Author : 郭富东
 * @Date ：2018/8/8 - 14:53
 * @Email：878749089@qq.com
 * @descriptio：
 */
class CategoryActivity : BaseActivity() {

    private val categoryTitles = arrayOf("抢先看", "电视剧", "电影", "综艺")
    private val mFragments = ArrayList<CategoryFragment>()
    private var currentPosition = 0
    override fun getLayoutId(): Int {
        return R.layout.activity_category
    }

    override fun initView() {
        currentPosition = when (intent.getIntExtra(Concant.CATEGORY, 0)) {
            Concant.CATEGORY_NEW -> 0
            Concant.CATEGORY_DINASHI -> 1
            Concant.CATEGORY_MOVIE -> 2
            Concant.CATEGORY_ZONGYI -> 3
            else -> 0
        }
        initFragment()
        videoPager.noScroll()
        videoPager.offscreenPageLimit = mFragments.size
        videoPager.adapter = CategoryPagerAdapter(mFragments, categoryTitles, supportFragmentManager)
        categoryTabs.setupWithViewPager(videoPager, false)
        videoPager.setCurrentItem(currentPosition, false)
        for (i in 0..categoryTabs.tabCount) {
            val tab = categoryTabs.getTabAt(i)
            tab?.customView = getTabView(i)
        }
        categoryTabs.init()
    }


    /**
     * 获取自定义的tab View
     * @param position Int
     * @return View
     */
    private fun getTabView(position: Int): View {
        val view = LayoutInflater.from(this).inflate(R.layout.layout_tab, null)
        val textView = view.findViewById(R.id.tab_item_textview) as TextView
        textView.text = categoryTitles[position]
        textView.textSize = resources.getDimension(R.dimen.dp_5)
        if (position == currentPosition) {
            textView.textSize = resources.getDimension(R.dimen.dp_6)
            textView.setTextColor(resources.getColor(R.color.colorSearchItemBtn))
            textView.paint.isFakeBoldText = true
        }
        return view
    }


    /** 创建Fragment*/
    private fun initFragment() {
        //抢先看
        val newFragment = CategoryFragment()
        val new = Bundle()
        new.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_NEW)
        newFragment.arguments = new
        mFragments.add(newFragment)
        //电视剧
        val dxFragment = CategoryFragment()
        val dx = Bundle()
        dx.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_DINASHI)
        dxFragment.arguments = dx
        mFragments.add(dxFragment)
        //电影
        val dyFragment = CategoryFragment()
        val dy = Bundle()
        dy.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_MOVIE)
        dyFragment.arguments = dy
        mFragments.add(dyFragment)
        //综艺
        val zywFragment = CategoryFragment()
        val zy = Bundle()
        zy.putInt(CategoryFragment.CATEGORY, CategoryFragment.CATEGORY_ZONGYI)
        zywFragment.arguments = zy
        mFragments.add(zywFragment)
    }

    override fun initData() {
    }

}