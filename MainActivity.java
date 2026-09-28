package com.fxflowagent;
import android.app.Activity; import android.os.Bundle; import android.webkit.WebSettings; import android.webkit.WebView;
public class MainActivity extends Activity { @Override public void onCreate(Bundle b){super.onCreate(b);setContentView(R.layout.activity_main); WebView w=findViewById(R.id.webview); WebSettings s=w.getSettings(); s.setJavaScriptEnabled(true); s.setDomStorageEnabled(true); w.loadUrl("file:///android_asset/index.html");}}
