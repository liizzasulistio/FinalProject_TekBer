package com.example.myclassroom.auth.google;

import com.example.myclassroom.auth.LoginDataSource;
import com.example.myclassroom.auth.LoginRepository;
import com.example.myclassroom.auth.Result;
import com.example.myclassroom.auth.model.LoggedInUser;


public class GoogleLoginRepository extends LoginRepository {
    private GoogleLoginDataSource dataSource;

    public GoogleLoginRepository(GoogleLoginDataSource dataSource){
        super(dataSource);
        this.dataSource = dataSource;
    }

    public LoginDataSource.LoginTask<Result<LoggedInUser>> login(String idToken){
        LoginDataSource.LoginTask<Result<LoggedInUser>> result = dataSource.login(idToken);
        result.addOnCompleteListener(
        new LoginDataSource.OnLoginCompleteListener<Result<LoggedInUser>>(){
            public void OnComplete(Result<LoggedInUser> result1){
                if(result1 instanceof Result.Success){
                    setLoggedInUser((LoggedInUser) ((Result.Success) result1).getData());
                }
            }
        });
        return result;
    }
}