package com.kunal.learnandroid.connectivity.bluetooth.presentation

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.kunal.learnandroid.connectivity.bluetooth.presentation.components.ChatScreen
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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                checkForBluetoothScanAndConnectPermission()
            } else {
                if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
                    if (hasFineLocationPermission()) {
                        showBluetoothEnablePermissionsDialog()
                    } else {
                        fineLocationPermissionResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    }
                }
            }
        }

//        val permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { perms ->
//            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
//            } else true
//
//            if (canEnableBluetooth && !isBluetoothEnabled) {
//                enableBluetoothLauncher.launch(
//                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                )
//            }
//        }
//
//        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.R) {
//            ActivityCompat.requestPermissions(
//                this,
//                arrayOf(
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ),
//                0
//            )
//        }
//
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
//            permissionLauncher.launch(
//                arrayOf(
//                    Manifest.permission.BLUETOOTH_SCAN,
//                    Manifest.permission.BLUETOOTH_CONNECT
//                )
//            )
//        } else {
//            if (!isBluetoothEnabled) {
//                enableBluetoothLauncher.launch(
//                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
//                )
//            }
//        }

        setContent {
            LearnAndroidTheme {
                val viewModel = hiltViewModel<BluetoothViewModel>()
                val state by viewModel.state.collectAsState()

                LaunchedEffect(key1 = state.errorMessage) {
                    state.errorMessage?.let { error ->
                        Toast.makeText(
                            applicationContext,
                            error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                LaunchedEffect(key1 = state.isConnected) {
                    if (state.isConnected) {
                        Toast.makeText(
                            applicationContext,
                            "You're connected!",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                Surface(
                    color = MaterialTheme.colorScheme.background
                ) {
                    when {
                        state.isConnecting -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.Center,
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                CircularProgressIndicator()
                                Text(text = "Connecting...")
                            }
                        }

                        state.isConnected -> {
                            ChatScreen(
                                state = state,
                                onDisconnect = viewModel::disconnectFromDevice,
                                onSendMessage = viewModel::sendMessage
                            )
                        }

                        else -> {
                            DeviceScreen(
                                state = state,
                                onStartScan = viewModel::startScan,
                                onStopScan = viewModel::stopScan,
                                onDeviceClick = viewModel::connectToDevice,
                                onStartServer = viewModel::waitForIncomingConnections
                            )
                        }
                    }
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.S)
    private fun checkForBluetoothScanAndConnectPermission() {
        if (hasBluetoothScanAndConnectPermission()) {
            showBluetoothEnablePermissionsDialog()
        } else {
            requestMultiplePermissionsLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT
                )
            )
        }
    }

    private val fineLocationPermissionResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { it ->
            if (it) {
                showBluetoothEnablePermissionsDialog()
            } else {
                // permission denied so show dialog or prompt and take user to app notification settings
                Toast.makeText(
                    applicationContext,
                    "Location Permissions are denied. Tap Permissions > Location and ALLOW all permissions to proceed",
                    Toast.LENGTH_LONG
                ).show()

                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                intent.addCategory(Intent.CATEGORY_DEFAULT)
                intent.data = Uri.parse(
                    "package:" + applicationContext.packageName
                )
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
            }
        }

    private val requestMultiplePermissionsLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { it ->
            it.entries.forEach {
                if (it.value) {
                    // permission granted
                    showBluetoothEnablePermissionsDialog()
                } else {
                    // permission denied so show dialog or prompt and take user to app notification settings
                    Toast.makeText(
                        applicationContext,
                        "Nearby devices Permissions are denied. Tap Permissions > Nearby devices and ALLOW all permissions to proceed",
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent()
                    intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                    intent.addCategory(Intent.CATEGORY_DEFAULT)
                    intent.data = Uri.parse(
                        "package:" + applicationContext.packageName
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(intent)
                }
            }
        }

    private val enableBluetoothLauncher = registerForActivityResult(
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

    private fun showBluetoothEnablePermissionsDialog() {
        if (bluetoothAdapter?.isEnabled == false) {
            val enableBtIntent = Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
            enableBluetoothLauncher.launch(enableBtIntent)
        }
    }


    @RequiresApi(Build.VERSION_CODES.S)
    private fun hasBluetoothScanAndConnectPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.BLUETOOTH_SCAN
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        this, Manifest.permission.BLUETOOTH_CONNECT
    ) == PackageManager.PERMISSION_GRANTED

    private fun hasFineLocationPermission() = ContextCompat.checkSelfPermission(
        this, Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED
}