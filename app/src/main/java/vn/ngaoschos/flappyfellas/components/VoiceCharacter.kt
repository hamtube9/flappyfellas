package vn.ngaoschos.flappyfellas.components

import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import vn.ngaoschos.flappyfellas.R


enum class VoiceCharacter {tungTungTungSahur,bombadilorCocrodilo}

@Composable
fun VoiceChar(score : Long) {

    var currentVoice by remember { mutableStateOf<VoiceCharacter?>(null) }

    val mContext = LocalContext.current
    var tungtungtungPlayer = MediaPlayer.create(mContext,R.raw.tung_tung_tung_sahur)
    var crocodiloPlayer = MediaPlayer.create(mContext,R.raw.bombardino_crocodilo)

    when(score){
        in 10L..19L -> {
            if(currentVoice != VoiceCharacter.tungTungTungSahur){
                currentVoice = VoiceCharacter.tungTungTungSahur
                tungtungtungPlayer.start()
            }

        }
        in 20L..39L -> {
            if(currentVoice != VoiceCharacter.bombadilorCocrodilo){
                currentVoice = VoiceCharacter.bombadilorCocrodilo
                crocodiloPlayer.start()
            }
        }

        else -> {

        }

    }

}