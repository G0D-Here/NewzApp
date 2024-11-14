package com.example.newzapp.screens.mainscreencomponents

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.newzapp.R
import com.example.newzapp.blueprint.Article
import kotlinx.coroutines.delay
import java.util.Locale

@Composable
fun LoadingScreen() {
    val scale = remember() {
        Animatable(0f)
    }
    LaunchedEffect(key1 = 0f, block = {
        scale.animateTo(
            targetValue = 1.5f,
            animationSpec = tween(
                durationMillis = 800,
                delayMillis = 0
            )
        )
        delay(8000)

    })
    Box(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .scale(scale.value),
            shape = CircleShape,
        ) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(R.drawable.newz_black),
                contentDescription = "Logo",
            )
        }
    }
}


@Composable
fun ArticleCard(
    modifier: Modifier,
    article: Article,
    onClick: (String) -> Unit
) {
    Box(
        modifier
            .wrapContentWidth()
            .height(460.dp)
            .padding(start = 8.dp, end = 30.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = article.urlToImage, contentDescription = "Url Image",
                placeholder = painterResource(R.drawable.newz_black),
                modifier = Modifier
                    .height(230.dp)
                    .width(320.dp)
                    .padding(8.dp, 8.dp, 8.dp, 8.dp)
                    .clickable { onClick(article.url) },
            )

            Text(
                text = article.run { title.uppercase(Locale.ROOT) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 4.dp, bottom = 4.dp)
                    .clickable { onClick(article.url) },
                color = Color(0xFFECDBA7),
                fontSize = 23.sp,
                fontWeight = FontWeight.W500,
                fontFamily = FontFamily.Monospace,
                textAlign = TextAlign.Start,
                maxLines = 3,
            )
            article.description?.let {
                Text(
                    text = it.run { uppercase(Locale.ROOT) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp, 0.dp, 4.dp, 0.dp),
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    style = MaterialTheme.typography.bodyLarge,
                    maxLines = 4,
                    textAlign = TextAlign.Start
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight()
                        .width(100.dp)
                        .padding(start = 8.dp),
                    text = "~ ${article.author}",
                    color = Color.DarkGray,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.Start,
                )
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .height(40.dp)
                        .width(200.dp),
                    text = article.publishedAt,
                    color = Color.DarkGray,
                    fontSize = 10.sp,
                    fontFamily = FontFamily.SansSerif,
                    textAlign = TextAlign.End
                )
            }
        }
    }
}


@Composable
fun ButtonFun(modifier: Modifier, text: String) {
    Box(
        modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 10.sp,
            color = Color.White,
            textAlign = TextAlign.End
        )
    }
}





