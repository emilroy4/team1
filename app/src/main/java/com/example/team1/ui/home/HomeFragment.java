package com.example.team1.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.team1.R;
import com.example.team1.databinding.FragmentHomeBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private MapView mapView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //texts
        final TextView textView = binding.textHome;
        final TextView announcement1 = binding.announcetextView;
        final TextView locationTextView = binding.locationText;
        announcement1.setSelected(true);
        homeViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        homeViewModel.getAnnouncement1().observe(getViewLifecycleOwner(), announcement1::setText);
        homeViewModel.getLocationText().observe(getViewLifecycleOwner(), locationTextView::setText);



        //MapView
        mapView = root.findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                // Add a marker for a specific location
                LatLng location = new LatLng(53.38487296112957, -6.2581844182077475); // Replace with your desired coordinates
                googleMap.addMarker(new MarkerOptions().position(location).title("Training location"));
                // Move camera to the specific location
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 12.0f));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
