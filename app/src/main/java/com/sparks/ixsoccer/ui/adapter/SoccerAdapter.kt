package com.sparks.ixsoccer.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sparks.ixsoccer.ui.fragment.FixturesFragment
import com.sparks.ixsoccer.ui.fragment.ResultsFragment

class SoccerAdapter(
    fm: FragmentManager,
    var totalTabs: Int
) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                FixturesFragment()
            }
            1 -> {
                ResultsFragment()
            }
            else -> getItem(position)
        }
    }
    override fun getCount(): Int {
        return totalTabs
    }
}
