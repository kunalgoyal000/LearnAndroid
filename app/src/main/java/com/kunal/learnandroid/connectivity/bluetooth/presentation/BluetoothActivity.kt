package com.kunal.learnandroid.connectivity.bluetooth.presentation

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.kunal.learnandroid.connectivity.bluetooth.presentation.components.DeviceScreen
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BluetoothActivity : ComponentActivity() {

    private var bluetoothManager: BluetoothManager? = null
    private var bluetoothAdapter: BluetoothAdapter? = null
    private var isBluetoothEnabled: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            bluetoothManager = applicationContext.getSystemService(BluetoothManager::class.java)
        } else {
            bluetoothAdapter =
                (applicationContext.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager).adapter
        }

        if (bluetoothManager != null) {
            bluetoothAdapter = bluetoothManager?.adapter
        }

        if (bluetoothAdapter == null) {
            // Device doesn't support Bluetooth
            Toast.makeText(
                applicationContext,
                "Bluetooth is not supported on this device",
                Toast.LENGTH_LONG
            ).show()
            finish()
        } else {
            isBluetoothEnabled = bluetoothAdapter?.isEnabled == true
        }


        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                //bluetooth enable permission is approved
                Toast.makeText(applicationContext, "Bluetooth is enabled", Toast.LENGTH_LONG)
                    .show()
            } else {
                //bluetooth enable permission is denied
                Toast.makeText(
                    applicationContext,
                    "Bluetooth is not enabled",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
            )
        } else {
            if (!isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        setContent {
            LearnAndroidTheme {
                val viewModel = hiltViewModel<BluetoothViewModel>()
                val state by viewModel.state.collectAsState()

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    DeviceScreen(
                        state = state,
                        onStartScan = viewModel::startScan,
                        onStopScan = viewModel::stopScan
                    )
                }
            }
        }
    }
}