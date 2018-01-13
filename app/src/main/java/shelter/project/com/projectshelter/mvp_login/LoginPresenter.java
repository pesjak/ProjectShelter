package shelter.project.com.projectshelter.mvp_login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONObject;

import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.retrofit.OnLoginCompletedListener;
import shelter.project.com.projectshelter.retrofit.RetrofitHelper;


/**
 * Created by primo on 7. 11. 2017.
 */

public class LoginPresenter implements LoginContract.Presenter {
    private final String TAG = "LoginPresenter";
    private final LoginContract.View mView;
    private UserRepository mUserRepository;
    private SharedPreferences sharedPreferences;

    LoginPresenter(UserRepository mUserRepository, SharedPreferences sharedPreferences, LoginContract.View mView) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        this.sharedPreferences = sharedPreferences;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void loginNormal(EditText email, EditText password) {
        RetrofitHelper.loginUserNormalToServer(mUserRepository,  email.getText().toString(), password.getText().toString(), new OnLoginCompletedListener() {
            @Override
            public void onLoginSuccess() {
                openMainActivity();
                mView.showMessageLoginSuccess();
            }

            @Override
            public void onLoginFailed() {
                mView.showMessageLoginFailed();
            }
        });
    }


    @Override
    public void openMainActivity() {
        mView.showMainActivity();
    }

    @Override
    public void openForgottenPassword() {
        mView.showForgottenPassword();
    }

    @Override
    public void openRegistration() {
        mView.showRegistration();
    }

}
