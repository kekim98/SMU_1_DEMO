package example.com.smu_1_demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web2);

        setTitle("평창동계올림픽 홍보영상");

        WebView WebView02 = (WebView) findViewById(R.id.webView02);
        WebView02.setWebViewClient(new WebViewClient());

        WebSettings webSettings = WebView02.getSettings();
        webSettings.setJavaScriptEnabled(true);

        WebView02.loadUrl("https://www.youtube.com/watch?v=v7oSI5Sxais");
    }

}
