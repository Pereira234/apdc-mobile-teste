package com.example.lastapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import android.util.Patterns;

import com.example.lastapp.data.RegisterRepository;
import com.example.lastapp.data.Result;             //update?
import com.example.lastapp.data.model.RegisterData; //former LoggedInUser
import com.example.lastapp.R;
import com.example.lastapp.ui.register.RegisteredUserView;
import com.example.lastapp.ui.register.RegisterFormState;
import com.example.lastapp.ui.register.RegisterResult;

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

    LiveData<RegisterFormState> getLoginFormState() {
        return registerFormState;
    } //need to update!

    LiveData<RegisterResult> getLoginResult() { //need to update!
        return registerResult;
    }

    public void register(String username, String password, String confirmation, String name, String email) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<Integer> result = registerRepository.register(username, password, confirmation, name, email); //Not sure about Result type
                if (result instanceof Result.Success) {
                    RegisterData data = ((Result.Success<RegisterData>) result).getData();
                    registerResult.postValue(new RegisterResult(new RegisteredUserView(data.getUsername())));
                } else {
                    registerResult.postValue(new RegisterResult(R.string.register_failed));
                }
            }
        });
    }

    public void register_old(String username, String password, String confirmation, String name, String email) {
        // can be launched in a separate asynchronous job
        Result<Integer> result = registerRepository.register(username, password, confirmation, name, email); //Not sure about Result type

        if (result instanceof Result.Success) {
            RegisterData data = ((Result.Success<RegisterData>) result).getData();
            registerResult.setValue(new RegisterResult(new RegisteredUserView(data.getUsername())));
        } else {
            registerResult.setValue(new RegisterResult(R.string.register_failed));
        }
    }

    public void registerDataChanged(String username, String email, String password) {
        if (!isUserNameValid(username)) {
            registerFormState.setValue(new RegisterFormState(R.string.invalid_username, null, null));
        } else if (!isEmailValid(email)) {
            registerFormState.setValue(new RegisterFormState(null, R.string.invalid_email, null));
        }else if(!isPasswordValid(password)){
            registerFormState.setValue(new RegisterFormState(null, null, R.string.invalid_password));
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
        if (email.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        } else {
            return !email.trim().isEmpty();
        }
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        return password != null && password.trim().length() > 5;
    }
}