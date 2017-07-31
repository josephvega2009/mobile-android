package pe.com.mucontact.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.models.User;

public class AboutUserActivity extends AppCompatActivity {
    private TextView displayNameTextView;
    private TextView emailTextView;
    private TextView userTypeTextView;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        displayNameTextView = (TextView) findViewById(R.id.displayNameTextView);
        emailTextView = (TextView) findViewById(R.id.emailTextView);
        userTypeTextView = (TextView) findViewById(R.id.userTypeTextView);
        user = MuContactApp.getInstance().getCurrentUser();

        displayNameTextView.setText(user.getDisplayName());
        emailTextView.setText(user.getEmail());
        userTypeTextView.setText(user.getUserType());
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_profile_sources, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_profile:
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}