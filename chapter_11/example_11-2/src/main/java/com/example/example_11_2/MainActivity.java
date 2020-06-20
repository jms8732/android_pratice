package com.example.example_11_2;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView wv1;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv1 = (WebView)findViewById(R.id.wv1);

        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv1.loadUrl("file://android_asset/test.html");
    }
    @Override
    public void onClick(View v) {

    }
}
