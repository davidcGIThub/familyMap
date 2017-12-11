package com.example.david.familymap;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
    }

    public void switchToMapFragment()
    {
        fragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }
}
