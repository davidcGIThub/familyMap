package com.example.david.familymap;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NORMAL;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_HYBRID;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_SATELLITE;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_TERRAIN;
import static com.google.android.gms.maps.GoogleMap.MAP_TYPE_NONE;


import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import data.DataManager;
import data.MarkerTag;
import model.Event;
import model.Person;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView fullName;
    private TextView eventDetails;
    private ImageView gender;
    private LinearLayout lLayout;
    private List<Polyline> polylines = new ArrayList<Polyline>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onMapReady(GoogleMap googleMap) {
        DataManager dman = DataManager.getInstance();
        dman.filter();
        mMap = googleMap;
        mMap.clear();
        dman.filter();
        placeEventMarkers();
        //add all the events to the map
        setMarkerListener();
        setEventBarListener();
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        fullName = (TextView) v.findViewById(R.id.person_full_name);
        eventDetails = (TextView) v.findViewById(R.id.event_details);
        gender = (ImageView) v.findViewById(R.id.gender_icon);
        lLayout = (LinearLayout) v.findViewById(R.id.event_bar);
        lLayout.setTag("");
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
    }

    private void setEventBarListener()
    {
        lLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String temp = (String) lLayout.getTag();

                if(!temp.equals(""))
                {
                    Intent intent = new Intent(getActivity(), PersonActivity.class);
                    String personID = (String) lLayout.getTag();
                    intent.putExtra("PERSON_ID", personID);
                    startActivity(intent);
                }
            }
        });
    }

    private void setMarkerListener() {
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                for(Polyline line : polylines)
                {
                    line.remove();
                }
                polylines.clear();

                MarkerTag mtag = (MarkerTag) marker.getTag();
                Event event = mtag.event;
                Person person = mtag.person;
                lLayout.setTag(person.getPersonID());
                LatLng latlon = new LatLng(mtag.event.getLatitude(),mtag.event.getLongitude());
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latlon));
                fullName.setText(person.getFirstName() + " " + person.getLastName());
                eventDetails.setText(event.getEventType() + ": " + event.getCity() + ", " + event.getCountry() +
                        " (" + event.getYear() + ")");
                if(person.getGender().equals("m"))
                {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_male);
                    gender.setImageDrawable(drawable);
                }
                else
                {
                    Drawable drawable = getResources().getDrawable(R.mipmap.ic_female);
                    gender.setImageDrawable(drawable);
                }

                DataManager dman = DataManager.getInstance();
                if(dman.marriageLinesOn)
                {
                    drawMarriageLine(event);
                }
                if(dman.familyLinesOn)
                {
                    drawAncestorLines(event, 5);
                }
                if(dman.lifeLinesOn)
                {
                    drawLifeEvents(event, 5);
                }
                return true;
            }
        });

    }

    private void placeEventMarkers()
    {
        DataManager dman = DataManager.getInstance();
        for(int i = 0; i < dman.events.length; i++)
        {
            Event event = dman.events[i];
            Person person = dman.getPerson(dman.events[i].getPersonID());
            MarkerTag mtag = new MarkerTag(person,event);
            LatLng latlon = new LatLng(dman.events[i].getLatitude(),dman.events[i].getLongitude());
            switch (dman.events[i].getEventType()) {
                case "Birth":
                    mMap.addMarker(new MarkerOptions()
                            .position(latlon)
                            .title(dman.events[i].getEventType())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)))
                            .setTag(mtag);

                    break;
                case "Baptism":
                    mMap.addMarker(new MarkerOptions()
                            .position(latlon)
                            .title(dman.events[i].getEventType())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)))
                            .setTag(mtag);
                    break;
                case "Marriage":
                    mMap.addMarker(new MarkerOptions()
                            .position(latlon)
                            .title(dman.events[i].getEventType())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)))
                            .setTag(mtag);
                    break;
                case "Death":
                    mMap.addMarker(new MarkerOptions()
                            .position(latlon)
                            .title(dman.events[i].getEventType())
                            .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_VIOLET)))
                            .setTag(mtag);
                    break;
                default:
                    mMap.addMarker(new MarkerOptions()
                            .position(latlon)
                            .title(dman.events[i].getEventType()))
                            .setTag(mtag);
                    break;
            }
            //centers the camera on the birthplace of the user
            if(dman.events[i].getPersonID().equals(dman.userPersonID))
            {
                if(dman.events[i].getEventType().equals("Birth"))
                {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlon));
                }
            }
        }
        Intent intent = getActivity().getIntent();
        String eventID = intent.getStringExtra("EVENT_ID");
        Event selectedEvent = dman.getEvent(eventID);
        if(selectedEvent != null)
        {
            LatLng latlon = new LatLng(selectedEvent.getLatitude(), selectedEvent.getLongitude());
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlon));
            Person selectedPerson = dman.getPerson(selectedEvent.getPersonID());
            lLayout.setTag(selectedPerson.getPersonID());
            fullName.setText(selectedPerson.getFirstName() + " " + selectedPerson.getLastName());
            eventDetails.setText(selectedEvent.getEventType() + ": " + selectedEvent.getCity() + ", " + selectedEvent.getCountry() +
                    " (" + selectedEvent.getYear() + ")");
            Drawable drawable;
            if (selectedPerson.getGender().equals("m")) {
                drawable = getResources().getDrawable(R.mipmap.ic_male);
            } else {
                drawable = getResources().getDrawable(R.mipmap.ic_female);
            }
            gender.setImageDrawable(drawable);
        }

    }


    private void  drawMarriageLine(Event event)
    {
        DataManager dman = DataManager.getInstance();
        LatLng latlon = new LatLng(event.getLatitude(),event.getLongitude());
        Event spouseEvent = dman.getRelationsEarliestEvent(event, "Spouse");
        if (spouseEvent != null)
        {
            LatLng latlonSpouse = new LatLng(spouseEvent.getLatitude(),spouseEvent.getLongitude());
            drawLine(latlon, latlonSpouse, dman.marriageLineColor, 5);
        }
    }

    private void drawAncestorLines(Event event, int lineWidth)
    {
        drawParentsLine(event, lineWidth);
        DataManager dman = DataManager.getInstance();
        Person currentPerson = dman.getPerson(event.getPersonID());
        if(!currentPerson.getFather().equals("null"))
        {
            Event fatherEvent = dman.getRelationsEarliestEvent(event,"Father");
            int width = lineWidth - 1;
            if(width < 1)
            {
                width = 1;
            }
            drawAncestorLines(fatherEvent, width);
        }
        if(!currentPerson.getMother().equals("null"))
        {
            Event motherEvent = dman.getRelationsEarliestEvent(event,"Mother");
            int width = lineWidth - 1;
            if(width < 1)
            {
                width = 1;
            }
            drawAncestorLines(motherEvent, width);
        }
    }

    private void drawParentsLine(Event event, int lineWidth)
    {
        DataManager dman = DataManager.getInstance();
        LatLng latlon = new LatLng(event.getLatitude(),event.getLongitude());
        Event fatherEvent = dman.getRelationsEarliestEvent(event,"Father");
        if (fatherEvent != null)
        {
            LatLng latlonFather = new LatLng(fatherEvent.getLatitude(),fatherEvent.getLongitude());
            drawLine(latlon, latlonFather, dman.familyLineColor, lineWidth);
        }
        Event motherEvent = dman.getRelationsEarliestEvent(event,"Mother");
        if (motherEvent != null)
        {
            LatLng latlonMother = new LatLng(motherEvent.getLatitude(),motherEvent.getLongitude());
            drawLine(latlon, latlonMother, dman.familyLineColor, lineWidth);
        }
    }

    private void drawLifeEvents(Event event, int lineWidth)
    {
        DataManager dman = DataManager.getInstance();
        Event[] events = dman.getEvents(event.getPersonID());
        LatLng[] latlons = new LatLng[events.length];
        for(int i = 0; i < events.length; i++)
        {
            latlons[i] = new LatLng(events[i].getLatitude(),events[i].getLongitude());
        }
        LatLng prev = latlons[0];
        for(int i = 1; i < latlons.length; i++)
        {
            drawLine(prev, latlons[i], dman.lifeEventsColor, lineWidth);
            prev = latlons[i];
        }

    }

    void drawLine(LatLng point1, LatLng point2, int color, int lineWidth)
    {
        PolylineOptions options = new PolylineOptions().add(point1, point2).color(color).width(lineWidth);
        polylines.add(mMap.addPolyline(options));
    }


}