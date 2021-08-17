package com.example.t_guide;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

public class FurtherReadingActivity extends AppCompatActivity {

    private DatabaseHelper myDb;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_further_reading);

        myDb = new DatabaseHelper(this);

        String culture_id = getIntent().getStringExtra("culture_id");

        webView = findViewById(R.id.wv);

        webView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }
                return false;
            }
        });

        //get Culture respective link - direct to respective link based on the intent data passed from previous fragment
        Cursor res = myDb.getCultureData(culture_id);
        if(res.moveToFirst()){
            webView.loadUrl(res.getString(12));
        }
    }
}