package com.example.example_11_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    WebView wv1;
    Button btn1,btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wv1 = (WebView)findViewById(R.id.wv1);

        btn1 = (Button)findViewById(R.id.btn1);
        btn2 = (Button)findViewById(R.id.btn2);

        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);

        WebSettings webSettings = wv1.getSettings();
        webSettings.setJavaScriptEnabled(true);
        wv1.loadUrl("file:///android_asset/test.html");
        wv1.addJavascriptInterface(new JavascriptTest(),"android");
        wv1.setWebViewClient(new myWebClient());
        wv1.setWebChromeClient(new myWebChrome());
    }

    class JavascriptTest {
        @JavascriptInterface
        public String getChartData(){
            StringBuffer sb = new StringBuffer();
            sb.append("[");

            for(int i =0 ; i <14 ; i++){
                sb.append("["+i+","+Math.sin(i)+"]");
                Log.d("jms",i+","+Math.sin(i));
                if(i<13) sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    class myWebChrome extends WebChromeClient{
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            result.confirm();
            return true;
        }
    }

    class myWebClient extends WebViewClient{
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            Toast.makeText(MainActivity.this,url,Toast.LENGTH_SHORT).show();

            return true;
        }
    }

    @Override
    public void onClick(View v) {
        if(btn1 == v){
            wv1.loadUrl("javascript:lineChart()");
        }else
            wv1.loadUrl("javascript:barChart()");
    }
}
