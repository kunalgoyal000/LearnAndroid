package com.kunal.learnandroid.connectivity.bluetooth.domain.chat

typealias BluetoothDeviceDomain = BluetoothDevice

data class BluetoothDevice(
    val name: String?,
    val address: String
)
