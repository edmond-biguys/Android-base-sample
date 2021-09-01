package com.cym.home.ui.fragment

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cym.base.BaseFragment
import com.xmcc.androidbasesample.R
import com.xmcc.androidbasesample.databinding.HomeFragment2Binding

class HomeFragment : BaseFragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: HomeFragment2Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragment2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(HomeViewModel::class.java)
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        sensorManager.unregisterListener(sensorListener)
    }

    private fun initView() {
//        binding.toolbarHome.setBackgroundColor(resources.getColor(R.color.light_blue_600))
//        binding.toolbarHome.setNavigationIcon(android.R.drawable.arrow_down_float)
        //sensor()
    }

    private lateinit var sensorManager: SensorManager
//    private lateinit var gravity:
    private fun sensor() {
        sensorManager = requireActivity().getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val magneticSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        val accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        sensorManager.registerListener(sensorListener, magneticSensor, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager.registerListener(sensorListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)

        //初始化数组
        floatArrayOf()

    }

//    var gravity =  arrayOfNulls<Float>(3)//用来保存加速度传感器的值 3
//    var r = arrayOfNulls<Float>(9)// 9
//    var geomagnetic = arrayOfNulls<Float>(3)//用来保存地磁传感器的值 3
//    var values = arrayOfNulls<Float>(3)//用来保存最终的结果 3

    var gravity =  floatArrayOf()//用来保存加速度传感器的值 3
    var r = floatArrayOf(0f,0f,0f,
        0f,0f,0f,
        0f,0f,0f)// 9
    var geomagnetic = floatArrayOf()//用来保存地磁传感器的值 3
    var values = floatArrayOf(0f,0f,0f)//用来保存最终的结果 3

    private val sensorListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            println("onSensorChanged")
            try {
                if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                    geomagnetic = event.values
                }
                if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    gravity = event.values
                    getOrientation()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            println("onAccuracyChanged")
        }
    }

    /**
     * 获取手机旋转角度
     */
    fun getOrientation() {
        // r从这里返回
        println("caoj " + SensorManager.getRotationMatrix(r, null, gravity, geomagnetic))
        //values从这里返回
        println(SensorManager.getOrientation(r, values))
        //提取数据
        val degreeZ = Math.toDegrees(values[0].toDouble())
        val degreeX = Math.toDegrees(values[1].toDouble())
        val degreeY = Math.toDegrees(values[2].toDouble())
        println("caoj z: $degreeZ x: $degreeX y: $degreeY")
//        if (listener != null) {
//            listener.onOrientationChange(degreeX, degreeY, degreeZ)
//        }
    }



}