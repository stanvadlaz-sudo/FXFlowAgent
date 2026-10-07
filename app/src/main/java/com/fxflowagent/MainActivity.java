package com.fxflowagent;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends Activity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        webView = new WebView(this);

        webView.setWebViewClient(new WebViewClient());

        webView.getSettings().setJavaScriptEnabled(true);

        webView.addJavascriptInterface(new CbrBridge(), "AndroidCbr");

        webView.loadUrl("file:///android_asset/index.html");

        setContentView(webView);
    }

    public class CbrBridge {

        @JavascriptInterface
        public String getRates() {
            return CbrService.getRates();
        }
    @JavascriptInterface
public String getBrent() {
    return CbrService.getBrent();
}
}
