package vn.ngaoschos.flappyfellas.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import vn.ngaoschos.flappyfellas.R
import vn.ngaoschos.flappyfellas.utils.AppRouter
import vn.ngaoschos.flappyfellas.utils.openUri

@Composable
fun MenuScreen(navController: NavController){

    val uriHandler = LocalUriHandler.current

    Column(modifier = Modifier.fillMaxSize()) {
        AdvertView()
        space(dpHeight = 32.dp, isFillMaxSize = false)

        Box(modifier = Modifier.fillMaxSize()){
            Image(painter = painterResource(R.drawable.flappy_fellas_bg), contentDescription = "Doge Background", modifier = Modifier.fillMaxSize(), alignment = Alignment.Center,
                contentScale = ContentScale.FillBounds)
                Column(modifier = Modifier.wrapContentHeight()
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = {_openUri(uriHandler)}) {
                    Icon(painterResource(id = R.drawable.logo),null,
                        modifier = Modifier.size(48.dp),
                        tint = Color.Yellow)
                }

                Text(appName(),
                    fontSize = 24.sp,
                    color = Color.White)
                space(dpHeight = 10.dp)
                Row(modifier = Modifier.fillMaxSize()
                    .horizontalScroll(rememberScrollState(), enabled = true),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically) {
                    space(dpWith = 30.dp)
                    Image(painter = painterResource(R.drawable.logo), contentDescription = "Play", modifier = Modifier.bounceClick(onClick = {
                        navController.navigate("${AppRouter.GAME.name}/fellas")
                    }))
                }
            }


        }

    }
}

fun appName() : String {
    val appName = buildAnnotatedString {
        withStyle(style = SpanStyle(
            fontWeight = FontWeight.ExtraLight,
            fontFamily = FontFamily.Monospace
        )){
            append("Flappy ")
            withStyle(style = SpanStyle(
                fontWeight = FontWeight.ExtraBold
            )){
                append("Fellas")
            }
        }
    }
    return appName.text
}

fun _openUri(uriHandler : UriHandler){
    openUri(uriHandler,"https://x.com")
}

@Composable
fun space(dpHeight : Dp? = null,dpWith : Dp? = null, isFillMaxSize : Boolean = false, bgColor : Color? = null){
    val spaceModifier = Modifier
    if(dpHeight != null){
        spaceModifier.height(dpHeight)
    }
    if(dpWith != null){
        spaceModifier.width(dpWith)
    }
    if(isFillMaxSize){
        spaceModifier.fillMaxSize()
    }
    if(bgColor != null){
        spaceModifier.background(bgColor)
    }
    Spacer(modifier = spaceModifier)

}

@Composable
fun AdvertView(modifier: Modifier = Modifier) {
    val isInEditMode = LocalInspectionMode.current

    if (isInEditMode) {
        Text(
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Red)
                .padding(horizontal = 2.dp, vertical = 6.dp),
            textAlign = TextAlign.Center,
            color = Color.Black,
            text = "Advert Here",
        )
    } else {
        AndroidView(
            modifier = modifier.fillMaxWidth().height(60.dp),
            factory = { context ->
                AdView(context).apply {
                    setAdSize(AdSize.BANNER)
                    adUnitId = context.getString(R.string.native_ad_id)
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}



fun Modifier.bounceClick(
    animationDuration: Int = 100,
    scaleDown: Float = 0.9f,
    onClick: () -> Unit
) = composed {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val animatable = remember {
        Animatable(1f)
    }

    LaunchedEffect(key1 = isPressed) {
        if (isPressed) {
            animatable.animateTo(scaleDown)
        } else animatable.animateTo(1f)
    }

    Modifier
        .graphicsLayer {
            val scale = animatable.value
            scaleX = scale
            scaleY = scale
        }
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onClick()
        }
}