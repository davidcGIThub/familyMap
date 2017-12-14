package com.example.david.familymap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;
import android.graphics.Color;
import data.DataManager;
import request.LoginRequest;
import spinners.FamilySpinner;
import spinners.LifeSpinner;
import spinners.MapTypeSpinner;
import spinners.SpouseSpinner;
import task.ReSyncTask;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;

public class SettingsActivity extends AppCompatActivity {
    private Spinner lifeStorySpinner;
    private Spinner familyTreeSpinner;
    private Spinner spouseSpinner;
    private Spinner mapTypeSpinner;
    private ToggleButton lifeStoryToggle;
    private ToggleButton familyTreeToggle;
    private ToggleButton spouseToggle;
    private LinearLayout resyncLayout;
    private LinearLayout logoutLayout;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        initializeLifeSettings();
        initializeSpouseSettings();
        initializeFamilySettings();
        initializeMapTypeSettings();

        resyncLayout = (LinearLayout) findViewById(R.id.resync_switch);
        logoutLayout = (LinearLayout) findViewById(R.id.logout_switch);

        lifeStoryToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataManager dman = DataManager.getInstance();
                if (isChecked) {
                    dman.lifeLinesOn = true;
                } else {
                    dman.lifeLinesOn = false;
                }
            }
        });

        familyTreeToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataManager dman = DataManager.getInstance();
                if (isChecked) {
                    dman.familyLinesOn = true;
                } else {
                    dman.familyLinesOn = false;
                }
            }
        });

        spouseToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                DataManager dman = DataManager.getInstance();
                if (isChecked) {
                    dman.marriageLinesOn = true;
                } else {
                    dman.marriageLinesOn = false;
                }
            }
        });

        resyncLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager dman = DataManager.getInstance();
                dman.resync();
                LoginRequest loginRequest = new LoginRequest(dman.username, dman.password);
                ReSyncTask task = new ReSyncTask(context);
                task.execute(loginRequest);
            }
        });

        logoutLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataManager dman = DataManager.getInstance();
                dman.refresh();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                // have it go to the login screen
            }
        });
    }

    public void initializeLifeSettings() {
        DataManager dman = DataManager.getInstance();

        lifeStoryToggle = (ToggleButton) findViewById(R.id.life_story_toggle);
        if (dman.lifeLinesOn) {
            lifeStoryToggle.setChecked(true);
        } else {
            lifeStoryToggle.setChecked(false);
        }

        lifeStorySpinner = (Spinner) findViewById(R.id.life_story_spinner);
        ArrayAdapter<CharSequence> lifeStoryAdapter = ArrayAdapter.createFromResource(this, R.array.colorOptions, android.R.layout.simple_spinner_item);
        lifeStoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lifeStorySpinner.setAdapter(lifeStoryAdapter);
        lifeStorySpinner.setOnItemSelectedListener(new LifeSpinner());
        switch(dman.lifeEventsColor) {
            case (Color.BLACK):
                lifeStorySpinner.setSelection(0);
                break;
            case (Color.BLUE):
                lifeStorySpinner.setSelection(1);
                break;
            case (Color.GREEN):
                lifeStorySpinner.setSelection(2);
                break;
            case (Color.RED):
                lifeStorySpinner.setSelection(3);
                break;
            case (Color.YELLOW):
                lifeStorySpinner.setSelection(4);
                break;
            case (Color.WHITE):
                lifeStorySpinner.setSelection(5);
                break;
            default:
                lifeStorySpinner.setSelection(4);
                break;
        }

    }

    public void initializeSpouseSettings() {
        DataManager dman = DataManager.getInstance();

        spouseToggle = (ToggleButton) findViewById(R.id.spouse_toggle);
        if (dman.marriageLinesOn) {
            spouseToggle.setChecked(true);
        } else {
            spouseToggle.setChecked(false);
        }
        spouseSpinner = (Spinner) findViewById(R.id.spouse_line_spinner);
        ArrayAdapter<CharSequence> spouseAdapter = ArrayAdapter.createFromResource(this, R.array.colorOptions, android.R.layout.simple_spinner_item);
        spouseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spouseSpinner.setAdapter(spouseAdapter);
        spouseSpinner.setOnItemSelectedListener(new SpouseSpinner());

        switch(dman.marriageLineColor) {
            case (Color.BLACK):
                spouseSpinner.setSelection(0);
                break;
            case (Color.BLUE):
                spouseSpinner.setSelection(1);
                break;
            case (Color.GREEN):
                spouseSpinner.setSelection(2);
                break;
            case (Color.RED):
                spouseSpinner.setSelection(3);
                break;
            case (Color.YELLOW):
                spouseSpinner.setSelection(4);
                break;
            case (Color.WHITE):
                spouseSpinner.setSelection(5);
                break;
            default:
                spouseSpinner.setSelection(4);
                break;
        }
    }

    public void initializeFamilySettings()
    {
        DataManager dman = DataManager.getInstance();
        familyTreeToggle = (ToggleButton) findViewById(R.id.family_tree_toggle);
        if (dman.familyLinesOn)
        {
            familyTreeToggle.setChecked(true);
        }
        else
        {
            familyTreeToggle.setChecked(false);
        }
        familyTreeSpinner = (Spinner) findViewById(R.id.family_tree_spinner);
        ArrayAdapter<CharSequence> familyTreeAdapter = ArrayAdapter.createFromResource(this, R.array.colorOptions, android.R.layout.simple_spinner_item);
        familyTreeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        familyTreeSpinner.setAdapter(familyTreeAdapter);
        familyTreeSpinner.setOnItemSelectedListener(new FamilySpinner());

        switch(dman.familyLineColor) {
            case (Color.BLACK):
                familyTreeSpinner.setSelection(0);
                break;
            case (Color.BLUE):
                familyTreeSpinner.setSelection(1);
                break;
            case (Color.GREEN):
                familyTreeSpinner.setSelection(2);
                break;
            case (Color.RED):
                familyTreeSpinner.setSelection(3);
                break;
            case (Color.YELLOW):
                familyTreeSpinner.setSelection(4);
                break;
            case (Color.WHITE):
                familyTreeSpinner.setSelection(5);
                break;
            default:
                familyTreeSpinner.setSelection(2);
                break;
        }
    }

    public void initializeMapTypeSettings()
    {
        DataManager dman = DataManager.getInstance();
        mapTypeSpinner = (Spinner) findViewById(R.id.map_type_spinner);
        //mapTypeSpinner.setPromptId(2);
        ArrayAdapter<CharSequence> mapTypeAdapter = ArrayAdapter.createFromResource(this, R.array.mapTypeOptions, android.R.layout.simple_spinner_item);
        mapTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mapTypeSpinner.setAdapter(mapTypeAdapter);
        mapTypeSpinner.setOnItemSelectedListener(new MapTypeSpinner());

        switch(dman.mapType)
        {
            case(MAP_TYPE_NORMAL):
                mapTypeSpinner.setSelection(0);
                break;
            case(MAP_TYPE_HYBRID):
                mapTypeSpinner.setSelection(1);
                break;
            case(MAP_TYPE_SATELLITE):
                mapTypeSpinner.setSelection(2);
                break;
            case(MAP_TYPE_TERRAIN):
                mapTypeSpinner.setSelection(3);
                break;
            case(MAP_TYPE_NONE):
                mapTypeSpinner.setSelection(4);
                break;
            default:
                mapTypeSpinner.setSelection(0);
                break;
        }
    }


}
