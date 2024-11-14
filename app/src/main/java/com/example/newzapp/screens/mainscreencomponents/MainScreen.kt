package com.example.newzapp.screens.mainscreencomponents

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newzapp.R

@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val newsState = viewModel.newzData.value
    val num = remember { mutableIntStateOf(0) }
    val context = LocalContext.current

    when {
        newsState.loading == true -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LoadingScreen()
            }
        }

        newsState.e != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "Error: ${newsState.e?.localizedMessage}")
            }
        }

        newsState.data != null -> {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                LazyColumn(modifier = Modifier.weight(1f).wrapContentHeight()) {
                    item {
                        ArticleCard(
                            Modifier,
                            article = newsState.data!!.articles[num.intValue]
                        ) {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                            context.startActivity(intent)
                        }
                    }
                }

                Row(
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(.29f)
                        .padding(start = 15.dp, end = 15.dp)
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .alpha(0.8f),
                        painter = painterResource(R.drawable.newz_black),
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(80.dp))

                    ButtonFun(
                        Modifier
                            .size(60.dp)
                            .clip(CircleShape)
                            .background(Color.Black)
                            .clickable {
                                if (num.intValue != 0) num.intValue =
                                    (num.intValue - 1)
                            }, "Previous"
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    ButtonFun(
                        Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFECDBA7))
                            .clickable {
                                if (num.intValue == newsState.data!!.articles.size - 1)
                                    num.intValue = 0
                                else if (newsState.data!!.articles[num.intValue].title == "[Removed]") num.intValue =
                                    (num.intValue + 1)
                                else
                                    num.intValue = (num.intValue + 1)

                            }, "Next"
                    )

                }
                Spacer(modifier = Modifier.height(20.dp))


            }


        }
    }
}
