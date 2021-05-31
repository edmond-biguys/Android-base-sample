package com.xmcc.androidbasesample.device.bluetooth

/**
 * Created by caoj on 2021/5/30.
 */
object RunningData {
    var doorStatus = -1
    var speed: Double = -1.0
    var hasPerson = -1
    var floorNum = -1
    var floorAlias = "--"
    fun getDoorAlias(): String {
        return when(doorStatus) {
            1 -> "开门"
            2 -> "关门"
            3 -> "开门中"
            4 -> "关门中"
            else -> "未知"
        }
    }
    /*
    int DOOR_STATUS_OPEN    = 1;
    int DOOR_STATUS_CLOSE   = 2;
    int DOOR_STATUS_OPENING = 3;
    int DOOR_STATUS_CLOSING = 4;
     */
}