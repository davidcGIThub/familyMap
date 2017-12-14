package com.example.david.familymap;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
//for iconify
import com.joanzapata.iconify.Iconify;  //for iconify
import com.joanzapata.iconify.fonts.FontAwesomeModule;

import data.DataManager;

public class MainActivity extends AppCompatActivity {

    private Fragment fragment;
    private FragmentManager fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm = getSupportFragmentManager();
        fragment = fm.findFragmentById(R.id.fragment_container);
        DataManager dman =  DataManager.getInstance();

        if(fragment == null && dman.authToken == null)
        {
            fragment = new LoginFragment();
        }
        else if(dman.authToken == null)
        {
            fragment = new LoginFragment(); // make this check to see if the auth token has timed out in the future
        }
        else
        {
            fragment = new MapFragment();
        }
        fm.beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit();
        dman.ORIGINAL_CONTEXT = this;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.map_fragment_toolbar, menu);
        return false;
    }

        @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.map_fragment_search_icon:
                Intent searchIntent = new Intent(this, SearchActivity.class);
                startActivity(searchIntent);
                return true;
            case R.id.map_fragment_filter_icon:
                Intent filterIntent = new Intent(this, FilterActivity.class);
                startActivity(filterIntent);
                return true;
            case R.id.map_fragment_settings_icon:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
                return true;
            default:
                return true;
        }
    }

    public void switchToMapFragment()
    {
        fragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    private void startSearchActivity()
    {

    }

    private void startFilterActivity()
    {

    }

}
