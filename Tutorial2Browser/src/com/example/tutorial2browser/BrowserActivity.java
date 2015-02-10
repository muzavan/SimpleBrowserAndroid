package com.example.tutorial2browser;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class BrowserActivity extends Activity {
  EditText eView;
  WebView wView;
  Button bView;
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    // To keep this example simple, we allow network access
    // in the user interface thread
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
        .permitAll().build();
    StrictMode.setThreadPolicy(policy);

    setContentView(R.layout.activity_browser);
    eView = (EditText) findViewById(R.id.eView);
    wView = (WebView) findViewById(R.id.wView);
    bView = (Button)findViewById(R.id.bView);
    if(getIntent().hasExtra("url")){
		try {
			URL url;
			String ref = getIntent().getExtras().getString("url");
			Log.w("URL", ref);
			url = new URL(ref);
		    URLConnection conn = url.openConnection();
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    String web ="",tmp;
		    while((tmp=in.readLine())!=null)
		    	web+=tmp;
		    wView.loadData(web, "text/html", "utf-8");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.w("Error", e.getMessage().toString());
		}
    }
    
    bView.setOnClickListener(new OnClickListener() {

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
//			Intent intent = new Intent(getApplicationContext(),BrowserActivity.class);
//			intent.putExtra("url", eView.getText().toString());
//			startActivity(intent);
			
			try {
				URL url;
				String ref = eView.getText().toString();
				Log.w("URL", ref);
				url = new URL(ref);
			    URLConnection conn = url.openConnection();
			    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			    String web ="",tmp;
			    while((tmp=in.readLine())!=null)
			    	web+=tmp;
			    wView.loadData(web, "text/html", "utf-8");
			    eView.selectAll();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.w("Error", e.getMessage().toString());
			}
		}
	
	});
    

  }
} 

