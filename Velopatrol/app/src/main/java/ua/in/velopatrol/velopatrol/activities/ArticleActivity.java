package ua.in.velopatrol.velopatrol.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.rightutils.rightutils.utils.CacheUtils;

import java.io.IOException;

import ua.in.velopatrol.velopatrol.R;


/**
 * Created by Anton Maniskevich on 25.07.2014.
 */
public class ArticleActivity extends Activity {

	private static final String TAG = ArticleActivity.class.getSimpleName();

    private TextView title;
    private WebView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_articles);
        title = (TextView) findViewById(R.id.article_title);
        text = (WebView) findViewById(R.id.article_text);


        WebSettings settings = text.getSettings();
        settings.setDefaultTextEncodingName("utf-8");

        Intent intent = getIntent();
        title.setText(intent.getStringExtra("title"));
        text.loadData(intent.getStringExtra("text"), "text/html", "utf-8");
    }

}
