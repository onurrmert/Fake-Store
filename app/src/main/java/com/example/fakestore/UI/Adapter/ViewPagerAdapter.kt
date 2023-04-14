package com.example.fakestore.UI.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter (
     fragmentManager: FragmentManager,
    ) : FragmentPagerAdapter(fragmentManager)  {

    private val fragmentList = ArrayList<FragmentModel>()

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getItem(position: Int): Fragment {
        return fragmentList.get(position).fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return fragmentList.get(position).title
    }

    fun addFragment(fragment: Fragment, title : String) {
        fragmentList.add(FragmentModel(fragment, title))
    }
}