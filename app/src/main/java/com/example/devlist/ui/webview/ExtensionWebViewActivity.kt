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
import kotlinx.android.synthetic.main.extension_web_view_activity.*

class ExtensionWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = Color.BLACK

        setContentView(R.layout.extension_web_view_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url!=null){
            extensionWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            extensionWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            extensionWebView.settings.setAppCacheEnabled(true)
            extensionWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            extensionWebView.settings.domStorageEnabled = true
            extensionWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            extensionWebView.settings.javaScriptEnabled = true
            extensionWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            extensionWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    extensionWebViewProgressBar.visibility = View.GONE
                    extensionWebView.visibility = View.VISIBLE
                }
            }
            extensionWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if ( extensionWebView.canGoBack()) {
                        extensionWebView.goBack()
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