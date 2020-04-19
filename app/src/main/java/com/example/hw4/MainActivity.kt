package com.example.hw4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity(), NewsListAdapter.FragmentButtonListener,
    LikedListAdapter.FragmentLikeListener {
    private var pager: LockableViewPager? = null
    private var toolbar: Toolbar? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var list: ArrayList<Fragment> = ArrayList()
    private var pagefragment = PageFragment()
    private var fragment = FragmentLike()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var singleton = Singleton.create()
        var newsList = Singleton.newsGenerator()
        bindViews()
        inits()
        botNav()
    }

    private fun bindViews() {
        toolbar = findViewById(R.id.toolbar) //toolbar
        setSupportActionBar(toolbar)
        pager = findViewById(R.id.pager) //find pager
        bottomNavigationView =
            findViewById(R.id.bottom_navigation) //bottomnavigation
    }

    private fun inits() {
        list.add(pagefragment) //adding fragments to list
        list.add(fragment)
        pager?.setSwipable(false)
        val pagerAdapter =
            CustomPagerAdapter(supportFragmentManager, list) //adapter for pager
        pager?.adapter = pagerAdapter

    }

    private fun botNav() {
        bottomNavigationView?.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    pager?.setCurrentItem(0, false)
                    bottomNavigationView?.menu?.findItem(R.id.navigation_home)
                        ?.setIcon(R.drawable.ic_home)
                    bottomNavigationView?.menu?.findItem(R.id.navigation_likes)
                        ?.setIcon(R.drawable.ic_heart)
                }
                R.id.navigation_likes -> {
                    pager?.setCurrentItem(1, false)
                    item.setIcon(R.drawable.ic_favorite)
                }
            }
            false
        }
    }

    override fun myClick(news: News?, option: Int) {
        if (option == 1) fragment.saveNews(news) else fragment.removeNews(news)
    }

    override fun removeItemLike(news: News?) {
        pagefragment.removeLike(news)
        fragment.removeLike(news)

    }
}
