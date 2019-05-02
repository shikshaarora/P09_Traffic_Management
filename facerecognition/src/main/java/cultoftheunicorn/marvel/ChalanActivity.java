package cultoftheunicorn.marvel;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.cultoftheunicorn.marvel.R;

import java.util.HashMap;
import java.util.Map;


public class ChalanActivity extends AppCompatActivity {

    private static final String TAG = "ChalanActivity";
    private static final String URL_FOR_LOGIN = "https://criminaldetect.000webhostapp.com/test/login.php";
    ProgressDialog progressDialog;
    private EditText InputLicence;
    private Button btncheck;
    private Button btnLinkSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chalan);
        InputLicence = (EditText) findViewById(R.id.input_Lno);
        btncheck = (Button) findViewById(R.id.btn_check);
        btnLinkSignup = (Button) findViewById(R.id.btn_link_signup);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(InputLicence.getText().toString());

            }
        });

        btnLinkSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterFormActivity.class);
                startActivity(i);
            }
        });
    }

    private void loginUser( final String lno) {
        // Tag used to cancel the request
        String cancel_req_tag = "Check";
        progressDialog.setMessage("Checking Licence Number...");
        showDialog();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_LOGIN, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");
                        String lno = jObj.getJSONObject("user").getString("lno");
                        String so = jObj.getJSONObject("user").getString("so");
                        String DOI = jObj.getJSONObject("user").getString("DOI");
                        String ValidTill = jObj.getJSONObject("user").getString("ValidTill");
                        String count = jObj.getJSONObject("user").getString("count_in_col");
                        String image_url = jObj.getJSONObject("user").getString("image_url");


                        // Launch User activity
                        Intent intent = new Intent(
                                ChalanActivity.this,
                                RegisterFormActivity2.class);
                        intent.putExtra("username", user);
                        intent.putExtra("lno", lno);
                        intent.putExtra("so", so);
                        intent.putExtra("DOI", DOI);
                        intent.putExtra("ValidTill", ValidTill);
                        intent.putExtra("count", count);
                        intent.putExtra("iname", image_url);

                        startActivity(intent);
                        finish();
                    } else {

                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "Login Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to login url
                Map<String, String> params = new HashMap<String, String>();
                params.put("lno", lno);
                return params;
            }

        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq,cancel_req_tag);
    }

    private void showDialog() {
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void hideDialog() {
        if (progressDialog.isShowing())
            progressDialog.dismiss();
    }

}


