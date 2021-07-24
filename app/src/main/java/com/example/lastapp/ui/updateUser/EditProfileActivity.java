package com.example.lastapp.ui.updateUser;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lastapp.GWApp;
import com.example.lastapp.R;
import com.example.lastapp.ui.login.LoginActivity;

public class EditProfileActivity extends AppCompatActivity {

    private UpdateUserViewModel updateUserViewModel;
    private EditProfileActivity mActivity;

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF = "mypref";
    private static final String USERNAME_KEY = "username";
    private static final String TOKEN_ID_KEY = "tokenid";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        mActivity = this;

        updateUserViewModel = new ViewModelProvider(this, new UpdateUserViewModelFactory(((GWApp) getApplication()).getExecutorService()))
                .get(UpdateUserViewModel.class);

        final EditText nameEditText = findViewById(R.id.editName);
        final EditText cellphoneEditText = findViewById(R.id.editCellphone);
        final EditText addressEditText = findViewById(R.id.editAddress);
        final EditText zipcodeEditText = findViewById(R.id.editZipcode);
        final EditText descriptionEditText = findViewById(R.id.editDescription);
        final Button button = findViewById(R.id.updateProfileBtn);

        updateUserViewModel.getUpdateUserFormState().observe(this, new Observer<UpdateUserFormState>() {
            @Override
            public void onChanged(@Nullable UpdateUserFormState updateUserFormState) {
                if (updateUserFormState == null) {
                    return;
                }
                button.setEnabled(updateUserFormState.isDataValid());

            }
        });

        updateUserViewModel.getUpdateUserResult().observe(this, new Observer<UpdateUserResult>() {
            @Override
            public void onChanged(@Nullable UpdateUserResult updateUserResult) {
                if (updateUserResult == null) {
                    return;
                }

                if (updateUserResult.getError() != null) {
                    showUpdateUserFailed(updateUserResult.getError());
                }
                if (updateUserResult.getSuccess() != null) {
                    showUpdateUserSuccess();
                    setResult(Activity.RESULT_OK);
//                    Intent intent = new Intent(mActivity, LoginActivity.class);
//                    startActivity(intent);
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
                updateUserViewModel.updateUserDataChanged(nameEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        zipcodeEditText.getText().toString(),
                        cellphoneEditText.getText().toString(),
                        descriptionEditText.getText().toString());
            }
        };

        nameEditText.addTextChangedListener(afterTextChangedListener);
        addressEditText.addTextChangedListener(afterTextChangedListener);
        zipcodeEditText.addTextChangedListener(afterTextChangedListener);
        cellphoneEditText.addTextChangedListener(afterTextChangedListener);
        descriptionEditText.addTextChangedListener(afterTextChangedListener);

        sharedPreferences = getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(USERNAME_KEY, "");
        String tokenId = sharedPreferences.getString(TOKEN_ID_KEY, "");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserViewModel.updateUser(username, tokenId,
                        nameEditText.getText().toString(),
                        addressEditText.getText().toString(),
                        zipcodeEditText.getText().toString(),
                        cellphoneEditText.getText().toString(),
                        descriptionEditText.getText().toString());
            }
        });

    }

    private void showUpdateUserFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }

    private void showUpdateUserSuccess() {
        String message = getString(R.string.register_successful);
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

    }
}