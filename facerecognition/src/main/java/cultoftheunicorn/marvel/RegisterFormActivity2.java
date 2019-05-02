package cultoftheunicorn.marvel;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.annotations.SerializedName;

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.cultoftheunicorn.marvel.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cultoftheunicorn.marvel.RetrieveActivity.Spacecraft;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;


public class RegisterFormActivity2 extends AppCompatActivity {

    private static final String TAG = "RegisterFormActivity2";
    private static final String URL_FOR_UPDATE = "https://criminaldetect.000webhostapp.com/test/register2.php";
    ProgressDialog progressDialog;

   private TextView signupInputRecordsCount;
   private EditText signupInputlnoo,signupInputNamee,
    signupInputSOO, signupInputDOII, signupInputValidTilll,signupInputLplatee;
   private EditText signupInputViolationn, signupInputCommentss, signupInputdatee,
           signupInputplacee, signupInputamountt,signupInputinamee;
    private Button btnSubmit,btnLinkLoginn;
    private RadioGroup genderrRadioGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form2);
        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);


        Bundle bundle = getIntent().getExtras();
        String user = bundle.getString("username");
        String lno = bundle.getString("lno");
        String so = bundle.getString("so");
        String DOI = bundle.getString("DOI");
        String ValidTill = bundle.getString("ValidTill");
        String count = bundle.getString("count");
        String iname = bundle.getString("iname");

        signupInputRecordsCount = (TextView) findViewById(R.id.signup_input_RecordsCount);
        signupInputlnoo = (EditText) findViewById(R.id.signup_input_lnoo);
        signupInputNamee = (EditText) findViewById(R.id.signup_input_namee);
        signupInputSOO = (EditText) findViewById(R.id.signup_input_soo);
        signupInputDOII = (EditText) findViewById(R.id.signup_input_DOII);
        signupInputValidTilll = (EditText) findViewById(R.id.signup_input_ValidTilll);

        signupInputLplatee= (EditText) findViewById(R.id.signup_input_Lplatee);
        signupInputViolationn = (EditText) findViewById(R.id.signup_input_Violationn);
        signupInputCommentss = (EditText) findViewById(R.id.signup_input_Commentss);
        signupInputdatee = (EditText) findViewById(R.id.signup_input_datee);
        signupInputplacee = (EditText) findViewById(R.id.signup_input_placee);
        signupInputamountt = (EditText) findViewById(R.id.signup_input_amountt);
        signupInputinamee = (EditText) findViewById(R.id.signup_input_inamee);
        genderrRadioGroup = (RadioGroup) findViewById(R.id.genderr_radio_group);

        btnSubmit = (Button) findViewById(R.id.btn_signupp);
        btnLinkLoginn = (Button) findViewById(R.id.btn_link_loginn);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        btnLinkLoginn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ChalanActivity.class);
                startActivity(i);
            }
        });


        signupInputRecordsCount.setText(count);
        signupInputNamee.setText(user);
        signupInputlnoo.setText(lno);
        signupInputSOO.setText(so);
        signupInputDOII.setText(DOI);
        signupInputValidTilll.setText(ValidTill);
        signupInputinamee.setText(iname);

        // Progress dialog

    }


    private void submitForm() {

        int selectedId = genderrRadioGroup.getCheckedRadioButtonId();
        String gender;
        if(selectedId == R.id.femalee_radio_btn)
            gender = "Female";
        else
            gender = "Male";

        registerUser(
                signupInputlnoo.getText().toString(),
                signupInputNamee.getText().toString(),
                signupInputSOO.getText().toString(),
                gender,
                signupInputDOII.getText().toString(),
                signupInputValidTilll.getText().toString(),

                signupInputLplatee.getText().toString(),
                signupInputViolationn.getText().toString(),
                signupInputCommentss.getText().toString(),
                signupInputdatee.getText().toString(),
                signupInputplacee.getText().toString(),
                signupInputamountt.getText().toString(),
                signupInputinamee.getText().toString()

        );
    }

    private void registerUser(final String lnoo, final String namee, final String soo,
                              final String genderr, final String DOII, final String ValidTilll, final String Lplatee, final String Violationn
            , final String Commentss, final String datee, final String placee, final String amountt, final String inamee) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";

        progressDialog.setMessage("Adding you ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_UPDATE, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                Log.d(TAG, "Register Response: " + response.toString());
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");

                    if (!error) {
                        String user = jObj.getJSONObject("user").getString("name");

                        Toast.makeText(getApplicationContext(), "Hi " + user +", Added successfully!", Toast.LENGTH_SHORT).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                RegisterFormActivity2.this,
                                ChalanActivity.class);
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
                Log.e(TAG, "Registration Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();
                hideDialog();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                // Posting params to update url
                Map<String, String> params = new HashMap<String, String>();
                params.put("lnoo", lnoo);
                params.put("namee", namee);
                params.put("soo", soo);
                params.put("genderr", genderr);
                params.put("DOII", DOII);
                params.put("ValidTilll", ValidTilll);

                params.put("Lplatee", Lplatee);
                params.put("Violationn", Violationn);
                params.put("Commentss", Commentss);
                params.put("datee", datee);
                params.put("placee", placee);
                params.put("amountt", amountt);
                params.put("inamee", inamee);
                return params;
            }
        };
        // Adding request to request queue
        AppSingleton.getInstance(getApplicationContext()).addToRequestQueue(strReq, cancel_req_tag);
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


