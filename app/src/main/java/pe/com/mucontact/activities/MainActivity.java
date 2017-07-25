package pe.com.mucontact.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import pe.com.mucontact.R;
import pe.com.mucontact.fragments.HomeFragment;
import pe.com.mucontact.fragments.PublicationFragment;
import pe.com.mucontact.fragments.RewardFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((BottomNavigationView) findViewById(R.id.navigation))
                .setOnNavigationItemSelectedListener(
                        new BottomNavigationView.OnNavigationItemSelectedListener() {
                            @Override
                            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                                return navigateAccordingTo(item.getItemId());
                            }
                        });
        navigateAccordingTo(R.id.navigation_home);
    }

    private Fragment getFragmentFor(int id) {
        switch (id) {
            case R.id.navigation_home: return new HomeFragment();
            case R.id.navigation_publications: return new PublicationFragment();
            case R.id.navigation_rewards: return new RewardFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id) {
        try {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, getFragmentFor(id))
                    .commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}