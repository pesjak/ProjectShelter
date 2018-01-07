package shelter.project.com.projectshelter.mvp_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import org.json.JSONObject;

import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.mvp_register.listeners.OnRegisterCompletedListener;
import shelter.project.com.projectshelter.retrofit.RetrofitHelper;


/**
 * Created by primo on 7. 11. 2017.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private final String TAG = "RegisterPresenter";
    private final RegisterContract.View mView;
    private final UserRepository mUserRepository;
    private SharedPreferences sharedPreferences;

    public RegisterPresenter(UserRepository mUserRepository, SharedPreferences sharedPreferences, RegisterContract.View mView) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        this.sharedPreferences = sharedPreferences;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
    }


    @Override
    public void registerNormal(EditText email, EditText password) {
        RetrofitHelper.registerUser(mUserRepository, sharedPreferences, email.getText().toString(), password.getText().toString(), new OnRegisterCompletedListener() {
            @Override
            public void onRegisterSuccess() {
                mView.showMessageRegisterSuccess();
                openMainActivity();
            }

            @Override
            public void onRegisterFailed() {
                mView.showMessageRegisterFailed();
            }
        });
    }

    @Override
    public void openMainActivity() {
        mView.showMainActivity();
    }


}
