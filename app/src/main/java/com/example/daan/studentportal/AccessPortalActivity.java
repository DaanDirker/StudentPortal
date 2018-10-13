package com.example.daan.studentportal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AccessPortalActivity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access_portal);

        Intent intent = getIntent();
        String url = intent.getStringExtra(MainActivity.EXTRA_URL);
        String title = intent.getStringExtra(MainActivity.EXTRA_TITLE);

        //Set Toolbar title
        if (title != null) {
            setTitle(title);
        }

        // Load webview
        if (url != null) {
            mWebView = findViewById(R.id.webView);
            mWebView.setWebViewClient(new WebViewClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(url);
        }
    }
}
