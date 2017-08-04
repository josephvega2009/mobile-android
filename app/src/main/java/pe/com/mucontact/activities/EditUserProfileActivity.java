package pe.com.mucontact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.models.User;
import pe.com.mucontact.network.NewApiService;

public class EditUserProfileActivity extends AppCompatActivity {
    private EditText displayNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    User user;
    String TAG = "MuContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        displayNameEditText = (EditText) findViewById(R.id.displayNameInputEditText);
        emailEditText = (EditText) findViewById(R.id.emailInputEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordInputEditText);

        user = MuContactApp.getInstance().getCurrentUser();

        displayNameEditText.setText(user.getDisplayName().toString());
        emailEditText.setText(user.getEmail().toString());
        passwordEditText.setText(user.getPassword());

        Button updateInfoButton = (Button) findViewById(R.id.updateInfoButton);
        updateInfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(displayNameEditText.getText().toString().isEmpty()
                        || emailEditText.getText().toString().isEmpty()
                        || passwordEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill empty field", Toast.LENGTH_SHORT).show();
                }
                else{
                    editProfile();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    private void editProfile() {
        AndroidNetworking.put(NewApiService.USERS_EDIT_URL)
                .addBodyParameter("email", emailEditText.getText().toString())
                .addBodyParameter("displayName", displayNameEditText.getText().toString())
                .addBodyParameter("password", passwordEditText.getText().toString())
                .addPathParameter("user_id", user.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        user.setDisplayName(displayNameEditText.getText().toString());
                        user.setEmail(emailEditText.getText().toString());
                        Toast.makeText(getApplicationContext(), "Profile updated!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), AboutUserActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to update profile", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
