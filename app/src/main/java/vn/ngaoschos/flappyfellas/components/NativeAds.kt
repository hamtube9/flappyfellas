package vn.ngaoschos.flappyfellas.components

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.google.android.gms.ads.nativead.NativeAd
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAdOptions
import com.google.android.gms.ads.nativead.NativeAdView

@Composable
fun CallNativeAd(nativeAd: NativeAd) {
    NativeAdView(ad = nativeAd) { ad, view ->
        LoadAdContent(ad, view)
    }
}

@Composable
fun NativeAdView(
    ad: NativeAd,
    adContent: @Composable (ad: NativeAd, contentView: View) -> Unit,
) {
    val contentViewId by remember { mutableIntStateOf(View.generateViewId()) }
    val adViewId by remember { mutableIntStateOf(View.generateViewId()) }
    AndroidView(
        factory = { context ->
            val contentView = ComposeView(context).apply {
                id = contentViewId
            }
            NativeAdView(context).apply {
                id = adViewId
                addView(contentView)
            }
        },
        modifier = Modifier
        ,
        update = { view ->
            val adView = view.findViewById<NativeAdView>(adViewId)
            val contentView = view.findViewById<ComposeView>(contentViewId)

            adView.setNativeAd(ad)
            adView.callToActionView = contentView
            contentView.setContent { adContent(ad, contentView) }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun LoadAdContent(nativeAd: NativeAd?, composeView: View) {
    Card(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clip(CardDefaults.shape)
            .combinedClickable {
                composeView.performClick()
            },
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        nativeAd?.let {
            Column(
                modifier = Modifier
                    .padding(8.dp)
            ) {
                Row(
                    modifier = Modifier,
                    horizontalArrangement = Arrangement.Start
                ) {
                    val icon: Drawable? = it.icon?.drawable
                    icon?.let { drawable ->
                        Image(
                            painter = rememberAsyncImagePainter(model = drawable),
                            contentDescription = "Ad"/*it.icon?.contentDescription*/,
                            modifier = Modifier
                                .size(40.dp)
                                .padding(end = 8.dp),
                            contentScale = ContentScale.Crop
                        )
                    }

                    Column {
                        Text(
                            text = it.headline ?: "",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                        Text(
                            text = it.body ?: "",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurface,
                        )
                    }
                }

                it.callToAction?.let { cta ->
                    Button(
                        modifier = Modifier,
                        onClick = {
                            composeView.performClick()
                        },
                        content = {
                            Text(
                                text = cta.uppercase(),
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                style = MaterialTheme.typography.titleMedium
                            )
                        }
                    )
                }
            }
        } ?: run {
            // Placeholder for loading state or error state
            Text("Loading ad...")
        }
    }
}


fun loadNativeAd(context: Context, adUnitId: String, callback: (NativeAd?) -> Unit) {
    val builder = AdLoader.Builder(context, adUnitId)
        .forNativeAd { nativeAd ->
            callback(nativeAd)
        }

    val adLoader = builder
        .withAdListener(object  : AdListener() {
            override fun onAdFailedToLoad(p0: LoadAdError) {
                super.onAdFailedToLoad(p0)
                Log.e("onAdFailedToLoad",p0.message)
                callback(null)
            }
        })
        .withNativeAdOptions(NativeAdOptions.Builder().build())
        .build()

    adLoader.loadAd(AdRequest.Builder().build())
}
