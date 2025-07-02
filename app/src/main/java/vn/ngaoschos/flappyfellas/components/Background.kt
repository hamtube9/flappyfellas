package vn.ngaoschos.flappyfellas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vn.ngaoschos.flappyfellas.R
import vn.ngaoschos.flappyfellas.ui.theme.GameBackground
import vn.ngaoschos.flappyfellas.ui.theme.spaceX
import vn.ngaoschos.flappyfellas.ui.theme.spaceXMars
import vn.ngaoschos.flappyfellas.ui.theme.spaceXMoon
import vn.ngaoschos.flappyfellas.ui.theme.twitter
import vn.ngaoschos.flappyfellas.ui.theme.twitterDoge
import vn.ngaoschos.flappyfellas.ui.theme.twitterGreen
import vn.ngaoschos.flappyfellas.ui.theme.twitterPink
import vn.ngaoschos.flappyfellas.ui.theme.twitterWhite
import vn.ngaoschos.flappyfellas.utils.dpToPx


@Composable
fun Background(score : Long) {

    val bg = when (score) {
        in 0L..19L -> {
            R.drawable.doge_bg
        }

        in 20L..39L -> {
            R.drawable.flappy_fellas_bg
        }

        else -> R.drawable.ic_launcher_background
    }
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.dpToPx()
    val screenHeight = configuration.screenHeightDp.dp.dpToPx()

    val backgroundUrl = getBackgroundUrl(MaterialTheme.colorScheme.primary)

    backgroundUrl?.let {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(painter = painterResource(bg), contentDescription = "Background", modifier = Modifier.size(screenWidth.dp,screenHeight.dp), contentScale = ContentScale.FillBounds)
        }
    }
}

private fun getBackgroundUrl(primaryColor: Color): String? {
    return when (primaryColor) {
        spaceX.primary -> {
            GameBackground.SPACE_X
        }

        twitter.primary -> {
            GameBackground.TWITTER
        }

        twitterWhite.primary -> {
            GameBackground.TWITTER_WHITE
        }

        twitterGreen.primary -> {
            GameBackground.TWITTER_GREEN
        }

        twitterPink.primary -> {
            GameBackground.TWITTER_PINK
        }

        twitterDoge.primary -> {
            GameBackground.TWITTER_DOGE
        }

        spaceXMars.primary -> {
            GameBackground.SPACE_X_MARS
        }

        spaceXMoon.primary -> {
            GameBackground.SPACE_X_MOON
        }

        else -> null
    }?.url
}