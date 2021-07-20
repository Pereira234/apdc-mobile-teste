package com.example.lastapp.ui.getUser;

import androidx.lifecycle.ViewModel;

import com.example.lastapp.R;
import com.example.lastapp.data.GetUserRepository;
import com.example.lastapp.data.Result;
import com.example.lastapp.data.model.GetUserResponse;
import com.example.lastapp.data.model.LoggedInUser;

import java.util.concurrent.Executor;

public class GetUserViewModel extends ViewModel {

    private GetUserRepository getUserRepository;
    private GetUserResponse user;

    private final Executor executor;

    GetUserViewModel(GetUserRepository getUserRepository, Executor executor) {
        this.getUserRepository = getUserRepository;
        this.executor = executor;
//        user = null;
    }

//    public void getUser(String username) {
//        executor.execute(new Runnable() {
//            @Override
//            public void run() {
//                Result<GetUserResponse> result = getUserRepository.getUser(username);
//                if (result instanceof Result.Success) {
//                    user = ((Result.Success<GetUserResponse>) result).getData();
//                }
//                else {
//                    user = null;
//                }
//            }
//        });
//    }

    public GetUserResponse returnUser() {
        return user;
    }

}