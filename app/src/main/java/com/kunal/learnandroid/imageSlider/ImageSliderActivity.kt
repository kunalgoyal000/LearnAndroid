package com.kunal.learnandroid.imageSlider

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.kunal.learnandroid.R
import com.kunal.learnandroid.core.ui.theme.LearnAndroidTheme
import kotlinx.coroutines.launch

class ImageSliderActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val animals = listOf(
            R.drawable.cat,
            R.drawable.dog,
            R.drawable.chicken
        )

        setContent {
            LearnAndroidTheme {
                val pagerState = rememberPagerState(
                    initialPage = 0, pageCount = { animals.size }
                )
                val scope = rememberCoroutineScope()
                Box(modifier = Modifier.fillMaxSize()) {
                    HorizontalPager(
                        outOfBoundsPageCount = animals.size,
                        state = pagerState,
                        key = { animals[it] },
                        pageSize = PageSize.Fill
                    ) {
                        Image(
                            painter = painterResource(id = animals[it]),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                    Box(
                        modifier = Modifier
                            .offset(y = -(16).dp)
                            .fillMaxWidth(0.5f)
                            .clip(RoundedCornerShape(100))
                            .background(Color.White)
                            .padding(8.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterStart),
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage - 1
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                                contentDescription = "Go back"
                            )
                        }
                        IconButton(
                            modifier = Modifier.align(Alignment.CenterEnd),
                            onClick = {
                                scope.launch {
                                    pagerState.animateScrollToPage(
                                        pagerState.currentPage + 1
                                    )
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                                contentDescription = "Go forward"
                            )
                        }
                    }
                }
            }
        }
    }
}