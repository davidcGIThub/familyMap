package com.example.david.familymap;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import data.DataManager;

public class FilterActivity extends AppCompatActivity
{
    private ToggleButton baptismToggle;
    private ToggleButton birthToggle;
    private ToggleButton marriageToggle;
    private ToggleButton deathToggle;
    private ToggleButton fatherToggle;
    private ToggleButton motherToggle;
    private ToggleButton maleToggle;
    private ToggleButton femaleToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        initializeToggles();
        setListeners();
    }

    public void initializeToggles()
    {
        DataManager dman = DataManager.getInstance();
        baptismToggle = (ToggleButton) findViewById(R.id.baptism_events_toggle);
        if (dman.baptismFiltered) {
            baptismToggle.setChecked(true);
        } else {
            baptismToggle.setChecked(false);
        }

        birthToggle = (ToggleButton) findViewById(R.id.birth_events_toggle);
        if (dman.birthFiltered) {
            birthToggle.setChecked(true);
        } else {
            birthToggle.setChecked(false);
        }

        marriageToggle = (ToggleButton) findViewById(R.id.marriage_events_toggle);
        if (dman.marriageFiltered) {
            marriageToggle.setChecked(true);
        } else {
            marriageToggle.setChecked(false);
        }

        deathToggle = (ToggleButton) findViewById(R.id.death_events_toggle);
        if (dman.deathFiltered) {
            deathToggle.setChecked(true);
        } else {
            deathToggle.setChecked(false);
        }

        fatherToggle = (ToggleButton) findViewById(R.id.fathers_side_events_toggle);
        if (dman.fatherFiltered) {
            fatherToggle.setChecked(true);
        } else {
            fatherToggle.setChecked(false);
        }

        motherToggle = (ToggleButton) findViewById(R.id.mothers_side_events_toggle);
        if (dman.motherFiltered) {
            motherToggle.setChecked(true);
        } else {
            motherToggle.setChecked(false);
        }

        maleToggle = (ToggleButton) findViewById(R.id.male_events_toggle);
        if (dman.malesFiltered)
        {
            maleToggle.setChecked(true);
        }
        else
        {
            maleToggle.setChecked(false);
        }

        femaleToggle = (ToggleButton) findViewById(R.id.female_events_toggle);
        if (dman.femalesFiltered)
        {
            femaleToggle.setChecked(true);
        }
        else
        {
            femaleToggle.setChecked(false);
        }

    }

    public void setListeners()
    {
        baptismToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.baptismFiltered = true;
                }
                else
                {
                    dman.baptismFiltered  = false;
                }
            }
        });

        birthToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.birthFiltered = true;
                }
                else
                {
                    dman.birthFiltered  = false;
                }
            }
        });

        marriageToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.marriageFiltered = true;
                }
                else
                {
                    dman.marriageFiltered  = false;
                }
            }
        });

        deathToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.deathFiltered = true;
                }
                else
                {
                    dman.deathFiltered  = false;
                }
            }
        });

        fatherToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.fatherFiltered = true;
                }
                else
                {
                    dman.fatherFiltered  = false;
                }
            }
        });

        motherToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.motherFiltered = true;
                }
                else
                {
                    dman.motherFiltered  = false;
                }
            }
        });

        maleToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.malesFiltered = true;
                }
                else
                {
                    dman.malesFiltered  = false;
                }
            }
        });

        femaleToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                DataManager dman = DataManager.getInstance();
                if (isChecked)
                {
                    dman.femalesFiltered = true;
                }
                else
                {
                    dman.femalesFiltered  = false;
                }
            }
        });
    }

}
