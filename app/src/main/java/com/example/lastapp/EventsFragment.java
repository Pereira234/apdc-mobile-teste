package com.example.lastapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.lastapp.ui.event.EventFormState;
import com.example.lastapp.ui.event.EventResult;
import com.example.lastapp.ui.event.EventViewModel;
import com.example.lastapp.ui.event.EventViewModelFactory;
import com.example.lastapp.ui.login.LoginActivity;
import com.example.lastapp.ui.register.RegisterViewModel;
import com.example.lastapp.ui.register.RegisterViewModelFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment forthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_events, container, false);



        






//        super.onCreate(savedInstanceState);
//        this.getActivity().setContentView(R.layout.fragment_events); //pode estar mal
//
//
//        EventViewModel eventViewModel = new ViewModelProvider(this,
//                new EventViewModelFactory(((GWApp) this.getActivity().getApplication()).getExecutorService()))
//                .get(EventViewModel.class);
//
//        final EditText eventNameEditText = v.findViewById(R.id.eventName);
//        final EditText eventDescriptionEditText = v.findViewById(R.id.eventDescription);
//        final EditText eventDurationEditText = v.findViewById(R.id.eventDuration);
//        final EditText eventDateEditText = v.findViewById(R.id.eventDate);
//        final EditText locationLatEditText = v.findViewById(R.id.locationLat);
//        final EditText locationLonEditText = v.findViewById(R.id.locationLon);
//
//        final Button createEventButton = v.findViewById(R.id.createEvent);
//
//
//
//        eventViewModel.getEventFormState().observe(this.getActivity(), new Observer<EventFormState>() { //need to update! (updated)
//            @Override
//            public void onChanged(@Nullable EventFormState eventFormState) {
//                if (eventFormState == null) {
//                    return;
//                }
//                createEventButton.setEnabled(eventFormState.isDataValid());
//                if (eventFormState.getNameError() != null) {
//                    eventNameEditText.setError(getString(eventFormState.getNameError()));
//                }
//                if (eventFormState.getDescriptionError() != null) {
//                    eventDescriptionEditText.setError(getString(eventFormState.getDescriptionError()));
//                }
//                if (eventFormState.getDurationError() != null) {
//                    eventDurationEditText.setError(getString(eventFormState.getDurationError()));
//                }
//                if (eventFormState.getDateError() != null) {
//                    eventDateEditText.setError(getString(eventFormState.getDateError()));
//                }
//                if(eventFormState.getLatitudeError() != null){
//                    locationLatEditText.setError(getString(eventFormState.getLatitudeError()));
//                }
//                if(eventFormState.getLongitudeError() != null){
//                    locationLonEditText.setError(getString(eventFormState.getLongitudeError()));
//                }
//            }
//        });
//
//
//
//        eventViewModel.getEventResult().observe(this.getActivity(), new Observer<EventResult>() {  //need to update!
//            @Override
//            public void onChanged(@Nullable EventResult eventResult) {
//                if (eventResult == null) {
//                    return;
//                }
//
//                if (eventResult.getError() != null) {
//                    showRegisterFailed(eventResult.getError(), v);
//                }
//                if (eventResult.getSuccess() != null) {
//                    showRegisterSuccess(v);
//                    //setResult(Activity.RESULT_OK);
//
//                }
//
//            }
//        });
//
//
//
//
//
//        TextWatcher afterTextChangedListener = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//                // ignore
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // ignore
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                eventViewModel.eventDataChanged(eventNameEditText.getText().toString(),
//                        eventDateEditText.getText().toString(),
//                        eventDurationEditText.getText().toString(),
//                        eventDateEditText.getText().toString(),
//                        Double.parseDouble(locationLatEditText.toString()),
//                        Double.parseDouble(locationLonEditText.toString()));
//            }
//        };
//
//
//        eventNameEditText.addTextChangedListener(afterTextChangedListener);
//        eventDescriptionEditText.addTextChangedListener(afterTextChangedListener);
//        eventDurationEditText.addTextChangedListener(afterTextChangedListener);
//        eventDateEditText.addTextChangedListener(afterTextChangedListener);
//        locationLatEditText.addTextChangedListener(afterTextChangedListener);
//        locationLonEditText.addTextChangedListener(afterTextChangedListener);
//        eventNameEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    eventViewModel.createEvent(eventNameEditText.getText().toString(),
//                            eventDescriptionEditText.getText().toString(),
//                            eventDurationEditText.getText().toString(),
//                            eventDateEditText.getText().toString(),
//                            Double.parseDouble(locationLatEditText.toString()),
//                            Double.parseDouble(locationLonEditText.toString())
//                    );
//                }
//                return false;
//            }
//        });
//
//        createEventButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                eventViewModel.createEvent(eventNameEditText.getText().toString(),
//                        eventDescriptionEditText.getText().toString(),
//                        eventDurationEditText.getText().toString(),
//                        eventDateEditText.getText().toString(),
//                        Double.parseDouble(locationLatEditText.toString()),
//                        Double.parseDouble(locationLonEditText.toString())
//
//                );
//            }
//        });
//
//
//
//




        return v;
    }

    private void showRegisterFailed(@StringRes Integer errorString, View v) {
        Context context = v.getContext().getApplicationContext();
        Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
    }

    private void showRegisterSuccess(View v) {
        Context context = v.getContext().getApplicationContext();
        String message = getString(R.string.register_successful);
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

    }
}