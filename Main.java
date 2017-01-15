package info.jasonbutz.android.web;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.app.DownloadManager;
import android.app.DownloadManager.Request;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuInflater;
import android.widget.Toast;


public class Main extends Activity {

    WebView wv;
    ProgressBar progressBar;
	/** Called when the activity is first created. */
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);  // This needs to be done before
										// trying to findViewById
										// I imagine it sets up where to look
		//
		Resources res = getResources();
		wv = (WebView) findViewById(R.id.my_webview);
        progressBar = (ProgressBar) findViewById(R.id.progressBar1);
		WebSettings webSettings = wv.getSettings();
		//
		wv.setVisibility(View.INVISIBLE);
		webSettings.setDatabaseEnabled(true);
		webSettings.setGeolocationEnabled(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setJavaScriptEnabled(true);
		webSettings.setSupportMultipleWindows(false);

		wv.setWebViewClient(new WebViewClient()
        {

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
				WebView wv = (WebView) findViewById(R.id.my_webview);

				progressBar.setVisibility(View.GONE);
				wv.setVisibility(View.VISIBLE);
			}

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                // TODO Auto-generated method stub
                //super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

		//wv.loadUrl(res.getString(R.string.url));
        wv.loadUrl("http://dl.roms4droid.com/r/.leecherdirectory.php");

        wv.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String s, String s2, String s3, String s4, long l) {
                /*Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(s));
                i.setType(MIME_TYpe_PDF);
                startActivity(i);*/

                DownloadManager mManager = (DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                Request mRqRequest = new Request(
                        Uri.parse(s));
                mRqRequest.setDescription("Downloaded file from Roms4Droid");
//        mRqRequest.setDestinationUri(Uri.parse("give your local path"));
                long idDownLoad=mManager.enqueue(mRqRequest);

            }
        });
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// Check if the key event was the Back button and if there's history
		WebView wv = (WebView) findViewById(R.id.my_webview);
		if ((keyCode == KeyEvent.KEYCODE_BACK) && wv.canGoBack()) {
			wv.goBack();
			return true;
		}
		// If it wasn't the Back key or there's no web page history, bubble up
		// to the default
		// system behavior (probably exit the activity)
		return super.onKeyDown(keyCode, event);
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

       // super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mymenu, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.download:
                Intent mView = new Intent();
                mView.setAction(DownloadManager.ACTION_VIEW_DOWNLOADS);
                startActivity(mView);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
