package com.kunal.learnandroid.connectivity.bluetooth.data.chat

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import com.kunal.learnandroid.connectivity.bluetooth.domain.chat.BluetoothDeviceDomain


@SuppressLint("MissingPermission")
fun BluetoothDevice.toBluetoothDeviceDomain(): BluetoothDeviceDomain {
    return BluetoothDeviceDomain(
        name = name,
        address = address
    )
}