package com.google.android.chapter_23_webview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    // Khai bao quyen su dung internet
    WebView webView;
    String pathOfYoutube = "https://www.youtube.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.web_view);

        webView.setWebChromeClient(new ChromeClient());
        webView.loadUrl(pathOfYoutube);// chuyển đến browser với link truyền vào
    }

    final class ChromeClient extends WebChromeClient {
        @Override
        public boolean onConsoleMessage(ConsoleMessage msg) {
            Log.d(
                    "WebView",
                    String.format("%s %s:%d", msg.message(), msg.lineNumber(), msg.sourceId())
            );
            return true;
        }
    }

}