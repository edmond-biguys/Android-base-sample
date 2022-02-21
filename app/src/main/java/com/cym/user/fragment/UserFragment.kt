package com.cym.user.fragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.SimpleAdapter
import androidx.fragment.app.ListFragment
import com.cym.housedecoration.HouseDecorationListActivity
import com.xmcc.androidbasesample.R

class UserFragment : ListFragment() {

    companion object {
        fun newInstance() = UserFragment()
    }

    private lateinit var viewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listAdapter = SimpleAdapter(
            requireContext(), getData(), android.R.layout.simple_list_item_1,
            arrayOf("title"), intArrayOf(android.R.id.text1))

        return inflater.inflate(R.layout.user_fragment, container, false)
    }

    private fun getData(): List<Map<String, Any>> {
        val myData = mutableListOf<Map<String, Any>>()

        myData.add(
            mapOf(
                "title" to "House Decoration List",
             "intent" to  activityToIntent(HouseDecorationListActivity::class.java.name)
            ))

//        myData.add(
//            mapOf(
//                "title" to "123",
//                "intent" to  activityToIntent("")
//            ))

        return myData
    }

    private fun activityToIntent(activity: String): Intent =
        Intent(Intent.ACTION_VIEW).setClassName(requireActivity().packageName, activity)


    override fun onListItemClick(listView: ListView, v: View, position: Int, id: Long) {
        super.onListItemClick(listView, v, position, id)
        println("position $position")
        val map = listView.getItemAtPosition(position) as Map<*, *>
        val intent = Intent(map["intent"] as Intent)
        startActivity(intent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        viewModel.startWorkManager(requireContext())
    }

}