package com.xmcc.androidbasesample.fragment.navigation.deeplink

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.*
import androidx.navigation.fragment.findNavController
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.FragmentFirst2Binding
import com.xmcc.androidbasesample.fragment.navigation.FragmentNavigationUseActivity
import java.util.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class First2Fragment : Fragment() {

    private lateinit var binding: FragmentFirst2Binding
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirst2Binding.inflate(inflater, container, false)
        createNotificationChannel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            println("caoj onViewCreated ${it.getInt("orderId")}")
        }
        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_first2Fragment_to_second2Fragment)
            findNavController().navigateUp()
            findNavController().popBackStack()
        }

        binding.buttonSendNotification.setOnClickListener {

            val controller = Navigation.findNavController(it)
            val clazz = Class.forName(NavController::class.java.name)
            val method = clazz.getDeclaredMethod("getBackStack")
            println("caoj ${(method.invoke(controller) as Deque<NavBackStackEntry>).size}")


            val intent = Intent(requireContext(), DeepLinkActivity::class.java)
            val pendingIntent1: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)

            val pendingIntent: PendingIntent = NavDeepLinkBuilder(requireActivity())
                .setGraph(R.navigation.nav_graph)
                .setDestination(R.id.secondFragment)
                .setArguments(bundleOf("orderId" to 11))
                .setComponentName(FragmentNavigationUseActivity::class.java)
                .createPendingIntent()

            val builder = NotificationCompat.Builder(requireContext(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("开学了")
                .setContentText("小朋友开学了，请注意")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(requireContext())) {
                notify(1, builder.build())
            }


        }

        binding.buttonImplicit.setOnClickListener {
            try {
                val intent = Intent("android.intent.action.VIEW")
                intent.data = Uri.parse("https://test.abc/")
                intent.`package` = "com.xmcc.androidbasesample.free.debug"
                startActivity(intent)

//                println("caoj button implicit")
//                val request = NavDeepLinkRequest.Builder
//                        .fromUri("https://second.abc".toUri())
//                        .build()
//                findNavController().navigate(request)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "channel-name"
            val descriptionText = "descriptionText"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}