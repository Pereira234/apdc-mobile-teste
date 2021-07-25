package com.example.lastapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lastapp.data.UserService;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import com.example.lastapp.data.model.GetUserResponse;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsFragment extends Fragment {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "mypref";
    private static final String USERNAME_KEY = "username";
    private static final String TOKEN_ID_KEY = "tokenid";

    private List<GetEventNameIDResponse> events;

    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng FCT = new LatLng(38.660967, -9.204417);
            LatLng event1 = new LatLng(38.667770746919395,-9.214596500911977);
            LatLng event2 = new LatLng(38.6599251914614,-9.175331051475938);
            LatLng event3 = new LatLng(38.64539348427262,-9.241273311698817);

            int i = 0;
            for (GetEventNameIDResponse event: events){
                LatLng coords = new LatLng(38.667770746919395+i,-9.214596500911977+i);
                googleMap.addMarker(new MarkerOptions().position(coords).title(event.getName()));
                i+=10;
            }
            googleMap.addMarker(new MarkerOptions().position(FCT).title("FCT UNL"));
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(FCT));
//            googleMap.addMarker(new MarkerOptions().position(event1).title("Lunch with the boys"));
//            googleMap.addMarker(new MarkerOptions().position(event2).title("Park"));
//            googleMap.addMarker(new MarkerOptions().position(event3).title("Barbas"));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_maps, container, false);

        FragmentActivity activity = this.getActivity();
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);

        String tokenId = sharedPreferences.getString(TOKEN_ID_KEY, null);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<List<GetEventNameIDResponse>> getEventsNameIDCall = service.getEventsNameID(tokenId);

        getEventsNameIDCall.enqueue(new Callback<List<GetEventNameIDResponse>>() {
            @Override
            public void onResponse(Call<List<GetEventNameIDResponse>> call, Response<List<GetEventNameIDResponse>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(activity, "Error getting the events", Toast.LENGTH_LONG).show();
                }
                else {
                    events = response.body();
                }
            }

            @Override
            public void onFailure(Call<List<GetEventNameIDResponse>> call, Throwable t) {
                Toast.makeText(activity, "Failed Request", Toast.LENGTH_LONG).show();
            }
        });

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}