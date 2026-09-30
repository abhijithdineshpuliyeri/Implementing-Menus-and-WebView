package com.example.implementingmenusandwebview.fragments

import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.implementingmenusandwebview.R

class WebViewFragment :
    Fragment(R.layout.fragment_web_view) {

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {

        super.onViewCreated(view, savedInstanceState)

        val webView =
            view.findViewById<WebView>(
                R.id.webView
            )

        webView.webViewClient = WebViewClient()

        webView.settings.javaScriptEnabled = true

        webView.loadUrl(
            "https://www.flipkart.com"
        )
    }
}
