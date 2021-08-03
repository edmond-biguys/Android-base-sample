package com.xmcc.androidbasesample.fragment.navigation.nestedgraph.ordernode

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavBackStackEntry
import androidx.navigation.fragment.findNavController
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentOrderRatingBinding
import java.util.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OrderRatingFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OrderRatingFragment : Fragment() {
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

    private lateinit var binding: FragmentOrderRatingBinding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
//        com.xmcc.androidbasesample.databinding.FragmentOrderRatingBinding.
        // Inflate the layout for this fragment
        binding = FragmentOrderRatingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUpto.setOnClickListener {
            val controller1 = findNavController()
            println("backStack ${findNavController()}")
            findNavController().navigateUp()
            findNavController().navigate(R.id.orderRatingFragmentPop)
            val controller = findNavController()
            println("backStack ${findNavController()}")
        }

        binding.buttonPopStack.setOnClickListener {
            val controller1 = findNavController()
            println("backStack ${findNavController()}")
            findNavController().popBackStack()
            val controller = findNavController()
            println("backStack ${findNavController()}")
            val mBackStack: Deque<NavBackStackEntry> = ArrayDeque()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OrderRatingFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OrderRatingFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}