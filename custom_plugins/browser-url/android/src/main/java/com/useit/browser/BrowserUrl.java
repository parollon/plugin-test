package com.useit.browser;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.net.URI;
import java.net.URISyntaxException;

public class BrowserUrl extends AppCompatActivity {

  public static final String ACTION_PAGE_LOADED = "com.example.PAGE_LOADED";
  public static final String ACTION_URL_CHANGED = "com.example.URL_CHANGED";
  public static final String EXTRA_CURRENT_URL = "extra_current_url";
  private static final String REDIRECT_URL = "https://www.firabarcelona.com/?code=";

  private WebView webView;
  private LinearLayout toolbar;
  private ImageButton closeButton;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    String url = getIntent().getStringExtra("url");
    String color = getIntent().getStringExtra("color");
    String title = getIntent().getStringExtra("title");

    setTheme(R.style.AppTheme_NoActionBar2);

    // Create the toolbar
    toolbar = new LinearLayout(this);
    toolbar.setLayoutParams(new LinearLayout.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      getResources().getDimensionPixelSize(R.dimen.toolbar_height)
    ));
    toolbar.setBackgroundColor(Color.parseColor(color));
    toolbar.setOrientation(LinearLayout.HORIZONTAL);
    toolbar.setGravity(Gravity.CENTER_VERTICAL);

    // Create the close button
    closeButton = new ImageButton(this);
    closeButton.setLayoutParams(new LinearLayout.LayoutParams(
      getResources().getDimensionPixelSize(R.dimen.toolbar_height),
      ViewGroup.LayoutParams.MATCH_PARENT
    ));
    closeButton.setImageResource(R.drawable.close);
    closeButton.setBackgroundColor(Color.TRANSPARENT);
    closeButton.setOnClickListener(v -> finish());

    toolbar.addView(closeButton);

    // Create the title TextView
    TextView titleTextView = new TextView(this);
    titleTextView.setLayoutParams(new LinearLayout.LayoutParams(
      ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.MATCH_PARENT
    ));
    titleTextView.setText(title);
    titleTextView.setTextColor(Color.WHITE);
    titleTextView.setTypeface(null, Typeface.BOLD);
    titleTextView.setTextSize(18);
    titleTextView.setGravity(Gravity.CENTER_VERTICAL);

    toolbar.addView(titleTextView);

    // Create the WebView
    webView = new WebView(this);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

    LinearLayout mainLayout = new LinearLayout(this);
    mainLayout.setOrientation(LinearLayout.VERTICAL);
    mainLayout.addView(toolbar);
    mainLayout.addView(webView);

    setContentView(mainLayout);

    webView.setWebViewClient(new WebViewClient() {
      @Override
      public boolean shouldOverrideUrlLoading(WebView view, String url) {
        // Handle OAuth redirect URL
        if (url.startsWith(REDIRECT_URL)) {
          String code = getCodeFromUrl(url);
          Log.i("TOKEN_LINK", code);
          Intent intent = new Intent(ACTION_URL_CHANGED);
          intent.putExtra(EXTRA_CURRENT_URL, code);
          LocalBroadcastManager.getInstance(BrowserUrl.this)
            .sendBroadcast(intent);
          finishActivity();
          return true;
        }

        return super.shouldOverrideUrlLoading(view, url);
      }

      @Override
      public void onPageFinished(WebView view, String url) {
        LocalBroadcastManager.getInstance(BrowserUrl.this)
          .sendBroadcast(new Intent(ACTION_PAGE_LOADED));
      }
    });

    if (url != null) {
      webView.loadUrl(url);
    }

    closeButton.setOnClickListener(view -> {
      Intent intent = new Intent(ACTION_URL_CHANGED);
      LocalBroadcastManager.getInstance(BrowserUrl.this)
              .sendBroadcast(intent);
      finishActivity();
    });
  }

  private static String getCodeFromUrl(String url) {
    try {
      URI uri = new URI(url);
      String query = uri.getQuery();
      String[] params = query.split("&");
      for (String param : params) {
        String[] keyValue = param.split("=");
        if (keyValue[0].equals("code")) {
          return keyValue[1];
        }
      }
    } catch (URISyntaxException e) {
      e.printStackTrace();
    }
    return null;
  }

  private void finishActivity() {
    finish();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (webView != null) {
      webView.destroy();
    }
  }
}
