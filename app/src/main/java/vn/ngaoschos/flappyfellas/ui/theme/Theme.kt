package vn.ngaoschos.flappyfellas.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView

@Composable
fun FlappyFellasTheme(
    colorScheme: ColorScheme = spaceX,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}


val spaceX = darkColorScheme(
    primary = spacePurple,
    secondary = Color.Black,
    tertiary = Color.Black
)

val twitter = darkColorScheme(
    primary = earthYellow,
    secondary = twitterBlue,
    tertiary = Color.Black
)

val twitterGreen = darkColorScheme(
    primary = earthGreen,
    secondary = earthGreen,
    tertiary = Color.Black
)

val twitterPink = darkColorScheme(
    primary = flowerPink,
    secondary = flowerPink,
    tertiary = Color.White
)

val twitterWhite = darkColorScheme(
    primary = roadGray,
    secondary = Color.White,
    tertiary = Color.White
)

val spaceXMoon = darkColorScheme(
    primary = darkMoon,
    secondary = Color.White,
    tertiary = Color.White
)

val spaceXMars = darkColorScheme(
    primary = marsRust,
    secondary = Color.White,
    tertiary = Color.White
)

val twitterDoge = darkColorScheme(
    primary = moonLight,
    secondary = Color.White,
    tertiary = Color.White
)

val menuTheme = darkColorScheme(
    primary = Color.Black,
    secondary = Color.White,
    tertiary = Color.White
)

enum class GameBackground(val url: String) {
    TWITTER_DOGE("https://source.unsplash.com/qIRJeKdieKA"),
    SPACE_X("https://source.unsplash.com/ln5drpv_ImI"),
    SPACE_X_MOON("https://source.unsplash.com/Na0BbqKbfAo"),
    SPACE_X_MARS("https://source.unsplash.com/-_5dCixJ6FI"),
    TWITTER("https://source.unsplash.com/vC8wj_Kphak"),
    TWITTER_WHITE("https://source.unsplash.com/4nqSD0YmbDE"),
    TWITTER_GREEN("https://source.unsplash.com/MHNjEBeLTgw"),
    TWITTER_PINK("https://source.unsplash.com/5UCZREYSXDs")
}

fun getGameTheme(gameId: String?): ColorScheme {
    return when (gameId) {
        GameBackground.SPACE_X.name -> spaceX
        GameBackground.TWITTER.name -> twitter
        GameBackground.TWITTER_PINK.name -> twitterPink
        GameBackground.TWITTER_GREEN.name -> twitterGreen
        GameBackground.TWITTER_DOGE.name -> twitterDoge
        GameBackground.TWITTER_WHITE.name -> twitterWhite
        GameBackground.SPACE_X_MOON.name -> spaceXMoon
        GameBackground.SPACE_X_MARS.name -> spaceXMars
        else -> twitter
    }
}