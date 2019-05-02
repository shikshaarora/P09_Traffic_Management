package cultoftheunicorn.marvel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.opencv.cultoftheunicorn.marvel.R;

public class submit extends AppCompatActivity {
    TextView txtText;
    Button btnCopyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);
        txtText= (TextView) this.findViewById(R.id.recyclerView);
        btnCopyText= (Button) this.findViewById(R.id.btnCopy);

        btnCopyText.setOnClickListener(new View.OnClickListener() {
            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                ((ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE)).setText(txtText.getText().toString());
            }
        });
    }
    public void backbutton(View v){
        Intent intent= new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}
