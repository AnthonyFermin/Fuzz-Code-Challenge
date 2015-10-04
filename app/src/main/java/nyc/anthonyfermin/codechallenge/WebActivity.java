package nyc.anthonyfermin.codechallenge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

/**
 * Created by c4q-anthonyf on 10/3/15.
 */
public class WebActivity extends AppCompatActivity {

    private WebView webView;
    private ProgressBar progressBar;

    private static final String FUZZ_URL = "https://fuzzproductions.com/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        bindViews();

        webView.getSettings().setJavaScriptEnabled(true);

        final Activity activity = this;

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.GONE);
            }

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, "Error!" + description, Toast.LENGTH_SHORT).show();
            }
        });

        webView.loadUrl(FUZZ_URL);

    }

    private void bindViews() {
        webView = (WebView) findViewById(R.id.webview);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
    }
}
