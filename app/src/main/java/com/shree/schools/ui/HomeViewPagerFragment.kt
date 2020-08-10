/*
 * Copyright 2020 Shreedhar Acharya
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shree.schools.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.shree.schools.R
import com.shree.schools.adapters.FAV_SCHOOL_LIST_PAGE_INDEX
import com.shree.schools.adapters.SCHOOL_LIST_PAGE_INDEX
import com.shree.schools.adapters.SchoolPagerAdapter
import com.shree.schools.databinding.FragmentViewPagerBinding

class HomeViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewpager: ViewPager2 = binding.viewPager
        viewpager.adapter = SchoolPagerAdapter(this)

        viewpager.currentItem = getCurrentItem()

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewpager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()


        return binding.root
    }

    private fun getCurrentItem(): Int {
        return arguments?.getInt("tabPosition") ?: FAV_SCHOOL_LIST_PAGE_INDEX
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            FAV_SCHOOL_LIST_PAGE_INDEX -> getString(R.string.menu_fav)
            SCHOOL_LIST_PAGE_INDEX -> getString(R.string.menu_school)
            else -> null
        }
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            FAV_SCHOOL_LIST_PAGE_INDEX -> R.drawable.fav_school_tab_selector
            SCHOOL_LIST_PAGE_INDEX -> R.drawable.school_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }
}