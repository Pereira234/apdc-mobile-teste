package com.example.lastapp.ui.updateUser;

import android.util.Patterns;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lastapp.R;
import com.example.lastapp.data.Result;
import com.example.lastapp.data.UpdateUserRepository;

import java.util.concurrent.Executor;

public class UpdateUserViewModel extends ViewModel {

    private MutableLiveData<UpdateUserFormState> updateUserFormState = new MutableLiveData<>();
    private MutableLiveData<UpdateUserResult> updateUserResult = new MutableLiveData<>();
    private UpdateUserRepository updateUserRepository;

    private final Executor executor;

    UpdateUserViewModel(UpdateUserRepository updateUserRepository, Executor executor) {
        this.updateUserRepository = updateUserRepository;
        this.executor = executor;
    }

    LiveData<UpdateUserFormState> getUpdateUserFormState() {
        return updateUserFormState;
    }

    LiveData<UpdateUserResult> getUpdateUserResult() { //need to update!
        return updateUserResult;
    }

    public void updateUser(String username, String tokenId, String name, String address, String zipcode, String cellphone, String description) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                Result<Void> result = updateUserRepository.updateUser(username, tokenId, name, address, zipcode, cellphone, description);
                if (result instanceof Result.Success) {
                    updateUserResult.postValue(new UpdateUserResult(true));
                } else {
                    updateUserResult.postValue(new UpdateUserResult(R.string.update_user_failed));
                }
            }
        });
    }

    public void updateUserDataChanged(String name, String address, String zipcode, String cellphone, String description) {

        updateUserFormState.setValue(new UpdateUserFormState(true));
    }

}