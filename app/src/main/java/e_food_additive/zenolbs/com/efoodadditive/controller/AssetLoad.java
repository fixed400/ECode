package e_food_additive.zenolbs.com.efoodadditive.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;

import e_food_additive.zenolbs.com.efoodadditive.R;

public class AssetLoad extends AppCompatActivity {

    private WebView mButterflyWebView;
    private String pathHtml = "e100_e199/e101.html";

    private void init() {
        mButterflyWebView = (WebView) findViewById(R.id.webView1);
    }
// если не использовать этих функци вылетает
    private void init2() {
      //  mButterflyWebView = (WebView) findViewById(R.id.webView2);
      //  init();
    }

    private void init3() {
     //   mButterflyWebView = (WebView) findViewById(R.id.webView3);
       // init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //------------------------------------------
        Intent intent = getIntent();

        String fName = intent.getStringExtra("fname");

//=============================================================
        pathHtml =  fName;

  // глючит разобратся
        if(pathHtml.equals("e_food.html"))
        {

            init2();
        }

        else if(pathHtml.equals("codex_alimentarius.html")) {

            init3();
        }

        else  {
            setContentView(R.layout.activity_e_all);

            init();
        }

        loadHtmlPage();


        AdView adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }


    private String getHtmlFromAsset() {
        InputStream is;
        StringBuilder builder = new StringBuilder();
        String htmlString = null;
        try {

            is = getAssets().open(pathHtml);

            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null) {
                    builder.append(line);
                }
                htmlString = builder.toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlString;
    }// ---end getHtmlFromAsset

    //Loads html page with the content
    private void loadHtmlPage() {
        String htmlString = getHtmlFromAsset();
        if (htmlString != null) {

            mButterflyWebView.loadDataWithBaseURL(null, htmlString, "text/html", "UTF-8", null);

        }
        else {
            Toast.makeText(this, R.string.no_such_page, Toast.LENGTH_LONG).show();
        }
    }




}
