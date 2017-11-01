package com.event.app.izhar.eventapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

//this is the gallery view
public class EventDetailsNavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        Gallery.OnFragmentInteractionListener{

    NavigationView navigationView = null;
    Toolbar toolbar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //TODO set activity home as home
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        //change to starting screen fragment
        tx.replace(R.id.fragment_container, new Gallery());
        tx.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation_drawer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    private void DisplaySelectedScreen(int id) {
        Fragment fragment = null;
        //TODO add the rest of the fragments
        switch (id) {
            case R.id.view_gallery:
                fragment = new Gallery();
//                Intent eventDetailsIntent = new Intent(this, EventDetailsNavigationDrawer.class);
//                startActivity(eventDetailsIntent);
                break;
            case R.id.nav_account:
                Intent accountDetailsIntent = new Intent(this, AccountDetailsNavigationDrawer.class);
                startActivity(accountDetailsIntent);
            case R.id.events:
//                fragment = new EventActivity();
                Intent eventIntent = new Intent(this, EventNavigationDrawer.class);
                startActivity(eventIntent);
                break;
            case R.id.nav_login:
                Intent loginIntent = new Intent(this, LoginTwoActivity.class);
                startActivity(loginIntent);
            case R.id.nav_register:
                Intent registerUserIntent = new Intent(this, RegisterUserActivity.class);
                startActivity(registerUserIntent);
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

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}