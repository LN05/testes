package br.com.inf3cm.loginregister;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.loginregister.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class LoginActivity extends AppCompatActivity {

    TextView mTextViewNewUser, mTextViewForgotPassword;
    Button mButtonLogin;
    EditText mEditTextEmail, mEditTextPassword;
    ProgressBar mProgressBarLogin;
    String mUser;
    String mPassword;
    String mEmail;
    String mApiKey;
    SharedPreferences mSharedPreferencesLogin;


    private boolean isRequiredPassword(){
        return TextUtils.isEmpty(mEditTextPassword.getText());
    }


    private boolean isValidEmail(String mEmail){
        if(mEmail==null || mEmail.isEmpty()){
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(mEmail).matches();
    }

    // iniciar uma tela
    private void showOrder(){
        Intent mIntent = new Intent(getApplicationContext(), OrderActivity.class);
        startActivity(mIntent);
        finish();
    }


    private void verifyLogged(){
        if(mSharedPreferencesLogin.getString("logged" , "false").equals("true")) {
            showOrder();
        }


    }


    private void getIpV4(){}


    // funcionalidade para trabalhar
    // com o banco de dados
    private void postDataUsingVolley(){
        mEmail = String.valueOf(mEditTextEmail.getText());
        mPassword = String.valueOf(mEditTextPassword.getText());

        if(!isValidEmail(mEmail)){
            String mTextMessage = getString(R.string.text_email_not_valid);
            Toast.makeText(this , mTextMessage , Toast.LENGTH_SHORT).show();
            return;

        }

        if(isRequiredPassword()){
            String mTextMessage = getString(R.string.text_error_fill_mandatory_information);
            Toast.makeText(this , mTextMessage , Toast.LENGTH_SHORT).show();
            return;
        }

        mProgressBarLogin.setVisibility(View.VISIBLE);

        String mUrl = "http://127.0.0.1/app/login.php";
        RequestQueue mRequestQueue = Volley.newRequestQueue(this);
        StringRequest mStringRequest = new StringRequest(Request.Method.POST, mUrl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                mProgressBarLogin.setVisibility(View.GONE);
                try {
                    JSONObject mJsonObject = new JSONObject(response);
                    String mStatus = mJsonObject.getString("status");
                    String mMessage = mJsonObject.getString("message");
                    if (mStatus.equals("success")) {
                        mUser = mJsonObject.getString("user");
                        mEmail = mJsonObject.getString("email");
                        mApiKey = mJsonObject.getString("apiKey");
                        SharedPreferences.Editor mEditor = mSharedPreferencesLogin.edit();
                        mEditor.putString("logged", "true");
                        mEditor.putString("user", mUser);
                        mEditor.putString("email", mEmail);
                        mEditor.putString("apiKey", mApiKey);
                        mEditor.apply();
                        showOrder();

                    } else {
                        Toast.makeText(getApplicationContext(), mMessage, Toast.LENGTH_LONG).show();
                    }

                } catch (JSONException mJsonException) {
                    mJsonException.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mProgressBarLogin.setVisibility(View.GONE);
                error.printStackTrace();
                Toast.makeText(getApplicationContext() , "Fail to get response: " +error , Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String , String> getParams(){
                Map<String, String> mParams = new HashMap<>();
                mParams.put("email" , mEmail);                  //Put = colocar
                mParams.put("password" , mPassword);
                return mParams;

            }
        };

        mStringRequest.setRetryPolicy(new DefaultRetryPolicy(2*1000, 2, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mRequestQueue.add(mStringRequest);

    }

    public class ClickMyButtonLogin implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            postDataUsingVolley();
        }
    }

    private void showSignUp(){
        Intent mIntent = new Intent(getApplicationContext(), SignUpActivity.class);
        startActivity(mIntent);
        finish();

    }

    public class ClickMyNewUser implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            showSignUp();
        }
    }

    private void showForgotPassword(){
        Intent mIntent = new Intent(getApplicationContext(), ResetPsswordActivity.class);
        startActivity(mIntent);
        finish();

    }

    public class ClickMyForgotPassword implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            showForgotPassword();
        }
    }

    //impedir a visualização do teclado
    public class EditTextAction implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                postDataUsingVolley();
            }
            return false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        mEditTextEmail = findViewById(R.id.editText_email_login);

        mEditTextPassword = findViewById(R.id.editText_password_login);
        mEditTextPassword.setOnEditorActionListener(new EditTextAction()); //comportamento

        mButtonLogin = findViewById(R.id.button_login);
        mButtonLogin.setOnClickListener(new ClickMyButtonLogin()); //comportamento

        mTextViewForgotPassword = findViewById(R.id.textView_forgot_password);
        mTextViewForgotPassword.setOnClickListener(new ClickMyForgotPassword()); //comportamento

        mTextViewNewUser = findViewById(R.id.textView_new_user);
        mTextViewNewUser.setOnClickListener(new ClickMyNewUser()); // comportamento

        mProgressBarLogin = findViewById(R.id.progressBar_login);

        mSharedPreferencesLogin = getSharedPreferences("MyAppName" , MODE_PRIVATE);

        verifyLogged();

    }
}
