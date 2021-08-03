package com.xmcc.androidbasesample.fragment.navigation.nestedgraph.ordernode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentOrderListBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderListFragment : BaseFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//
//        return inflater.inflate(R.layout.fragment_order_list, container, false)
//    }

    override fun initView() {
        val b = binding as FragmentOrderListBinding
        b.buttonToDetail.setOnClickListener {
            println("button to detail")

//            findNavController().navigate(R.id.action_orderListFragment_to_orderDetailFragment)
            findNavController().navigate(R.id.action_orderListFragment_to_orderDetailFragment, bundleOf(
                    "orderId" to 10,
                    "orderCode" to "abcd"
            ))
            //action_orderListFragment_to_orderDetailFragment
//            OrderListFragmentDirections
//            OrderDetailFragmentDirections

            val directions = OrderListFragmentDirections.actionOrderListFragmentToOrderDetailFragment()
            directions.orderId = 100
            findNavController().navigate(directions)


            val controller = findNavController()
            println("backStack ${findNavController()}")

        }

        b.buttonPopUp.setOnClickListener {

            val controller1 = findNavController()
            findNavController().navigateUp()
            val controller = findNavController()
            println("backStack ${findNavController()}")
        }

        b.buttonPopStack.setOnClickListener {

            val controller1 = findNavController()
            findNavController().popBackStack()
            val controller = findNavController()
            println("backStack ${findNavController()}")
        }
    }

    override fun getBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentOrderListBinding {
        return FragmentOrderListBinding.inflate(inflater, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}