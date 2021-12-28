package com.xmcc.androidbasesample.fragment.navigation

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentFirstBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("FirstFragment onCreate")
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentFirstBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        println("FirstFragment onCreateView")
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("FirstFragment onViewCreated")
        binding.buttonNavigateToSecondFragment.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_secondFragment)
        }

        binding.buttonToNavigationTest.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_testNavigationActivity)
            val request = NavDeepLinkRequest.Builder
                    .fromUri("https://abcd.com".toUri())
                    .setAction("android.intent.action.VIEW")
                    .build()
            findNavController().navigate(request)
//            findNavController().navigateUp()
//            findNavController().popBackStack()
        }

        binding.buttonToSettingsFragment.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_firstFragment_to_settingsFragment)
            /*
                Fragment.findNavController()
                View.findNavController()
                Activity.findNavController(viewId: Int)
             */
        }

        binding.buttonToNavigationViewBinding.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_viewBindingTestActivity)
        }

        binding.buttonToNavigationInDialogFragment.setOnClickListener {
//            findNavController().navigate(R.id.action_firstFragment_to_navigationDialogFragment)
            findNavController().navigate(R.id.action_firstFragment_to_my_dialog_fragment)
        }

        binding.buttonToSchemeActivity.setOnClickListener {
            findNavController().navigate(R.id.action_firstFragment_to_testSchemeActivity)
        }

        binding.buttonToDynamicSchemeActivity.setOnClickListener {
            findNavController().navigate(
                R.id.action_firstFragment_to_testDynamicSchemeActivity,
                bundleOf("userId" to "abcd1234")
                )
        }
    }

    override fun onPause() {
        super.onPause()
        println("FirstFragment onPause")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        println("FirstFragment onAttach")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("FirstFragment onActivityCreated")
    }

    override fun onDetach() {
        super.onDetach()
        println("FirstFragment onDetach")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        println("FirstFragment onDestroyView")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("FirstFragment onDestroy")
    }

    override fun onAttachFragment(childFragment: Fragment) {
        super.onAttachFragment(childFragment)
        println("FirstFragment onAttachFragment")
    }

    override fun onResume() {
        super.onResume()
        println("FirstFragment onResume")
    }

    override fun onStart() {
        super.onStart()
        println("FirstFragment onStart")
    }

    override fun onStop() {
        super.onStop()
        println("FirstFragment onStop")
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}