package com.xmcc.androidbasesample.fragment.navigation.deeplink

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkBuilder
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.xmcc.androidbasesample.R
import kotlinx.android.synthetic.main.fragment_second2.*
import java.util.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class Second2Fragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().popBackStack()
        }

        buttonSendNotification.setOnClickListener {
            val controller = Navigation.findNavController(it)
            val clazz = Class.forName(NavController::class.java.name)
            val method = clazz.getDeclaredMethod("getBackStack")
            println("caoj ${(method.invoke(controller) as Deque<NavBackStackEntry>).size}")

            val pendingIntent: PendingIntent = NavDeepLinkBuilder(requireActivity())
                .setGraph(R.navigation.nav_deeplink)
                .setDestination(R.id.second2Fragment)
                .createPendingIntent()

            val intent = Intent(requireContext(), DeepLinkActivity::class.java)
            val pendingIntent1: PendingIntent = PendingIntent.getActivity(requireContext(), 0, intent, 0)

            val builder = NotificationCompat.Builder(requireContext(), "CHANNEL_ID")
                .setSmallIcon(R.drawable.ic_notifications_black_24dp)
                .setContentTitle("开学了")
                .setContentText("小朋友开学了，请注意")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

            with(NotificationManagerCompat.from(requireContext())) {
                notify(1, builder.build())
            }
        }
    }
}