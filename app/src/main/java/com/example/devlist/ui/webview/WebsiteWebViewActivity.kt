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
import kotlinx.android.synthetic.main.website_web_view_activity.*

class WebsiteWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.website_web_view_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url!=null){
            websiteWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            websiteWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            websiteWebView.settings.setAppCacheEnabled(true)
            websiteWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            websiteWebView.settings.domStorageEnabled = true
            websiteWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            websiteWebView.settings.javaScriptEnabled = true
            websiteWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            websiteWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    websiteWebViewProgressBar.visibility = View.GONE
                    websiteWebView.visibility = View.VISIBLE
                }
            }
            websiteWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if ( websiteWebView.canGoBack()) {
                        websiteWebView.goBack()
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