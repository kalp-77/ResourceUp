package com.example.devlist.ui.webview

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.WindowManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.devlist.R
import kotlinx.android.synthetic.main.font_web_view_activity.*


class FontWebViewActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.font_web_view_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url != null){
            // to load webView faster
            fontWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            fontWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            fontWebView.settings.setAppCacheEnabled(true)
            fontWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            fontWebView.settings.domStorageEnabled = true
            fontWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            fontWebView.settings.javaScriptEnabled = true
            fontWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            fontWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    fontWebViewProgressBar.visibility = View.GONE
                    fontWebView.visibility = View.VISIBLE
                }
            }
            fontWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.action === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (fontWebView.canGoBack()) {
                        fontWebView.goBack()
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