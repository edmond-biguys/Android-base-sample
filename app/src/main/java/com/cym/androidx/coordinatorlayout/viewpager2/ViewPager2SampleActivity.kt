package com.cym.androidx.coordinatorlayout.viewpager2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xmcc.androidbasesample.databinding.ActivityViewpager2Binding
import com.xmcc.androidbasesample.databinding.FragmentHomeViewPagerBinding

class ViewPager2SampleActivity : AppCompatActivity() {

    private lateinit var binding: ActivityViewpager2Binding

    companion object {
        val TITLES = arrayListOf(
            "House Decoration List", "TabLayout test 02"
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpager2Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = MyFragmentStateAdapter(this)
        val fragments: MutableList<Fragment> = arrayListOf()

        fragments.add(MyFragment.create())
        fragments.add(MyFragment.create())
        adapter.fragments = fragments

        binding.pager2.adapter = adapter

        TabLayoutMediator(binding.tbLayout, binding.pager2) { tab, position ->
            tab.text = TITLES[position]
            tab.customView
        }.attach()


    }


    class MyFragmentStateAdapter(fragmentActivity: FragmentActivity): FragmentStateAdapter(fragmentActivity) {

        var fragments: MutableList<Fragment> = arrayListOf()
        override fun getItemCount() = fragments.size

        override fun createFragment(position: Int) = fragments[position]

    }

    class MyFragment: Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val binding = FragmentHomeViewPagerBinding.inflate(inflater, container, false)
            return binding.root
        }

        companion object {
            fun create(): MyFragment {
                val fragment = MyFragment()
                return fragment
            }
        }
    }
}