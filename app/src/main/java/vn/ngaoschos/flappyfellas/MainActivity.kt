package vn.ngaoschos.flappyfellas

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.initialization.InitializationStatus
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        Thread(
            Runnable {
                // Initialize the Google Mobile Ads SDK on a background thread.
                MobileAds.initialize(
                    this,
                    OnInitializationCompleteListener { initializationStatus: InitializationStatus? ->
                        Log.e("initializationStatus","$initializationStatus")
                    })
            })
            .start()
        setContent {
            AppFlappyFellas()
        }
    }
}
