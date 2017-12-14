package com.example.david.familymap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import data.DataManager;
import data.EventAdapter;
import data.FamilyAdapter;
import model.Event;
import model.Person;
//import android.view.View;

public class PersonActivity extends AppCompatActivity
{
    private Activity activity = this;
    private TextView firstName;
    private TextView lastName;
    private TextView gender;
    private RecyclerView eventList;
    private RecyclerView.LayoutManager eventLayoutManager;
    private RecyclerView.Adapter eventAdapter;
    private RecyclerView familyList;
    private RecyclerView.LayoutManager familyLayoutManager;
    private RecyclerView.Adapter familyAdapter;
    private LinearLayout eventLayout;
    private LinearLayout familyLayout;
    private Boolean eventShow;
    private Boolean familyShow;

    private Event[] events;
    private Person person;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);
        Intent intent = getIntent();
        String personID = intent.getStringExtra("PERSON_ID");
        DataManager dman = DataManager.getInstance();

        this.person = dman.getPerson(personID);
        this.events = dman.getEvents(personID);

        firstName = (TextView) findViewById(R.id.first_name);
        lastName = (TextView) findViewById(R.id.last_name);
        gender = (TextView) findViewById(R.id.gender);

        firstName.setText(person.getFirstName());
        lastName.setText(person.getLastName());
        if(person.getGender().equals("m"))
        {
            gender.setText("Male");
        }
        else
        {
            gender.setText("Female");
        }

        eventList = (RecyclerView) findViewById(R.id.event_list);
        eventLayoutManager = new LinearLayoutManager(this);
        eventList.setLayoutManager(eventLayoutManager);
        Event[] emptyEvents = new Event[0];
        eventAdapter = new EventAdapter(emptyEvents,this);
        eventList.setAdapter(eventAdapter);
        eventShow = false;
        eventLayout = (LinearLayout) findViewById(R.id.event_drop_down);
        setEventsListListener();

        familyList = (RecyclerView) findViewById(R.id.family_list);
        familyLayoutManager = new LinearLayoutManager(this);
        familyList.setLayoutManager(familyLayoutManager);
        Person[] emptyPersons = new Person[0];
        familyAdapter = new FamilyAdapter(emptyPersons,person, this);
        familyList.setAdapter(familyAdapter);
        familyLayout = (LinearLayout) findViewById(R.id.family_drop_down);
        familyShow = false;
        setFamilyListListener();
    }


    private void setEventsListListener()
    {
        eventLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(eventShow)
                {
                    Event[] empty = new Event[0];
                    eventAdapter = new EventAdapter(empty,activity);
                    eventList.setAdapter(eventAdapter);
                    eventShow = false;
                }
                else
                {
                    eventAdapter = new EventAdapter(events,activity);
                    eventList.setAdapter(eventAdapter);
                    eventShow = true;
                }
            }
        });
    }

    private void setFamilyListListener()
    {
        familyLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(familyShow)
                {
                    Person[] empty = new Person[0];
                    familyAdapter = new FamilyAdapter(empty,person, activity);
                    familyList.setAdapter(familyAdapter);
                    familyShow = false;
                }
                else
                {
                    DataManager dman = DataManager.getInstance();
                    familyAdapter = new FamilyAdapter(dman.getFamily(person), person, activity);
                    familyList.setAdapter(familyAdapter);
                    familyShow = true;
                }
            }
        });
    }
}
