package com.example.david.familymap;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import data.DataManager;
import data.EventAdapter;
import data.FamilyAdapter;
import data.PersonAdapter;
import model.Event;
import model.Person;

public class SearchActivity extends AppCompatActivity
{
    private RecyclerView eventList;
    private RecyclerView.LayoutManager eventLayoutManager;
    private RecyclerView.Adapter eventAdapter;
    private RecyclerView personList;
    private RecyclerView.LayoutManager personLayoutManager;
    private RecyclerView.Adapter personAdapter;
    private EditText searchBox;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchBox = (EditText) findViewById(R.id.search_box);
        searchBox.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count)
            {
                String temp = s.toString();
                DataManager dman = DataManager.getInstance();
                Person[] persons = dman.searchPersons(temp);
                Event[] events = dman.searchEvents(temp);

                eventList = (RecyclerView) findViewById(R.id.search_event_list);
                eventLayoutManager = new LinearLayoutManager(context);
                eventList.setLayoutManager(eventLayoutManager);
                eventAdapter = new EventAdapter(events,context);
                eventList.setAdapter(eventAdapter);

                personList = (RecyclerView) findViewById(R.id.search_person_list);
                personLayoutManager = new LinearLayoutManager(context);
                personList.setLayoutManager(personLayoutManager);
                personAdapter = new PersonAdapter(persons, context);
                personList.setAdapter(personAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
