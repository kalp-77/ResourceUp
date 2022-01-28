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
import kotlinx.android.synthetic.main.icons_web_view_activity.*
import kotlinx.android.synthetic.main.public_api_web_view_activity.*
import kotlinx.android.synthetic.main.public_api_web_view_activity.apiWebView

class IconsWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.icons_web_view_activity)

        window.statusBarColor = Color.BLACK

        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url!=null){
            iconWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            iconWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            iconWebView.settings.setAppCacheEnabled(true)
            iconWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            iconWebView.settings.domStorageEnabled = true
            iconWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            iconWebView.settings.javaScriptEnabled = true
            iconWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            iconWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    iconWebViewProgressBar.visibility = View.GONE
                    iconWebView.visibility = View.VISIBLE
                }
            }
            iconWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
            if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (iconWebView.canGoBack()) {
                        iconWebView.goBack()
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