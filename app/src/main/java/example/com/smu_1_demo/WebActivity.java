package example.com.smu_1_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        setTitle("평창동계올림픽 공식 홈페이지");

        WebView WebView01 = (WebView) findViewById(R.id.webView01);
        WebView01.setWebViewClient(new WebViewClient());

        WebSettings webSettings = WebView01.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebView01.loadUrl("https://www.pyeongchang2018.com/ko/index");
    }

}
