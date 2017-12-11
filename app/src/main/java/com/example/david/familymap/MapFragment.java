package com.example.david.familymap;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import android.widget.TextView;

import data.DataManager;


public class MapFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap mMap;
    private TextView textView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        DataManager dman = DataManager.getInstance();
        //add all the events to the map
        for(int i = 0; i < dman.events.length; i++)
        {
            LatLng latlon = new LatLng(dman.events[i].getLatitude(),dman.events[i].getLongitude());
            mMap.addMarker(new MarkerOptions().position(latlon).title(dman.events[i].getEventType()));
            //centers the camera on the birthplace of the user
            if(dman.events[i].getPersonID().equals(dman.userPersonID))
            {
                if(dman.events[i].getEventType().equals("Birth"))
                {
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(latlon));
                }
            }
        }
        setClickListener();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        textView = (TextView) v.findViewById(R.id.text);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapview);
        mapFragment.getMapAsync(this);
        //in case doesnt work try onMapReady as an anonymous function
        return v;
    }

    void setClickListener() {
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng)
            {
                textView.setText(latLng.toString());
            }
        });
    }
}