package com.xinzailingtech.aidlclient

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cym.sample.binder.IMyAidlRemote
import com.xinzailingtech.aidlclient.databinding.FragmentFirstBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {
    companion object {
        const val TAG = "FirstFragment"
    }
    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        binding.buttonBind.setOnClickListener { bind() }
        binding.buttonUnbind.setOnClickListener { unbind() }
        binding.buttonSend.setOnClickListener { send() }
    }

    private var remoteService: IMyAidlRemote? = null
    private val conn: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName?, iBinder: IBinder?) {
            remoteService = IMyAidlRemote.Stub.asInterface(iBinder)
            val uid = remoteService?.uid
            val user = uid?.let { remoteService?.getUser(it) }
            Log.i(TAG, "onServiceConnected: uid $uid user $user")
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            Log.i(TAG, "onServiceDisconnected: ")
            remoteService = null
        }

    }

    private fun bind() {
        Log.i(TAG, "bind: ")
        val intent = Intent()
        intent.action = "com.cym.sample.binder.RemoteService"
        intent.`package` = "com.xmcc.androidbasesample"
        activity?.bindService(intent, conn, Context.BIND_AUTO_CREATE)
    }

    private fun unbind() {
        Log.i(TAG, "unbind: ")
        activity?.unbindService(conn)
    }

    private fun send() {
        val randomUid = Random.nextInt(1, 10)
        Log.i(TAG, "send: $randomUid")
        val user = remoteService?.getUser(randomUid)
        Log.i(TAG, "send: user $user")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}