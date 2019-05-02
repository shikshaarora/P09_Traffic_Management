package cultoftheunicorn.marvel;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import org.opencv.cultoftheunicorn.marvel.R;

public class DatabaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database);


        WebView dataview = (WebView)findViewById(R.id.dataview);
        dataview.setWebChromeClient(new WebChromeClient());
        dataview.getSettings().setJavaScriptEnabled(true);
        dataview.getSettings().setBuiltInZoomControls(true);
        dataview.loadUrl("https://criminaldetect.000webhostapp.com/Displaystoredtable.php");
    }
    public void backbutton(View v){
        Intent intent= new Intent(this, HomeActivity.class);
        startActivity(intent);
    }


}
