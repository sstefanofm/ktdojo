package com.example.helloworld

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.helloworld.ui.theme.HelloWorldTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HelloWorldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.Black
                ) {
                    ComposeArticleApp()
                }
            }
        }
    }
}

@Composable
fun ComposeArticleApp() {
    ArticleCard(
        title = stringResource(R.string.title_jetpack_compose_tutorial),
        shortDescription = stringResource(R.string.compose_short_desc),
        longDescription = stringResource(R.string.compose_long_desc),
        imagePainter = painterResource(R.drawable.sand),
    )
}

@Composable
fun ArticleCard(
    title: String,
    shortDescription: String,
    longDescription: String,
    imagePainter: Painter,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        ArticleHeader(
            title = title,
            imagePainter = imagePainter,
        )
        ArticleBody(
            shortDescription = shortDescription,
            longDescription = longDescription,
        )
    }
}

@Composable
fun ArticleHeader(title: String, imagePainter: Painter) {
    Box(
        modifier = Modifier
            .height(120.dp)
            .border(2.dp, Color.Black),
    ) {
        Image(
            painter = imagePainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
        )
        Text(
            text = title,
            color = Color.White,
            fontSize = 25.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Composable
fun ArticleBody(shortDescription: String, longDescription: String) {
    Column {
        Text(
            text = shortDescription,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(12.dp),
        )
        Text(
            text = longDescription,
            textAlign = TextAlign.Justify,
            color = Color.White,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp),
        )
    }
}