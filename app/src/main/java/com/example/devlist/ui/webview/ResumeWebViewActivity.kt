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
import kotlinx.android.synthetic.main.resume_web_view_activity.*

class ResumeWebViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resume_web_view_activity)
        window.statusBarColor = Color.BLACK

        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url!=null){
            resumeWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            resumeWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            resumeWebView.settings.setAppCacheEnabled(true)
            resumeWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            resumeWebView.settings.domStorageEnabled = true
            resumeWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            resumeWebView.settings.javaScriptEnabled = true
            resumeWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            resumeWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    resumeWebViewProgressBar.visibility = View.GONE
                    resumeWebView.visibility = View.VISIBLE
                }
            }
            resumeWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (resumeWebView.canGoBack()) {
                        resumeWebView.goBack()
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