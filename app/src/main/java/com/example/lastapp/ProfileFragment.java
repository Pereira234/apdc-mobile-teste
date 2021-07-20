package com.example.lastapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lastapp.data.GetUserDataSource;
import com.example.lastapp.data.GetUserRepository;
import com.example.lastapp.data.Result;
import com.example.lastapp.data.UserService;
import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.ui.getUser.GetUserViewModel;
import com.example.lastapp.ui.getUser.GetUserViewModelFactory;
import com.example.lastapp.ui.login.LoginViewModel;
import com.example.lastapp.ui.login.LoginViewModelFactory;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    ImageView imgView;
//    private GetUserDataSource getUserDataSource = new GetUserDataSource();
    private GetUserViewModel userViewModel;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "mypref";
    private static final String USERNAME_KEY = "username";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FifthFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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

        //imgView = findViewById(R.id.profilePicView);
        //Glide.with(this).load("https://cdn.discordapp.com/attachments/393483519055364117/860942284676399104/unknown.png").into(imgView);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        imgView = v.findViewById(R.id.profilePicView);
        Glide.with(this).load("https://t4.ftcdn.net/jpg/00/64/67/63/360_F_64676383_LdbmhiNM6Ypzb3FM4PPuFP9rHe7ri8Ju.jpg").into(imgView);


        final TextView profileName = v.findViewById(R.id.profileName);
        sharedPreferences = this.getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME_KEY, null);

//        Result<GetUserResponse> userResponseResult = getUserDataSource.getUser(username);
//        if (userResponseResult instanceof Result.Success) {
//            GetUserResponse user = ((Result.Success<GetUserResponse>) userResponseResult).getData();
//            profileName.setText(user.getName());
//        }




//        ExecutorService executorService = Executors.newFixedThreadPool(4);
//        userViewModel = new ViewModelProvider(this.getActivity(), new GetUserViewModelFactory(executorService))
//                .get(GetUserViewModel.class);
//
//        userViewModel.getUser(username);
//        GetUserResponse user = userViewModel.returnUser();
//
//        if (user != null) {
//            profileName.setText(user.getName());
//        }
//        else {
//            profileName.setText("Error");
//        }

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://goodway-320318.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UserService service = retrofit.create(UserService.class);

        Call<GetUserResponse> getUserResponseCall = service.getUser(username);

        getUserResponseCall.enqueue(new Callback<GetUserResponse>() {
            @Override
            public void onResponse(Call<GetUserResponse> call, Response<GetUserResponse> response) {
                if (!response.isSuccessful()) {
                    profileName.setText("Code " + response.code());
                }
                else {
                    GetUserResponse user = response.body();
                    profileName.setText(user.getName());
                }
            }

            @Override
            public void onFailure(Call<GetUserResponse> call, Throwable t) {
                profileName.setText("Error " + t.getMessage());
            }
        });



        return v;
    }
}