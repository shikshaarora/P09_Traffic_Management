package cultoftheunicorn.marvel;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
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

import org.json.JSONException;
import org.json.JSONObject;
import org.opencv.cultoftheunicorn.marvel.R;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class RegisterFormActivity extends AppCompatActivity {
//test
    private static final int REQUEST_GALLERY = 0;
    private static final int REQUEST_CAMERA = 1;
    private static final int MY_PERMISSIONS_REQUESTS = 0;
    private Uri imageUri;
    private TextView detectedTextView;
    //test end
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUESTS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    // FIXME: Handle this case the user denied to grant the permissions
                }
                break;
            }
            default:
                // TODO: Take care of this case later
                break;
        }
    }

    private void requestPermissions()
    {
        List<String> requiredPermissions = new ArrayList<>();

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requiredPermissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            requiredPermissions.add(Manifest.permission.CAMERA);
        }

        if (!requiredPermissions.isEmpty()) {
            ActivityCompat.requestPermissions(this,
                    requiredPermissions.toArray(new String[]{}),
                    MY_PERMISSIONS_REQUESTS);
        }
    }


    private static final String TAG = "RegisterFormActivity";
    private static final String URL_FOR_REGISTRATION = "https://criminaldetect.000webhostapp.com/test/register.php";
    ProgressDialog progressDialog;

    private EditText signupInputlno, signupInputName, signupInputSO, signupInputDOI, signupInputValidTill,
            signupInputLplate, signupInputViolation, signupInputComments, signupInputdate, signupInputplace,signupInputiname,
            signupInputamount; //so= son of
    private Button btnSubmit;
    private Button btnLinkLogin;
    private RadioGroup genderRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_form);


        //test
        requestPermissions();
        findViewById(R.id.take_a_photoo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String filename = System.currentTimeMillis() + ".jpg";

                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.TITLE, filename);
                values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
                imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, REQUEST_CAMERA);
            }
        });

        //test end



        // Progress dialog
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);

        signupInputlno = (EditText) findViewById(R.id.signup_input_lno);
        signupInputName = (EditText) findViewById(R.id.signup_input_name);
        signupInputSO = (EditText) findViewById(R.id.signup_input_so);
        signupInputDOI = (EditText) findViewById(R.id.signup_input_DOI);
        signupInputValidTill = (EditText) findViewById(R.id.signup_input_ValidTill);

        signupInputLplate = (EditText) findViewById(R.id.signup_input_Lplate);
        signupInputViolation = (EditText) findViewById(R.id.signup_input_Violation);
        signupInputComments = (EditText) findViewById(R.id.signup_input_Comments);
        signupInputdate = (EditText) findViewById(R.id.signup_input_date);
        signupInputplace = (EditText) findViewById(R.id.signup_input_place);
        signupInputamount = (EditText) findViewById(R.id.signup_input_amount);
        signupInputiname = (EditText) findViewById(R.id.signup_input_iname);

        btnSubmit = (Button) findViewById(R.id.btn_signup);
        btnLinkLogin = (Button) findViewById(R.id.btn_link_login);

        genderRadioGroup = (RadioGroup) findViewById(R.id.gender_radio_group);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitForm();
            }
        });
        btnLinkLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getApplicationContext(), ChalanActivity.class);
                startActivity(i);
            }
        });
    }


    private void submitForm() {

        int selectedId = genderRadioGroup.getCheckedRadioButtonId();
        String gender;
        if(selectedId == R.id.female_radio_btn)
            gender = "Female";
        else
            gender = "Male";

        registerUser(
                signupInputlno.getText().toString(),
                signupInputName.getText().toString(),
                signupInputSO.getText().toString(),
                gender,
                signupInputDOI.getText().toString(),
                signupInputValidTill.getText().toString(),

                signupInputLplate.getText().toString(),
                signupInputViolation.getText().toString(),
                signupInputComments.getText().toString(),
                signupInputdate.getText().toString(),
                signupInputplace.getText().toString(),
                signupInputamount.getText().toString(),
                imageUri,
                signupInputiname.getText().toString()

        );
    }

    private void registerUser(final String lno, final String name, final String so,
                              final String gender, final String DOI, final String ValidTill, final String Lplate, final String Violation
            , final String Comments, final String date, final String place, final String amount, final Uri Image, final String iname) {
        // Tag used to cancel the request
        String cancel_req_tag = "register";

        progressDialog.setMessage("Adding you ...");
        showDialog();

        StringRequest strReq = new StringRequest(Request.Method.POST,
                URL_FOR_REGISTRATION, new Response.Listener<String>() {

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
                                RegisterFormActivity.this,
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
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("lno", lno);
                params.put("name", name);
                params.put("so", so);
                params.put("gender", gender);
                params.put("DOI", DOI);
                params.put("ValidTill", ValidTill);


                params.put("Lplate", Lplate);
                params.put("Violation", Violation);
                params.put("Comments", Comments);
                params.put("date", date);
                params.put("place", place);
                params.put("amount", amount);
                //params.put("Image", Image);
                params.put("iname", iname);
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