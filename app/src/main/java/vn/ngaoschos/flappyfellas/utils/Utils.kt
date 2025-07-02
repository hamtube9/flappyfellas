package vn.ngaoschos.flappyfellas.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.dpToPx() = with(LocalDensity.current) {
    this@dpToPx.toPx().toInt()
}

fun openUri(uriHandler: UriHandler, uri : String){
    uriHandler.openUri(uri)
}
