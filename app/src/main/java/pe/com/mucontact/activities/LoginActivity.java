package pe.com.mucontact.activities;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.models.Musician;
import pe.com.mucontact.models.User;
import pe.com.mucontact.network.NewApiService;

public class LoginActivity extends AppCompatActivity {
    Intent intent;
    String TAG = "MuContact";
    EditText emailEditText;
    EditText passwordEditText;
    TextView signUpTextView;
    boolean correctEmail = false;
    boolean correctPassword = false;
    String email;
    String password;
    ProgressBar loginProgressBar;
    User user;
    String token;
    List<Musician> musicians;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        emailEditText = (EditText) findViewById(R.id.emailTextInputEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordInputEditText);
        loginProgressBar = (ProgressBar) findViewById(R.id.loginProgressBar);
        loginProgressBar.setVisibility(View.GONE);
        ((Button) findViewById(R.id.loginButton))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        loginProgressBar.setVisibility(View.VISIBLE);
                        intent = new Intent (v.getContext(), MainActivity.class);
                        if(Patterns.EMAIL_ADDRESS.matcher(emailEditText.getText().toString()).matches()==false){
                            emailEditText.setError("Invalid email");
                            correctEmail = false;
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            emailEditText.setError(null);
                            correctEmail = true;
                        }
                        if(passwordEditText.getText().toString().length() == 0) {
                            passwordEditText.setError("Invalid password");
                            correctPassword = false;
                            loginProgressBar.setVisibility(View.INVISIBLE);
                        } else {
                            passwordEditText.setError(null);
                            correctPassword = true;
                        }
                        if(correctEmail == true && correctPassword == true) {
                            email = emailEditText.getText().toString();
                            password = passwordEditText.getText().toString();
                            login();
                        }
                    }
                });
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext()
                        .startActivity(new Intent(v.getContext(),
                                RegisterActivity.class));
            }
        });
    }

    private void login() {
        AndroidNetworking.post(NewApiService.SIGNIN_URL)
                .addBodyParameter("email", email)
                .addBodyParameter("password", password)
                .setTag(TAG)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            user = User.build(response.getJSONObject("user"));
                            if(user.getUserType().equals("Musician")) {
                                user.setPassword(password);
                                token = response.getString("token");
                                MuContactApp.getInstance().setCurrentToken(token);
                                MuContactApp.getInstance().setCurrentUser(user);
                                startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                                updateMusician();
                            } else {
                                Toast.makeText(getApplicationContext(), "You need a musician account", Toast.LENGTH_SHORT).show();
                                loginProgressBar.setVisibility(View.INVISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        loginProgressBar.setVisibility(View.INVISIBLE);
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "User or password incorrect", Toast.LENGTH_SHORT).show();
                        loginProgressBar.setVisibility(View.INVISIBLE);
                    }
                });
    }

    private void updateMusician() {
        AndroidNetworking.get(NewApiService.MUSICIAN_USER_URL)
                .addPathParameter("user_id", user.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if(response == null) return;
                        try {
                            musicians = Musician.build(response.getJSONArray("musician"), user);
                            MuContactApp.getInstance().setCurrentMusician(musicians.get(0));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Error in musician profile", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }
}
