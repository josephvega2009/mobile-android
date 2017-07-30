package pe.com.mucontact.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import pe.com.mucontact.MuContactApp;
import pe.com.mucontact.R;
import pe.com.mucontact.models.Publication;
import pe.com.mucontact.models.User;
import pe.com.mucontact.network.NewApiService;

public class EditPublicationActivity extends AppCompatActivity {
    private final String photoRute = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/misfotos/";
    private File file = new File(photoRute);
    private Button camaraButton;
    private EditText instrumentEditText;
    private EditText descriptionEditText;
    private EditText locationAtEditText;
    User user;
    Publication publication;
    String TAG = "MuContact";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_publication);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        instrumentEditText = (EditText) findViewById( R.id.instrumentInputEditText);
        descriptionEditText = (EditText) findViewById( R.id.descriptionInputEditText);
        locationAtEditText = (EditText) findViewById( R.id.locationAtInputEditText);
        user = MuContactApp.getInstance().getCurrentUser();
        publication = MuContactApp.getInstance().getCurrentPublication();

        instrumentEditText.setText(publication.getInstrument());
        descriptionEditText.setText(publication.getDescription());
        locationAtEditText.setText(publication.getLocationReference());

        file.mkdirs();
        camaraButton = (Button) findViewById(R.id.camaraButton);
        camaraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String file = photoRute + getCode() + ".jpg";
                File camaraPhoto = new File( file );
                try {
                    camaraPhoto.createNewFile();
                } catch (IOException ex) {
                    Log.e("ERROR ", "Error:" + ex);
                }
                Uri uri = Uri.fromFile( camaraPhoto );
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(cameraIntent, 0);
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addPublicationFloatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(instrumentEditText.getText().toString().isEmpty()
                        || descriptionEditText.getText().toString().isEmpty()
                        || locationAtEditText.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Fill empty fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    editPublication();
                }
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_publication_sources, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete_publication:
                deletePublication();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private String getCode() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyymmddhhmmss");
        String date = dateFormat.format(new Date() );
        String photoCode = "pic_" + date;
        return photoCode;
    }

    private void editPublication() {
        AndroidNetworking.put(NewApiService.PUBLICATION_EDIT_URL)
                .addBodyParameter("instrument", instrumentEditText.getText().toString())
                .addBodyParameter("description", descriptionEditText.getText().toString())
                .addBodyParameter("locationAt", locationAtEditText.getText().toString())
                .addBodyParameter("user", user.get_id())
                .addPathParameter("publication_id", publication.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Publication save", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to save publication", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void deletePublication() {
        AndroidNetworking.delete(NewApiService.PUBLICATION_EDIT_URL)
                .addPathParameter("publication_id", publication.get_id())
                .setTag(TAG)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(getApplicationContext(), "Publication delete", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    @Override
                    public void onError(ANError error) {
                        Toast.makeText(getApplicationContext(), "Failed to delete publication", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
