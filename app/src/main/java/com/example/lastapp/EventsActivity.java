package com.example.lastapp;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastapp.ui.event.EventFormState;
import com.example.lastapp.ui.event.EventResult;
import com.example.lastapp.ui.event.EventViewModel;
import com.example.lastapp.ui.event.EventViewModelFactory;
import com.example.lastapp.ui.register.RegisterViewModel;
import com.example.lastapp.ui.register.RegisterViewModelFactory;

public class EventsActivity extends AppCompatActivity {

    private EventViewModel eventViewModel;
    private EventsActivity mActivity;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "mypref";
    private static final String TOKEN_ID_KEY = "tokenid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        mActivity = this;

        eventViewModel = new ViewModelProvider(this, new EventViewModelFactory(((GWApp) getApplication()).getExecutorService()))
                .get(EventViewModel.class);

        final EditText eventNameEditText = findViewById(R.id.eventName);
        final EditText eventDescriptionEditText = findViewById(R.id.eventDescription);
        final EditText eventDurationEditText = findViewById(R.id.eventDuration);
        final EditText eventDateEditText = findViewById(R.id.eventDate);
        final EditText locationLatEditText = findViewById(R.id.locationLat);
        final EditText locationLonEditText = findViewById(R.id.locationLon);
        final EditText eventCategory = findViewById(R.id.eventCategory);
        final EditText eventStartingTime = findViewById(R.id.eventStartingTime);

        final Button createEventButton = findViewById(R.id.createEvent);

        sharedPreferences = this.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String tokenId = sharedPreferences.getString(TOKEN_ID_KEY, "");

        eventViewModel.getEventFormState().observe(this, new Observer<EventFormState>() {
            @Override
            public void onChanged(@Nullable EventFormState eventFormState) {
                if (eventFormState == null) {
                    return;
                }
                createEventButton.setEnabled(eventFormState.isDataValid());
                if (eventFormState.getNameError() != null) {
                    eventNameEditText.setError(getString(eventFormState.getNameError()));
                }
                if (eventFormState.getDescriptionError() != null) {
                    eventDescriptionEditText.setError(getString(eventFormState.getDescriptionError()));
                }
                if (eventFormState.getDurationError() != null) {
                    eventDurationEditText.setError(getString(eventFormState.getDurationError()));
                }
                if (eventFormState.getDateError() != null) {
                    eventDateEditText.setError(getString(eventFormState.getDateError()));
                }
                if(eventFormState.getLatitudeError() != null){
                    locationLatEditText.setError(getString(eventFormState.getLatitudeError()));
                }
                if(eventFormState.getLongitudeError() != null){
                    locationLonEditText.setError(getString(eventFormState.getLongitudeError()));
                }
            }
        });



        eventViewModel.getEventResult().observe(this, new Observer<EventResult>() {  //need to update!
            @Override
            public void onChanged(@Nullable EventResult eventResult) {
                if (eventResult == null) {
                    return;
                }

                if (eventResult.getError() != null) {
                    showEventFailed(eventResult.getError());
                }
                if (eventResult.getSuccess() != null) {
                    showEventSuccess();
                    setResult(Activity.RESULT_OK);
                    finish();
                }

            }
        });





        TextWatcher afterTextChangedListener = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ignore
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ignore
            }

            @Override
            public void afterTextChanged(Editable s) {

                eventViewModel.eventDataChanged(eventNameEditText.getText().toString(),
                        eventDateEditText.getText().toString(),
                        eventDurationEditText.getText().toString(),
                        eventDateEditText.getText().toString(),
                        locationLatEditText.getText().toString(),
                        locationLonEditText.getText().toString());
            }
        };


        eventNameEditText.addTextChangedListener(afterTextChangedListener);
        eventDescriptionEditText.addTextChangedListener(afterTextChangedListener);
        eventDurationEditText.addTextChangedListener(afterTextChangedListener);
        eventDateEditText.addTextChangedListener(afterTextChangedListener);
        locationLatEditText.addTextChangedListener(afterTextChangedListener);
        locationLonEditText.addTextChangedListener(afterTextChangedListener);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Double lat = Double.parseDouble(locationLatEditText.getText().toString());
                Double lon = Double.parseDouble(locationLonEditText.getText().toString());

                eventViewModel.createEvent(eventNameEditText.getText().toString(),
                        eventDescriptionEditText.getText().toString(),
                        eventDurationEditText.getText().toString(),
                        eventDateEditText.getText().toString(),
                        lat,
                        lon,
                        eventStartingTime.getText().toString(),
                        eventCategory.getText().toString(),
                        tokenId

                );
            }
        });








    }

    private void showEventFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void showEventSuccess() {
        String message = "Event successful created!";
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }



}