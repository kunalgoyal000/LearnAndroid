package com.kunal.learnandroid.core.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kunal.learnandroid.appShortcut.AppShortcutActivity
import com.kunal.learnandroid.biometric.BiometricActivity
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.core.ui.viewModels.MainViewModel
import com.kunal.learnandroid.daggerHilt.ui.DaggerHiltActivity
import com.kunal.learnandroid.documentScanner.DocumentScannerActivity
import com.kunal.learnandroid.internet.InternetConnectivityActivity
import com.kunal.learnandroid.notifications.ui.NotificationActivity
import com.kunal.learnandroid.permissions.PermissionsActivity
import com.kunal.learnandroid.pullToRefresh.ui.PullToRefreshActivity
import com.kunal.learnandroid.services.foreground.ForegroundServiceActivity

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
            setOnExitAnimationListener { screen ->
                val zoomX = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_X,
                    0.4f,
                    0.0f
                )

                zoomX.interpolator = OvershootInterpolator()
                zoomX.duration = 500L
                zoomX.doOnEnd { screen.remove() }

                val zoomY = ObjectAnimator.ofFloat(
                    screen.iconView,
                    View.SCALE_Y,
                    0.4f,
                    0.0f
                )

                zoomY.interpolator = OvershootInterpolator()
                zoomY.duration = 500L
                zoomY.doOnEnd { screen.remove() }

                zoomX.start()
                zoomY.start()
            }
        }

        setContent {
            LearnAndroidTheme {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Button(onClick = {
                            startActivity(Intent(this@MainActivity, BiometricActivity::class.java))
                        }) {
                            Text(text = "Biometric")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DocumentScannerActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Document Scanner")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AppShortcutActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "App Shortcut")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ForegroundServiceActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Foreground Service")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PermissionsActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Permissions")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DaggerHiltActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Dagger-Hilt, Retrofit, Coil")
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Button(onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        InternetConnectivityActivity::class.java
                                    )
                                )
                            }) {
                                Text(text = "Internet connectivity")
                            }
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PullToRefreshActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Pull to Refresh")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    NotificationActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Notifications")
                        }
                    }
                }
            }
        }
    }