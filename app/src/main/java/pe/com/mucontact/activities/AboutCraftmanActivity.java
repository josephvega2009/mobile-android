package pe.com.mucontact.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.models.Craftman;

/**
 * Created by Franklin on 26/07/2017.
 */

public class AboutCraftmanActivity extends AppCompatActivity {
    private TextView nameTextView;
    private TextView descriptionTextView;
    private TextView phoneTextView;
    private TextView levelTextView;
    Craftman craftman;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_craftman);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        descriptionTextView = (TextView) findViewById(R.id.descriptionTextView);
        phoneTextView = (TextView) findViewById(R.id.phoneTextView);
        levelTextView = (TextView) findViewById(R.id.levelTextView);
        craftman = MuContactApp.getInstance().getCurrentCraftman();

        nameTextView.setText(craftman.getUser().getDisplayName());
        descriptionTextView.setText(craftman.getDescription());
        phoneTextView.setText(craftman.getPhone());
        levelTextView.setText(craftman.getLevel());
    }
}
