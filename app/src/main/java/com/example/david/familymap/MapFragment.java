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
import android.widget.TextView;

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placeEventMarkers();
        //add all the events to the map
        setMarkerListener();
        setEventBarListener();
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
        // in case doesnt work try onMapReady as an anonymous function
        return v;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
    }

    //    private void setClickListener() {
//        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng latLng)
//            {
//                //textView.setText(latLng.toString());
//            }
//        });
//    }

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
                return true; // why is there a return statement?????
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
    }

}