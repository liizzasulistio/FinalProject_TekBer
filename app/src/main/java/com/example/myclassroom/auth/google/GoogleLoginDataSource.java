package com.example.myclassroom.auth.google;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.myclassroom.auth.LoginDataSource;
import com.example.myclassroom.auth.Result;
import com.example.myclassroom.auth.model.LoggedInUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class GoogleLoginDataSource extends LoginDataSource {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    public LoginTask<Result<LoggedInUser>> login(String idToken){
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        Task<AuthResult> signInTask = mAuth.signInWithCredential(credential);
        LoginTask<Result<LoggedInUser>> loginTask = new LoginTask<>();
        signInTask.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("INFO:", "SignInWithCredentials:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    loginTask.setResult(
                            new Result.Success(
                                    new LoggedInUser(user.getUid(), user.getDisplayName())
                            )
                    );
                }else{
                    Log.d("INFO:", "SignInWithCredentials:fail");
                    loginTask.setResult(
                            new Result.Error(
                                    new Exception("Failed Authentication")
                            )
                    );
                }
            }
        });
        return loginTask;
    }

    @Override
    public void logout() {
        mAuth.signOut();
    }
}
