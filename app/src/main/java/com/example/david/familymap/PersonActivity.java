package com.example.david.familymap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        gender.setText(person.getGender());

        String[] empty = new String[0];

        eventList = (RecyclerView) findViewById(R.id.event_list);
        eventLayoutManager = new LinearLayoutManager(this);
        eventList.setLayoutManager(eventLayoutManager);
        eventAdapter = new EventAdapter(empty);
        eventList.setAdapter(eventAdapter);
        eventShow = false;
        eventLayout = (LinearLayout) findViewById(R.id.event_drop_down);
        setEventsListListener();

        familyList = (RecyclerView) findViewById(R.id.family_list);
        familyLayoutManager = new LinearLayoutManager(this);
        familyList.setLayoutManager(familyLayoutManager);
        familyAdapter = new FamilyAdapter(empty, this);
        familyList.setAdapter(familyAdapter);
        familyLayout = (LinearLayout) findViewById(R.id.family_drop_down);
        familyShow = false;
        setFamilyListListener();
    }

    private String[] makeEventText()
    {
        String eventText[] = new String[events.length];
        for(int i = 0; i < events.length; i++)
        {
            eventText[i] = events[i].getEventType() + ": " + events[i].getCity() + ", " + events[i].getCountry() + " (" + events[i].getYear() + ")" +
                            '\n' +  person.getFirstName() + " " + person.getLastName();
        }
        return eventText;
    }

    private String[] makeFamilyText()
    {
        DataManager dman = DataManager.getInstance();
        Person[] family = dman.getFamily(person); // 0: Father 1 : Mother : 2 Spouse : 3 child etc  . . .
        ArrayList<String> familyText= new ArrayList<String>();

        if(family[0] != null)
        {
            familyText.add(family[0].getFirstName() + " " + family[0].getLastName() + '\n' + "Father");
        }
        if(family[1] != null)
        {
            familyText.add(family[1].getFirstName() + " " + family[1].getLastName() + '\n' + "Mother");
        }
        if(family[2] != null)
        {
            familyText.add(family[2].getFirstName() + " " + family[2].getLastName() + '\n' + "Spouse");
        }
        if(family[3] != null)
        {
            familyText.add(family[3].getFirstName() + " " + family[3].getLastName() + '\n' + "Child");
        }

        String[] familyReturned = new String[family.length];
        familyText.toArray(familyReturned);

        return familyReturned;
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
                    String[] empty = new String[0];
                    eventAdapter = new EventAdapter(empty);
                    eventList.setAdapter(eventAdapter);
                    eventShow = false;
                }
                else
                {
                    eventAdapter = new EventAdapter(makeEventText());
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
                    String[] empty = new String[0];
                    familyAdapter = new FamilyAdapter(empty, activity);
                    familyList.setAdapter(familyAdapter);
                    familyShow = false;
                }
                else
                {
                    familyAdapter = new FamilyAdapter(makeFamilyText(), activity);
                    familyList.setAdapter(familyAdapter);
                    familyShow = true;
                }
            }
        });
    }
}
