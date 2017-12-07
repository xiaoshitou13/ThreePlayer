package byc.by.com.threeplayer.my;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import byc.by.com.threeplayer.R;

public class MywebViewClass extends AppCompatActivity {

    private WebView mGywebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myweb_view_class);
        initView();


    }

    private void initView() {
        mGywebview = (WebView) findViewById(R.id.gywebview);
        Intent in = getIntent();
        String boke = in.getStringExtra("bo");
        String git = in.getStringExtra("git");


            mGywebview.loadUrl(boke);

            mGywebview.loadUrl(git);

    }
}
