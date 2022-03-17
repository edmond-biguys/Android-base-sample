package com.cym.housedecoration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.cym.housedecoration.detail.DecorativeMaterialDetailActivity
import com.cym.housedecoration.detail.GoToDetailResultContract
import com.google.android.material.tabs.TabLayoutMediator
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.ActivityHouseDecorationListBinding

class HouseDecorationListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHouseDecorationListBinding
    private val viewModel by viewModels<HouseDecorationListViewModel>()

    private val goToDetailLauncher = registerForActivityResult(GoToDetailResultContract()) {
        println("output $it")
    }

    private val goToDetailLauncher02 = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHouseDecorationListBinding.inflate(layoutInflater)
        bindViewModel()
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        val adapter = MyFragmentStateAdapter(this)
        val fragmentList: MutableList<Fragment> = arrayListOf()

        fragmentList.add(DecorationItemFragment(goToDetailLauncher))
        fragmentList.add(DecorationItemFragment(goToDetailLauncher))

        adapter.fragmentLists = fragmentList
        binding.viewPager2.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) { tablayout, position ->
            tablayout.text = viewModel.TAB_DATAS[position]
        }.attach()

        binding.toolbar.setOnMenuItemClickListener {
            goToDetailLauncher.launch("input01")

            val intent = Intent(this, DecorativeMaterialDetailActivity::class.java)
            goToDetailLauncher02.launch(intent)
            return@setOnMenuItemClickListener true
        }
    }

    private fun bindViewModel() {
        viewModel.decorativeMaterialListLiveData.observe(this) {

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