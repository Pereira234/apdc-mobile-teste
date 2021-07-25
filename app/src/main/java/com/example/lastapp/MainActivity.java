package com.example.lastapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lastapp.data.UserService;
import com.example.lastapp.data.model.GetEventNameIDResponse;
import com.example.lastapp.ui.getEvents.GetEventsViewModel;
import com.example.lastapp.ui.getEvents.GetEventsViewModelFactory;
import com.example.lastapp.ui.login.LoginActivity;
import com.example.lastapp.ui.login.LoginViewModel;
import com.example.lastapp.ui.login.LoginViewModelFactory;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "mypref";
    private static final String USERNAME_KEY = "username";
    private static final String TOKEN_ID_KEY = "tokenid";

    private GetEventsViewModel getEventsViewModel;

    public static List<GetEventNameIDResponse> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.BottomNavigationView);
        NavController navController = Navigation.findNavController(this,  R.id.fragmentContainerView);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        sharedPreferences = getSharedPreferences(SHARED_PREF, MODE_PRIVATE);

        String tokenId = sharedPreferences.getString(TOKEN_ID_KEY, "");

        getEventsViewModel = new ViewModelProvider(this, new GetEventsViewModelFactory(((GWApp) getApplication()).getExecutorService()))
                .get(GetEventsViewModel.class);


        list = getEventsViewModel.getEvents(tokenId);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.settings_op:
                //to do
                return true;
            case R.id.logout_op:

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(USERNAME_KEY, null);
                editor.apply();

                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();

                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public List<GetEventNameIDResponse> getEventsForMap(String tokenID) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<List<GetEventNameIDResponse>> getEventsNameIDCall = service.getEventsNameID(tokenID);

        try {
            Response<List<GetEventNameIDResponse>> response = getEventsNameIDCall.execute();
            if (response.isSuccessful()){
                return  response.body();
            }
            else {
                Toast.makeText(this, "Error getting the events", Toast.LENGTH_LONG).show();
            }
        } catch (IOException e) {
            Toast.makeText(this, "Failed Request", Toast.LENGTH_LONG).show();
        }
        return null;
    }

}