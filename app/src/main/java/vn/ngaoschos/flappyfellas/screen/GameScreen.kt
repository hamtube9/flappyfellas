package vn.ngaoschos.flappyfellas.screen

import android.media.MediaPlayer
import android.os.Bundle
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import vn.ngaoschos.flappyfellas.components.Background
import vn.ngaoschos.flappyfellas.components.Bird
import vn.ngaoschos.flappyfellas.components.Ground
import vn.ngaoschos.flappyfellas.components.Pipes
import vn.ngaoschos.flappyfellas.components.Play
import vn.ngaoschos.flappyfellas.components.VoiceChar
import vn.ngaoschos.flappyfellas.components.getPipeDimensions
import vn.ngaoschos.flappyfellas.utils.AppRouter
import vn.ngaoschos.flappyfellas.utils.GameState
import vn.ngaoschos.flappyfellas.utils.Preferences



@Composable
fun GameScreen(navController : NavController, gameId : String?){
    val preference = Preferences(LocalContext.current)

    var gameState by remember { mutableStateOf(GameState.NOT_STARTED) }

    var score by remember { mutableLongStateOf(0L) }

    var birdOffset by remember { mutableStateOf(0.dp) }

    var bestScore by remember { mutableLongStateOf(preference.getDataLong("best_score")) }

    var lastScore by remember { mutableLongStateOf(preference.getDataLong("last_score"))}

    var birdRect by remember { mutableStateOf(Rect(left = 0f,top = 0f,64.dp.value,64.dp.value)) }

    val screenHeight = LocalConfiguration.current.screenHeightDp.dp

    var pipeDimensions by remember { mutableStateOf(Triple(0.1f,0.2f,0.3f)) }



    LaunchedEffect(key1 = birdOffset,gameState) {
        while (gameState == GameState.PLAYING){
            delay(16)
            birdOffset += 6.dp
        }
    }

    val updateBirdRect : (birdRect : Rect) -> Unit = {
        birdRect = it
        pipeDimensions = getPipeDimensions(it,screenHeight)
    }

    val updatePipeRect: (pipeRect: Rect) -> Unit = {
        if (!it.intersect(birdRect).isEmpty) {
            val bundle = Bundle()
            bundle.putString("theme", gameId)
            bundle.putLong("game_score", score)


            gameState = GameState.COMPLETED
            if (score > bestScore) {
                bestScore = score
                preference.saveDataLong("best_score", score)
                bundle.putLong("new_best_score", score)
            }
            preference.saveDataLong("last_score", score)
            lastScore = score

            score = 0
            birdOffset = 0.dp
            birdRect = Rect(0f, 0f, 64.dp.value, 64.dp.value)

            navController.navigate(AppRouter.GAME_OVER_SCREEN)
        }
    }

    val updateScoreCallback: (Long) -> Unit = {
        score += it
    }



    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        if (gameState == GameState.PLAYING) {
                            // Jump by changing the bird's offset
                            coroutineScope.launch {
                                var offsetChange = 80.dp
                                while (offsetChange > 0.dp) {
                                    birdOffset -= 2.dp
                                    delay(2L)
                                    offsetChange -= 2.dp
                                }
                            }

                        }
                    }
                )
            }
    ) {
        Background(score)

        Pipes(
            updatePipeRect = updatePipeRect,
            updateScoreCallback = updateScoreCallback,
            gameState = gameState,
            pipeDimensions = pipeDimensions.copy()
        )

        when (gameState) {
            GameState.PLAYING -> {
                Bird(birdOffset, updateBirdRect,score)
                VoiceChar(score)
                val onPauseCallback = {
                    val bundle = Bundle()
                    bundle.putString("theme", gameId)
//                    Analytics.logEvent(Events.GAME_PAUSED, bundle)
                    gameState = GameState.PAUSE
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Ground("Fellas Score", score, enablePause = true, onPauseCallback)
                }
            }

            GameState.NOT_STARTED, GameState.COMPLETED -> {
                val onPlayCallback = {
                    score = 0
                    gameState = GameState.PLAYING
                }

                Box(modifier = Modifier.align(Alignment.Center)) {
                    Play(onPlayCallback)
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    if (lastScore > 0) {
                        Column {
                            Ground("Last Score", lastScore)
                            Ground("Best Score", bestScore)
                        }
                    } else {
                        Ground("Best Score", bestScore)
                    }

                }
            }

            GameState.PAUSE -> {
                val onPlayCallback = {
                    val bundle = Bundle()
                    bundle.putString("theme", gameId)
//                    Analytics.logEvent(Events.GAME_RESUMED, bundle)
                    gameState = GameState.PLAYING
                }

                Box(modifier = Modifier.align(Alignment.Center)) {
                    Play(onPlayCallback)
                }

                Box(modifier = Modifier.align(Alignment.BottomCenter)) {
                    Ground("Current Score", score)
                }
            }
        }
    }
}