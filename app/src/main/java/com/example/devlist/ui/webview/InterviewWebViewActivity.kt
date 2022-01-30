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
import kotlinx.android.synthetic.main.interview_web_view_activity.*
import kotlinx.android.synthetic.main.resume_web_view_activity.*

class InterviewWebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.interview_web_view_activity)
        window.setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        val url = intent.getStringExtra("URL")

        if(url!=null){
            interviewWebView.settings.setRenderPriority(WebSettings.RenderPriority.HIGH)
            interviewWebView.settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
            interviewWebView.settings.setAppCacheEnabled(true)
            interviewWebView.scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            interviewWebView.settings.domStorageEnabled = true
            interviewWebView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
            interviewWebView.settings.javaScriptEnabled = true
            interviewWebView.settings.userAgentString = "Mozilla/5.0 (iPhone; U; CPU like Mac OS X; en) AppleWebKit/420+ (KHTML, like Gecko) Version/3.0 Mobile/1A543a Safari/419.3"

            interviewWebView.webViewClient = object: WebViewClient(){
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    interviewWebViewProgressBar.visibility = View.GONE
                    interviewWebView.visibility = View.VISIBLE
                }
            }
            interviewWebView.loadUrl(url)
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (event.getAction() === KeyEvent.ACTION_DOWN) {
            when (keyCode) {
                KeyEvent.KEYCODE_BACK -> {
                    if (interviewWebView.canGoBack()) {
                        interviewWebView.goBack()
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