package vn.ngaoschos.flappyfellas.screen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.navigation.NavController
import com.airbnb.lottie.LottieComposition
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import vn.ngaoschos.flappyfellas.R
import java.nio.file.WatchEvent

@Composable
fun GameOverScreen(navController: NavController,lottieResourceId: Int = R.raw.x_lottie) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(lottieResourceId))

    LaunchedEffect(key1 = Unit) {
        delay(300)
        navController.popBackStack()
    }

    Box {
        Image(painter = painterResource(R.drawable.doge_bg), contentDescription = "Doge Background", modifier = Modifier.fillMaxSize(), alignment = Alignment.Center,
            contentScale = ContentScale.FillHeight)
        Column(modifier = Modifier
            .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally) {
            LottieAnimation(
                modifier = Modifier.size(320.dp),
                composition = composition,
                iterations = LottieConstants.IterateForever
            )

            Text("GAME OVER",
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                fontFamily = FontFamily.Monospace)
        }

    }

}