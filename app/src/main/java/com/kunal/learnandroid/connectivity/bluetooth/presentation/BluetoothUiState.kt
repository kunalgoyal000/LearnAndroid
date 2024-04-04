package com.kunal.learnandroid.connectivity.bluetooth.presentation

import com.kunal.learnandroid.connectivity.bluetooth.domain.chat.BluetoothDeviceDomain

data class BluetoothUiState(
    val scannedDevices: List<BluetoothDeviceDomain> = emptyList(),
    val pairedDevices: List<BluetoothDeviceDomain> = emptyList()
)
