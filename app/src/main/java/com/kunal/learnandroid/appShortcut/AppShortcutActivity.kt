package com.kunal.learnandroid.appShortcut

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.kunal.learnandroid.MainViewModel
import com.kunal.learnandroid.R
import com.kunal.learnandroid.ShortcutType
import com.kunal.learnandroid.ui.theme.LearnAndroidTheme

class AppShortcutActivity: ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        handleIntent(intent)
        setContent{
            LearnAndroidTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(
                        16.dp, Alignment.CenterVertically)
                    ){
                    when(viewModel.shortcutType){
                        ShortcutType.STATIC ->  Text(text = "Static shortcut clicked")
                        ShortcutType.DYNAMIC -> Text(text = "Dynamic shortcut clicked")
                        ShortcutType.PINNED -> Text(text = "Pinned shortcut clicked")
                        null -> Unit
                    }
                    Button(
                        onClick = ::addDynamicShortcut
                    ) {
                     Text(text = "Add dynamic shortcut")
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun addDynamicShortcut() {
        val shortcut = ShortcutInfoCompat.Builder(applicationContext,"dynamic")
            .setShortLabel("Call Dad")
            .setLongLabel("Clicking this will call your dad")
            .setIcon(IconCompat.createWithResource(
                applicationContext, R.drawable.baseline_3p_24
            ))
            .setIntent(
                Intent(applicationContext, AppShortcutActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra("shortcut_id", "dynamic")
                }
            ).build()

        ShortcutManagerCompat.pushDynamicShortcut(applicationContext,shortcut)
    }

    private fun handleIntent(intent: Intent?){
        intent?.let {
            when(intent.getStringExtra("shortcut_id")){
                "static" -> viewModel.onShortcutClicked(ShortcutType.STATIC)
                "dynamic" -> viewModel.onShortcutClicked(ShortcutType.DYNAMIC)
                "pinned" -> viewModel.onShortcutClicked(ShortcutType.PINNED)
            }
        }
    }
}