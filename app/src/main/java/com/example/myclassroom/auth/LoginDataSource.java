package com.example.myclassroom.auth;

import com.example.myclassroom.auth.model.LoggedInUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public abstract class LoginDataSource {
    public static class OnLoginCompleteListener<ResultT>{
        public void OnComplete(ResultT result){
        }
    }

    public class LoginTask<ResultT>{
        private ResultT result;
        private Boolean isComplete;

        private List<OnLoginCompleteListener<ResultT>> onLoginCompleteListeners = new ArrayList<OnLoginCompleteListener<ResultT>>();

        // When instantiated with result, it means that the task is completed
        public LoginTask(ResultT object){
            this.isComplete = true;
            this.result = object;
        }
        // When instantiated without result, it means that the task is not completed yet
        public LoginTask(){
            this.isComplete = false;
        }

        // When setting result, it means the task is finished
        // WARNING : NOT THREAD SAFE, MAKE SURE TO ONLY HAVE ONE
        //           THING REFERRING ONE INSTANCE AT A TIME
        public void setResult(ResultT result){
            this.result = result;
            this.isComplete = true;
            for (OnLoginCompleteListener listener: onLoginCompleteListeners) {
                listener.OnComplete(this.result);
            }
        }

        public void addOnCompleteListener(OnLoginCompleteListener listener){
            onLoginCompleteListeners.add(listener);
        }

    }
    public LoginTask<Result<LoggedInUser>> login(String username, String password){
        //TODO: Implement this
        return new LoginTask<Result<LoggedInUser>>(
                new Result.Error(new Exception("Not Implemented"))
        );
    };

    public abstract void logout();
}