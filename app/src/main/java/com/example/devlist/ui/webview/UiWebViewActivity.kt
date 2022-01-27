package com.example.devlist.ui.webview

import android.os.Build
import android.os.Bundle

import android.view.KeyEvent

import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.R

import kotlinx.android.synthetic.main.ui_web_view_activity.*

class UiWebViewActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_web_view_activity)

        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);

        val url = intent.getStringExtra("URL")
        if(url != null){

            // to load webView faster
            uiWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            uiWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            uiWebView.settings.setAppCacheEnabled(true)
            uiWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            uiWebView.settings.domStorageEnabled = true
            uiWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            uiWebView.settings.javaScriptEnabled = true
            uiWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            uiWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    uiWebViewProgressBar.visibility = View.GONE
                    uiWebView.visibility = View.VISIBLE
                }
            }

            uiWebView.loadUrl(url)

        }

    }


    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (uiWebView.canGoBack()) {
                        uiWebView.goBack()
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