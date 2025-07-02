package vn.ngaoschos.flappyfellas

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import vn.ngaoschos.flappyfellas.screen.GameOverScreen
import vn.ngaoschos.flappyfellas.screen.GameScreen
import vn.ngaoschos.flappyfellas.screen.MenuScreen
import vn.ngaoschos.flappyfellas.ui.theme.FlappyFellasTheme
import vn.ngaoschos.flappyfellas.ui.theme.getGameTheme
import vn.ngaoschos.flappyfellas.ui.theme.menuTheme
import vn.ngaoschos.flappyfellas.utils.AppRouter

@Composable
fun AppFlappyFellas(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppRouter.MENU.name) {
        composable(AppRouter.MENU.name){
            FlappyFellasTheme(colorScheme = menuTheme) {
                MenuScreen(navController)
            }
        }

        composable("${AppRouter.GAME.name}/{gameId}") {
            val gameId = it.arguments?.getString("gameId")
            val gameTheme = getGameTheme(gameId)
            val bundle = Bundle()
            bundle.putString("theme",gameId)

            FlappyFellasTheme() {
                GameScreen(navController,gameId)
            }
        }

        composable(AppRouter.GAME_OVER.name) {
            GameOverScreen(navController)
        }

    }
}