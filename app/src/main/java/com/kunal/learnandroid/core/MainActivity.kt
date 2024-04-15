package com.kunal.learnandroid.core

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.animation.doOnEnd
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.kunal.learnandroid.alarmManager.AlarmManagerActivity
import com.kunal.learnandroid.appShortcut.AppShortcutActivity
import com.kunal.learnandroid.authentication.AuthenticationActivity
import com.kunal.learnandroid.backgroundLocationTracking.BackgroundLocationTrackingActivity
import com.kunal.learnandroid.camera.ui.CameraActivity
import com.kunal.learnandroid.connectivity.ConnectivityActivity
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import com.kunal.learnandroid.core.ui.viewModels.MainViewModel
import com.kunal.learnandroid.daggerHilt.ui.DaggerHiltActivity
import com.kunal.learnandroid.dropdownMenus.DropdownMenuActivity
import com.kunal.learnandroid.fcm.FCMPushNotificationsActivity
import com.kunal.learnandroid.imageSlider.ImageSliderActivity
import com.kunal.learnandroid.landmarkRecognitionTensorFlow.LandmarkRecognitionTensorFlowActivity
import com.kunal.learnandroid.material3.Material3Activity
import com.kunal.learnandroid.media.audio.audioRecording.AudioRecordingActivity
import com.kunal.learnandroid.media.documentScanner.DocumentScannerActivity
import com.kunal.learnandroid.media.image.PhotoPickerActivity
import com.kunal.learnandroid.media.video.exoplayer.VideoPlayerActivity
import com.kunal.learnandroid.notifications.ui.NotificationActivity
import com.kunal.learnandroid.paging.PagingActivity
import com.kunal.learnandroid.permissions.PermissionsActivity
import com.kunal.learnandroid.recyclerView.RecyclerViewActivity
import com.kunal.learnandroid.roomDatabase.ContactListActivity
import com.kunal.learnandroid.search.SearchActivity
import com.kunal.learnandroid.services.foreground.ForegroundServiceActivity
import com.kunal.learnandroid.supportAllScreenSizes.SupportAllScreenSizesActivity
import com.kunal.learnandroid.text.TextActivity

class MainActivity : ComponentActivity() {

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
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(top = 50.dp, bottom = 50.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AuthenticationActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Authentication")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    RecyclerViewActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Recycler View")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    TextActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Text")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ConnectivityActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Connectivity")
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
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    CameraActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "CameraX")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    LandmarkRecognitionTensorFlowActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Landmark Recognition Tensor Flow")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    BackgroundLocationTrackingActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Background Location tracking")
                        }
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            Button(onClick = {
                                startActivity(
                                    Intent(
                                        this@MainActivity,
                                        AlarmManagerActivity::class.java
                                    )
                                )
                            }) {
                                Text(text = "Alarm Manager")
                            }
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    AudioRecordingActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Audio Recording")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ContactListActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Room Database")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    SupportAllScreenSizesActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "All Screen Sizes")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PagingActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Paging & Caching")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    FCMPushNotificationsActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "FCM Push Notifications")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    ImageSliderActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Image Slider")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    PhotoPickerActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Photo Picker")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    VideoPlayerActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Video Player with Picture in Picture")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    SearchActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Search")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    DropdownMenuActivity::class.java
                                )
                            )
                        }) {
                            Text(text = "Dropdown Menu")
                        }
                        Button(onClick = {
                            startActivity(
                                Intent(
                                    this@MainActivity,
                                    Material3Activity::class.java
                                )
                            )
                        }) {
                            Text(text = "Material3")
                        }
                    }
                }
            }
        }
    }