package com.example.hw4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class MainActivity : AppCompatActivity() {
    private var pager: LockableViewPager? = null
    private var toolbar: Toolbar? = null
    private var bottomNavigationView: BottomNavigationView? = null
    private var list: ArrayList<Fragment> = ArrayList()
    private var pagefragment = PageFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var singleton = Singleton.create()
        var newsList = Singleton.newsGenerator()
        bindViews()
        inits()
    }

    private fun bindViews() {
        toolbar = findViewById(R.id.toolbar) //toolbar
        setSupportActionBar(toolbar)
        pager = findViewById(R.id.pager) //find pager
    }

    private fun inits() {
        list.add(pagefragment) //adding fragments to list
        pager?.setSwipable(false)
        val pagerAdapter =
            CustomPagerAdapter(supportFragmentManager, list) //adapter for pager
        pager?.adapter = pagerAdapter

    }


}
