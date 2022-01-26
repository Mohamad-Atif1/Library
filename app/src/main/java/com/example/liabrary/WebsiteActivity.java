package com.example.liabrary;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebsiteActivity extends AppCompatActivity {
    private WebView webView ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_website);

        webView = findViewById(R.id.webView);
        Intent intent = getIntent();
        if(intent != null){
           String url =  intent.getStringExtra("URL");
           webView.loadUrl(url);

            // get the website into the app otherwise it will go to the browser and opne the website
           webView.setWebViewClient(new WebViewClient());

           webView.getSettings().setJavaScriptEnabled(true);
        }

    }

    @Override
    public void onBackPressed() {
        if(webView.canGoBack())  // if there is bake pages
            webView.goBack(); // go to the back page pf the web
        else
        super.onBackPressed(); // go to the app
    }
}