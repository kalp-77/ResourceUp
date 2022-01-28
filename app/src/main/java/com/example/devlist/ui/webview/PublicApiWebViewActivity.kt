package com.example.devlist.ui.webview

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.KeyEvent

import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.devlist.R
import kotlinx.android.synthetic.main.public_api_web_view_activity.*


class PublicApiWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.public_api_web_view_activity)

        window.statusBarColor = Color.BLACK

        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        val url = intent.getStringExtra("URL")
        if(url != null){

            // to load webView faster
            apiWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            apiWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            apiWebView.settings.setAppCacheEnabled(true)
            apiWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            apiWebView.settings.domStorageEnabled = true
            apiWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            apiWebView.settings.javaScriptEnabled = true
            apiWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            apiWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    apiWebViewProgressBar.visibility = View.GONE
                    apiWebView.visibility = View.VISIBLE
                }
            }
            apiWebView.loadUrl(url)
        }
    }


    // to go back in webview
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (apiWebView.canGoBack()) {
                        apiWebView.goBack()
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