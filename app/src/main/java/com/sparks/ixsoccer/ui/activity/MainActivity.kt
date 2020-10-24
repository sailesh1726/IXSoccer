package com.sparks.ixsoccer.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.sparks.ixsoccer.R
import com.sparks.ixsoccer.ui.adapter.SoccerAdapter
import com.sparks.ixsoccer.util.InjectorUtils
import com.sparks.ixsoccer.util.XISoccerUtils
import com.sparks.ixsoccer.viewmodel.SoccerViewModel

class MainActivity : AppCompatActivity() {
    lateinit var tabLayout: TabLayout
    lateinit var viewPager: ViewPager
    private lateinit var viewModel: SoccerViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabLayout = findViewById(R.id.tabLayout)
        viewPager = findViewById(R.id.viewPager)
        tabLayout.addTab(tabLayout.newTab().setText(XISoccerUtils.FIXTURES))
        tabLayout.addTab(tabLayout.newTab().setText(XISoccerUtils.RESULTS))
        tabLayout.tabGravity = TabLayout.GRAVITY_FILL

        val factory = InjectorUtils.provideSoccerViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(SoccerViewModel::class.java)


        val adapter = SoccerAdapter(
            supportFragmentManager,
            tabLayout.tabCount
        )

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }
}