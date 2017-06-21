package rw.theclick.carsrw;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class TestActivity extends Activity {
    WebView website;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        website = (WebView)findViewById(R.id.website);
        website.setWebViewClient(new WebViewClient());
        website.clearCache(true);
        website.getSettings().setAppCacheEnabled(false);
        website.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        website.getSettings().setJavaScriptEnabled(true);
        website.setWebChromeClient(new WebChromeClient());
        website.loadUrl("http://www.cars.rw");
    }
}
