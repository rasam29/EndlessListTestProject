package com.example.endlessproject.appList.list

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.endlessproject.appList.AppListFragment
import com.example.endlessproject.authentication.ListKey

class TabPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = ListKey.values().size

    override fun createFragment(position: Int): Fragment =
        AppListFragment(ListKey.values()[position])
}