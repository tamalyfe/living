package com.example.living.userInterface.mainPage.mapPage;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.living.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPageFragment extends Fragment implements OnMapReadyCallback {
    private GoogleMap googleMap;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationCallback locationCallback;
    private Marker currentLocationMarker;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.map_page_fragment, container, false);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext());

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }

        view.findViewById(R.id.fabNormalMap).setOnClickListener(v -> changeMapTheme(GoogleMap.MAP_TYPE_NORMAL));
        view.findViewById(R.id.fabSatelliteMap).setOnClickListener(v -> changeMapTheme(GoogleMap.MAP_TYPE_SATELLITE));
        view.findViewById(R.id.fabTerrainMap).setOnClickListener(v -> changeMapTheme(GoogleMap.MAP_TYPE_TERRAIN));
        view.findViewById(R.id.fabHybridMap).setOnClickListener(v -> changeMapTheme(GoogleMap.MAP_TYPE_HYBRID));

        return view;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            enableMyLocation();
        } else {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        }

        googleMap.getUiSettings().setZoomControlsEnabled(true);

        createLocationCallback();
        startLocationUpdates();
    }

    @SuppressLint("MissingPermission")
    private void enableMyLocation() {
        googleMap.setMyLocationEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(true);
    }

    private void createLocationCallback() {
        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    return;
                }

                Location lastLocation = locationResult.getLastLocation();
                LatLng currentLatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());

                if (currentLocationMarker != null) {
                    currentLocationMarker.remove();
                }

                currentLocationMarker = googleMap.addMarker(new MarkerOptions().position(currentLatLng).title("Current Location"));
            }
        };
    }

    private void startLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create().setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY).setInterval(10000);

        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null);
        }
    }

    public void changeMapTheme(int mapType) {
        if (googleMap != null) {
            googleMap.setMapType(mapType);
        }
    }
}
