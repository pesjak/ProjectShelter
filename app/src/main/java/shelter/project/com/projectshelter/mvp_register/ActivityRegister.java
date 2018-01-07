package shelter.project.com.projectshelter.mvp_register;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.Toast;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.helpers.ValidationFunctions;
import shelter.project.com.projectshelter.listeners.ValidationLoginListener;
import shelter.project.com.projectshelter.main_activity.MainActivity;


public class ActivityRegister extends AppCompatActivity implements RegisterContract.View, ValidationLoginListener {

    public static final String TAG = ActivityRegister.class.getSimpleName();

    @BindView(R.id.inputRegisterEmail)
    TextInputEditText inputRegisterEmail;
    @BindView(R.id.tiEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.inputRegisterPassword)
    TextInputEditText inputRegisterPassword;
    @BindView(R.id.tiPassword)
    TextInputLayout layoutPassword;
    @BindView(R.id.progressBarRegister)
    ProgressBar progressBarRegister;

    RegisterContract.Presenter mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register); //Nastavimo xml layout
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out); //Animation
        unbinder = ButterKnife.bind(this);

        mPresenter = new RegisterPresenter(UserRepository.getInstance(), getSharedPreferences(getString(R.string.preference_file), MODE_PRIVATE), this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick(R.id.btnNormalRegister)
    public void onViewClicked() {
        if (ValidationFunctions.validateUserInput(inputRegisterEmail, inputRegisterPassword, this)) {
            progressBarRegister.setVisibility(View.VISIBLE);
            mPresenter.registerNormal(inputRegisterEmail, inputRegisterPassword);
        }
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(ActivityRegister.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showMessageRegisterSuccess() {
        Toast.makeText(this, R.string.message_registration_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageRegisterFailed() {
        Toast.makeText(this, R.string.message_registration_failed, Toast.LENGTH_SHORT).show();
        progressBarRegister.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(RegisterContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    @Override
    public void validationSuccessEmail() {
        layoutEmail.setError(null);
        layoutEmail.setErrorEnabled(false);
    }

    @Override
    public void validationFailedEmail() {
        layoutEmail.setErrorEnabled(true);
        layoutEmail.setError(getString(R.string.err_msg_email));
        requestFocus(inputRegisterEmail);
    }

    @Override
    public void validationSuccessPassword() {
        layoutPassword.setError(null);
        layoutPassword.setErrorEnabled(false);
    }

    @Override
    public void validationFailedPassword() {
        layoutPassword.setErrorEnabled(true);
        layoutPassword.setError(getString(R.string.err_msg_password));
        requestFocus(inputRegisterPassword);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.trans_right_in, R.anim.trans_right_out); //Animation

    }

    /*Private Methods*/
    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }
}
