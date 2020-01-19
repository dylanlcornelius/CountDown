package com.example.countdown

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TabAdapter
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    /**
     * sets up tabs
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)
        tabLayout = findViewById(R.id.tabLayout)

        adapter = TabAdapter(supportFragmentManager)
        adapter.addFragment(TabCountDownFragment(), "Count Down")
        adapter.addFragment(TabCountUpFragment(), "Count Up")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
    }
}
