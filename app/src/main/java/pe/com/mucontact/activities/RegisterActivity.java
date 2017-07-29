package pe.com.mucontact.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import pe.com.mucontact.R;

public class RegisterActivity extends AppCompatActivity {
    EditText nameEditText;
    EditText emailEditText;
    EditText passwordEditText;
    TextView signInTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        nameEditText = (EditText) findViewById(R.id.nameTextInputEditText);
        emailEditText = (EditText) findViewById(R.id.emailTextInputEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordInputEditText);
        signInTextView = (TextView) findViewById(R.id.signInTextView);
        signInTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext()
                        .startActivity(new Intent(v.getContext(),
                                LoginActivity.class));
            }
        });
    }

}
