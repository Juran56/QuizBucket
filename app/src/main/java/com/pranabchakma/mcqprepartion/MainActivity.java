package com.pranabchakma.mcqprepartion;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.pranabchakma.mcqprepartion.Fragments.AboutFragment;
import com.pranabchakma.mcqprepartion.Fragments.HelpFragment;
import com.pranabchakma.mcqprepartion.Fragments.HistoryFragment;
import com.pranabchakma.mcqprepartion.Fragments.ProfileFragment;
import com.pranabchakma.mcqprepartion.Fragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AdView adView;
    private FirebaseAnalytics mFirebaseAnalytics;

    private static final int TIME_INTERVAL = 1000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;

    DrawerLayout drawer;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, "ca-app-pub-7485935816424837~3698962005");
        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        adView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        adView.loadAd(adRequest);

        Toolbar toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, new MainFragment())
                    .commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }

        fragment = new MainFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_settings:
                Intent i = new Intent(this,SettingsActivity.class);
                startActivity(i);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        switch (item.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction()
                        .add(new MainFragment(), "MessageFragment")
                        .addToBackStack("MessageFragment")
                        .replace(R.id.fragment_container,
                                new MainFragment()).commit();
                break;
            case R.id.nav_Profile:
                getSupportFragmentManager().beginTransaction()
                        .add(new MainFragment(), "MessageFragment")
                        .addToBackStack("MessageFragment")
                        .replace(R.id.fragment_container,
                                new ProfileFragment()).commit();

                break;
            case R.id.nav_history:
                getSupportFragmentManager().beginTransaction()
                        .add(new MainFragment(), "MessageFragment")
                        .addToBackStack("MessageFragment")
                        .replace(R.id.fragment_container,
                                new HistoryFragment()).commit();

                break;
            case R.id.nav_help:
                getSupportFragmentManager().beginTransaction()
                        .add(new MainFragment(), "MessageFragment")
                        .addToBackStack("MessageFragment")
                        .replace(R.id.fragment_container,
                                new HelpFragment()).commit();

                break;
            case R.id.nav_about:
                getSupportFragmentManager().beginTransaction()
                        .add(new MainFragment(), "MessageFragment")
                        .addToBackStack("MessageFragment")
                        .replace(R.id.fragment_container,
                                new AboutFragment()).commit();

                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {

                this.finish();
                System.exit(0);
            } else {
                super.onBackPressed();
                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            }
            mBackPressed = System.currentTimeMillis();
        }
    }
}
