package pe.com.mucontact.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import pe.com.mucontact.R;
import pe.com.mucontact.network.NewApiService;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    TextView signInTextView;
    Button registerButton;
    private static String TAG = "MuContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        nameEditText = (EditText) findViewById(R.id.nameTextInputEditText);
        emailEditText = (EditText) findViewById(R.id.emailTextInputEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordInputEditText);
        signInTextView = (TextView) findViewById(R.id.signInTextView);
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext()
                        .startActivity(new Intent(v.getContext(),
                                LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
            }
        });
        registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        AndroidNetworking.post(NewApiService.SIGNUP_URL)
                .addBodyParameter("email", emailEditText.getText().toString())
                .addBodyParameter("displayName", nameEditText.getText().toString())
                .addBodyParameter("password",passwordEditText.getText().toString())
                .addBodyParameter("userType", "Craftmen")
                .addBodyParameter("password", passwordEditText.getText().toString())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "User saved", Toast.LENGTH_SHORT).show();

                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to save user", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
