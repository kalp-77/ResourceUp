package com.example.devlist.ui.webview

import android.graphics.Color
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.R
import kotlinx.android.synthetic.main.image_web_view_activity.*


class ImageWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        window.statusBarColor = Color.BLACK

        super.onCreate(savedInstanceState)
        setContentView(R.layout.image_web_view_activity)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
            WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
        );
        val url = intent.getStringExtra("URL")
        if (url != null) {
            // to load webView faster
            imageWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            imageWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            imageWebView.settings.setAppCacheEnabled(true)
            imageWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            imageWebView.settings.domStorageEnabled = true
            imageWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            imageWebView.settings.javaScriptEnabled = true
            imageWebView.settings.userAgentString =
                "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            imageWebView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    imageWebViewProgressBar.visibility = View.GONE
                    imageWebView.visibility = View.VISIBLE
                }
            }
            imageWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (imageWebView.canGoBack()) {
                        imageWebView.goBack()
                    } else {
                        finish()
                    }
                    return true
                }
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}