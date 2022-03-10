package com.cym.housedecoration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.ActivityResultRegistry
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cym.housedecoration.detail.DecorativeMaterialDetailActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityHouseDecorationListBinding

class HouseDecorationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHouseDecorationListBinding
    private val viewModel by viewModels<HouseDecorationListViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseDecorationListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapter = MyFragmentStateAdapter(this)
        val fragmentList: MutableList<Fragment> = arrayListOf()

        fragmentList.add(DecorationItemFragment())
        fragmentList.add(DecorationItemFragment())

        adapter.fragmentLists = fragmentList
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tablayout, position ->
            tablayout.text = viewModel.TAB_DATAS[position]
        }.attach()


        val startForResult = object : ActivityResultRegistry() {
            override fun <I : Any?, O : Any?> onLaunch(
                requestCode: Int,
                contract: ActivityResultContract<I, O>,
                input: I,
                options: ActivityOptionsCompat?
            ) {
                println("caoj onLaunch")
            }

        }

        binding.toolbar.setOnMenuItemClickListener {
            DecorativeMaterialDetailActivity.startDecorativeMaterialDetailActivity(
                this, Intent(this, DecorativeMaterialDetailActivity::class.java)
            )
//            registerForActivityResult()
//            registerForActivityResult(ActivityResultContracts.GetContent, startForResult)
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//                println("caoj resultCode ${it.resultCode}")
//            }.launch(Intent(this, DecorativeMaterialDetailActivity::class.java))
            return@setOnMenuItemClickListener true
        }
        


    }

    class MyFragmentStateAdapter(fragmentActivity: FragmentActivity):
        FragmentStateAdapter(fragmentActivity){
        var fragmentLists: MutableList<Fragment> = arrayListOf()
        override fun getItemCount(): Int = fragmentLists.size

        override fun createFragment(position: Int): Fragment = fragmentLists[position]

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_house_decoration, menu)
        return super.onCreateOptionsMenu(menu)
    }


}