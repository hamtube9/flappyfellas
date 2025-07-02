package vn.ngaoschos.flappyfellas.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import vn.ngaoschos.flappyfellas.utils.AppRouter
import vn.ngaoschos.flappyfellas.utils.dpToPx

@Composable
fun MenuItem(navController: NavController,
             backgroundUrl : String,
             name : String,
             originName : String) {
    val width = 300
    val height = 450
    val shareRounded = RoundedCornerShape(
        bottomStart = 30.dp,
        topEnd = 30.dp,
        topStart = 10.dp,
        bottomEnd = 10.dp
    )
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 30.dp
        ),
        shape = shareRounded,
        modifier = Modifier
            .size(width = width.dp, height = height.dp)
//            .clickable { navController.navigate("${AppRouter.GAME.name}/$originName") }
            .border(BorderStroke(2.dp, Color.Black),
                shareRounded
            )
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage("$backgroundUrl/${width.dp.dpToPx()}x${height.dp.dpToPx()}", contentDescription = "menu")
            Text(name,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 24.sp,
                color = Color.White
            )
        }
    }
}