package com.example.lastapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.lastapp.data.RegisterRepository;
import com.example.lastapp.data.Result;             //update?
import com.example.lastapp.data.model.RegisterRequest; //former LoggedInUser
import com.example.lastapp.R;
import com.example.lastapp.data.model.RegisterResponse;

import java.util.concurrent.Executor;

public class RegisterViewModel extends ViewModel {

    private MutableLiveData<RegisterFormState> registerFormState = new MutableLiveData<>();
    private MutableLiveData<RegisterResult> registerResult = new MutableLiveData<>();
    private RegisterRepository registerRepository; //is it necessary?

    private final Executor executor;

    RegisterViewModel(RegisterRepository registerRepository, Executor executor) {
        this.registerRepository = registerRepository;
        this.executor = executor;
    }

    LiveData<RegisterFormState> getRegisterFormState() {
        return registerFormState;
    }

    //need to update!
    LiveData<RegisterResult> getRegisterResult() { //need to update!
        return registerResult;
    }

    public void register(String username, String password, String confirmation, String name, String email) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<Void> result = registerRepository.register(username, password, confirmation, name, email);
                if (result instanceof Result.Success) {
                    registerResult.postValue(new RegisterResult(true));
                } else {
                    registerResult.postValue(new RegisterResult(R.string.register_failed));
                }
            }
        });
    }

//    public void register_old(String username, String password, String confirmation, String name, String email) {
//        // can be launched in a separate asynchronous job
//        Result<Void> result = registerRepository.register(username, password, confirmation, name, email);
//
//        if (result instanceof Result.Success) {
//            RegisterRequest data = ((Result.Success<RegisterRequest>) result).getData();
//            registerResult.postValue(new RegisterResult(R.string.register_successful));
//        } else {
//            registerResult.setValue(new RegisterResult(R.string.register_failed));
//        }
//    }

    public void registerDataChanged(String username, String email, String password, String name) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null, null));
        } else if (!isEmailValid(email)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_email, null, null));
        }else if(!isPasswordValid(password)){
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password, null));
        }
        else if(!isNameValid(name)){
            registerFormState.setValue(new RegisterFormState(null, null, null, R.string.invalid_name));
        } else {
            registerFormState.setValue(new RegisterFormState(true));
        }
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        } else {
            return !username.trim().isEmpty();
        }
    }

    // A placeholder email validation check
    private boolean isEmailValid(String email) {
        if (email == null) {
            return false;
        }

        return Patterns.EMAIL_ADDRESS.matcher(email).matches();

    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }

    // A placeholder password validation check
    private boolean isNameValid(String name) {
        return name != null;
    }
}