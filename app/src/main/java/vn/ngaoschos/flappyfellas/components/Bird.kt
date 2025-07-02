package vn.ngaoschos.flappyfellas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import vn.ngaoschos.flappyfellas.R
import vn.ngaoschos.flappyfellas.ui.theme.spaceX
import vn.ngaoschos.flappyfellas.ui.theme.spaceXMars
import vn.ngaoschos.flappyfellas.ui.theme.spaceXMoon
import vn.ngaoschos.flappyfellas.ui.theme.twitterDoge


@Composable
fun Bird(birdOffset: Dp, updateBirdRect: (Rect) -> Unit, score : Long) {

    val bird = when (score) {
        in 0L..119L -> {
            R.drawable.la_vaca_saturno_saturnita
        }

        in 120L..239L -> {
            R.drawable.boneca_ambalabu
        }

        in 240L..399L -> {
            R.drawable.brr_brr_patapim
        }

        in 400L..699L -> {
            R.drawable.chimpanzini_bananini
        }

        in 700L..999L -> {
            R.drawable.tralalero_tralala
        }

        in 1000L..1299L -> {
            R.drawable.bombombini_gusini
        }

        in 1300L..1599L -> {
            R.drawable.bombardino_crocodillo
        }

        else -> R.drawable.tung_tung_tung_sahur
    }

    bird.let {
        Box(
            modifier = Modifier
                .size(64.dp)
                .offset(y = birdOffset)
                .padding(5.dp)
                .onGloballyPositioned {
                    updateBirdRect(it.boundsInRoot())
                }
        ) {
            when (MaterialTheme.colorScheme.primary) {
                spaceX.primary, spaceXMoon.primary, spaceXMars.primary -> {
                    Image(painterResource(id = it), contentDescription = "rocket")
                }

                else -> {
                    if (MaterialTheme.colorScheme.primary == twitterDoge.primary) {
                        Image(painterResource(id = it), contentDescription = "doge rocket")
                    } else {
                        Icon(
                            painterResource(id = it),
                            tint = MaterialTheme.colorScheme.secondary,
                            contentDescription = "bird"
                        )
                    }
                }
            }
        }
    }
}