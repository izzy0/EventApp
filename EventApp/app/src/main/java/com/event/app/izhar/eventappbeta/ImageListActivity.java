package com.event.app.izhar.eventappbeta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;

import com.event.app.izhar.eventappbeta.DBConnection.Downloader;

public class ImageListActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    final static String IMAGE_LIST_URL = "http://10.15.21.74/Eventapp/image_list.php";
//    final static String IMAGE_LIST_URL = "http://localhost/Eventapp/image_list.php";
//    final static String IMAGE_LIST_URL = "http://192.168.1.135/Eventapp/image_list.php";
    final static String IMAGE_LIST_URL = "http://cq7243tk.000webhostapp.com/image_list.php";

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);

//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
//                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
//        drawer.setDrawerListener(toggle);
//        toggle.syncState();
//
//        navigationView = (NavigationView) findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        final GridView gridView = (GridView) findViewById(R.id.gallery_image_view);

        FloatingActionButton button = (FloatingActionButton) findViewById(R.id.image_list_fab_refresh);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Downloader(ImageListActivity.this, IMAGE_LIST_URL, gridView).execute();
            }
        });
    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    private void DisplaySelectedScreen(int id) {
        Fragment fragment = null;
        //TODO add the rest of the fragments
        switch (id) {
            case R.id.view_gallery:
//                fragment = new Gallery();
                Intent eventDetailsIntent = new Intent(this, EventDetailsNavigationDrawer.class);
                startActivity(eventDetailsIntent);
                break;
            case R.id.nav_account:
                Intent accountDetailsIntent = new Intent(this, AccountDetailsNavigationDrawer.class);
                startActivity(accountDetailsIntent);
            case R.id.create_event:
                fragment = new CreateEvent();
                break;
            case R.id.events:
                fragment = new EventFragment();
                break;
            case R.id.nav_login:
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
            case R.id.nav_register:
                Intent intent1 = new Intent(this, RegisterUserActivity.class);
                startActivity(intent1);
        }

        if (fragment != null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, fragment);
            fragmentTransaction.commit();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        DisplaySelectedScreen(id);

        return true;
    }
}
