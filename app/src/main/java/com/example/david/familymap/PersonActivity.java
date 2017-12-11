package com.example.david.familymap;

import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import data.DataManager;
import model.Person;
//import android.view.View;

public class PersonActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String personID = intent.getStringExtra("PERSON_ID");
        DataManager dman = DataManager.getInstance();
        Person person = dman.getPerson(personID);
    }

}
