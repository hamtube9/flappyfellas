package vn.ngaoschos.flappyfellas.utils

import vn.ngaoschos.flappyfellas.utils.AppRouterArgs.GAME_OVER_SCREEN_NAME
import vn.ngaoschos.flappyfellas.utils.AppRouterArgs.GAME_SCREEN_ID
import vn.ngaoschos.flappyfellas.utils.AppRouterArgs.MAIN_SCREEN_NAME


object AppRouterArgs {
    const val MAIN_SCREEN_NAME = "Counter"
    const val GAME_SCREEN_ID = "gameId"
    const val GAME_OVER_SCREEN_NAME = "GameOver"
}

object AppRouter {
    const val GAME_SCREEN_NAME = "Game"

    const val MAIN_SCREEN = MAIN_SCREEN_NAME
    const val GAME_SCREEN = "$GAME_SCREEN_NAME/{$GAME_SCREEN_ID}"
    const val GAME_OVER_SCREEN = GAME_OVER_SCREEN_NAME
}