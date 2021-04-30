package com.example.myclassroom.auth;

import com.example.myclassroom.auth.model.LoggedInUser;

/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
public class LoginRepository {

    private LoginDataSource dataSource;

    // If user credentials will be cached in local storage, it is recommended it be encrypted
    // @see https://developer.android.com/training/articles/keystore
    protected LoggedInUser user = null;

    // private constructor : singleton access
    public LoginRepository(LoginDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public boolean isLoggedIn() {
        return user != null;
    }

    public void logout() {
        user = null;
        dataSource.logout();
    }

    protected void setLoggedInUser(LoggedInUser user) {
        this.user = user;
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }

    public LoginDataSource.LoginTask<Result<LoggedInUser>> login(String username, String password) {
        // handle login
        LoginDataSource.LoginTask<Result<LoggedInUser>> result = dataSource.login(username, password);
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