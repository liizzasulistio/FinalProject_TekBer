package com.example.myclassroom.ui.login;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.example.myclassroom.R;
import com.example.myclassroom.auth.LoginDataSource;
import com.example.myclassroom.auth.LoginRepository;
import com.example.myclassroom.auth.LoginSingleton;
import com.example.myclassroom.auth.google.GoogleLoginDataSource;
import com.example.myclassroom.auth.google.GoogleLoginRepository;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import static android.provider.Settings.System.getString;

/**
 * ViewModel provider factory to instantiate LoginViewModel.
 * Required given LoginViewModel has a non-empty constructor
 */
public class LoginViewModelFactory implements ViewModelProvider.Factory {

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        new LoginSingleton(new GoogleLoginRepository(
                new GoogleLoginDataSource()
        ));
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(
                LoginSingleton.getRepository()
            );
        } else {
            throw new IllegalArgumentException("Unknown ViewModel class");
        }
    }
}