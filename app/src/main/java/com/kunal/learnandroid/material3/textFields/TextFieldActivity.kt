package com.kunal.learnandroid.material3.textFields

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.MonitorWeight
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme

class TextFieldActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LearnAndroidTheme {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var filledText by remember {
                        mutableStateOf("")
                    }
                    TextField(
                        value = filledText,
                        onValueChange = { filledText = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Start
                        ),
                        label = {
                            Text(text = "Enter your weight")
                        },
                        placeholder = {
                            Text(text = "Weight")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.MonitorWeight,
                                contentDescription = "Weight"
                            )
                        },
                        trailingIcon = {
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = "Person")
                        },
                        prefix = {
                            Text(text = "$")
                        },
                        suffix = {
                            Text(text = "Kg")
                        },
                        supportingText = {
                            Text(text = "Choose value less than 1000") // great way to show error with a state and isError = true
                        },
                        isError = false,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                println("Clicked Next")
                            }
                        ),
                        singleLine = true
                    )
                    Spacer(Modifier.height(32.dp))
                    var outlinedText by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(
                        value = outlinedText,
                        onValueChange = { outlinedText = it },
                        textStyle = LocalTextStyle.current.copy(
                            textAlign = TextAlign.Start
                        ),
                        label = {
                            Text(text = "Enter your weight")
                        },
                        placeholder = {
                            Text(text = "Weight")
                        },
                        leadingIcon = {
                            Icon(
                                imageVector = Icons.Outlined.MonitorWeight,
                                contentDescription = "Weight"
                            )
                        },
                        trailingIcon = {
                            Icon(imageVector = Icons.Outlined.Person, contentDescription = "Person")
                        },
                        prefix = {
                            Text(text = "$")
                        },
                        suffix = {
                            Text(text = "Kg")
                        },
                        supportingText = {
                            Text(text = "Choose value less than 1000") // great way to show error with a state and isError = true
                        },
                        isError = false,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                println("Clicked Next")
                            }
                        ),
                        singleLine = true
                    )
                }
            }
        }
    }
}