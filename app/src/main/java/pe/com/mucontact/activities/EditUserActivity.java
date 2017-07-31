package pe.com.mucontact.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
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

public class EditUserActivity extends AppCompatActivity {
    private EditText displayNameEditText;
    private FloatingActionButton addUserEditFloatingActionButton;
    User user;
    String TAG = "MuContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        displayNameEditText = (EditText) findViewById( R.id.displayNameInputEditText);
        user = MuContactApp.getInstance().getCurrentUser();

        displayNameEditText.setText(user.getDisplayName());

        addUserEditFloatingActionButton = (FloatingActionButton) findViewById(R.id.addUserEditFloatingActionButton);
        addUserEditFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(displayNameEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill empty fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    editUser();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void editUser() {
        AndroidNetworking.put(NewApiService.USER_EDIT_URL)
                .addBodyParameter("displayName", displayNameEditText.getText().toString())
                .addBodyParameter("user_id", user.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "User save", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to save user", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
