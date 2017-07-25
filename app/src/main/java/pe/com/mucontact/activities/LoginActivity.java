package pe.com.mucontact.activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v4.util.PatternsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import pe.com.mucontact.R;
import pe.com.mucontact.network.NewApiService;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    String TAG = "MuContact";
    EditText emailTextInputEditText;
    EditText passwordTextInputEditText;
    boolean correctEmail = false;
    boolean correctPassword = false;
    String email;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailTextInputEditText = (EditText) findViewById(R.id.emailTextInputEditText);
        passwordTextInputEditText = (EditText) findViewById(R.id.passwordInputEditText);
        ((Button) findViewById(R.id.loginButton))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent = new Intent (v.getContext(), MainActivity.class);
                        if(Patterns.EMAIL_ADDRESS.matcher(emailTextInputEditText.getText().toString()).matches()==false){
                            emailTextInputEditText.setError("Invalid email");
                            correctEmail = false;
                        } else {
                            emailTextInputEditText.setError(null);
                            correctEmail = true;
                        }
                        if(passwordTextInputEditText.getText().toString()==null) {
                            passwordTextInputEditText.setError("Invalid password");
                            correctPassword = false;
                        } else {
                            passwordTextInputEditText.setError(null);
                            correctPassword = true;
                        }
                        if(correctEmail == true && correctPassword == true) {
                            email = emailTextInputEditText.getText().toString();
                            password = passwordTextInputEditText.getText().toString();
                            login();
                        }
                    }
                });
    }

    private void login() {
        AndroidNetworking.post(NewApiService.SIGNIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        startActivity(intent);
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "User or password incorrect", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
