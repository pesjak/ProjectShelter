package shelter.project.com.projectshelter.mvp_login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.listeners.ValidationLoginListener;
import shelter.project.com.projectshelter.main_activity.MainActivity;
import shelter.project.com.projectshelter.mvp_register.ActivityRegister;

import static shelter.project.com.projectshelter.helpers.ValidationFunctions.isValidEmail;
import static shelter.project.com.projectshelter.helpers.ValidationFunctions.validateUserInput;

public class ActivityLogin extends AppCompatActivity implements ValidationLoginListener, LoginContract.View {
    public static final String TAG = ActivityLogin.class.getSimpleName();


    @BindView(R.id.inputLoginEmail)
    EditText inputEmail;
    @BindView(R.id.tiEmail)
    TextInputLayout layoutEmail;
    @BindView(R.id.inputLoginPassword)
    EditText inputPassword;
    @BindView(R.id.tiPassword)
    TextInputLayout layoutPassword;
    @BindView(R.id.tvRegister)
    TextView tvRegister;
    @BindView(R.id.progressBarLoggingIn)
    ProgressBar progressBarLoggingIn;

    private Context mContext;
    private LoginContract.Presenter mPresenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_mvp);
        overridePendingTransition(R.anim.trans_left_in, R.anim.trans_left_out); //Animation
        unbinder = ButterKnife.bind(this);
        mContext = getBaseContext();
        initView();
        //Presenter
        mPresenter = new LoginPresenter(UserRepository.getInstance(), getSharedPreferences(getString(R.string.preference_file), Context.MODE_PRIVATE), this);
        mPresenter.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    @OnClick({R.id.tvForgottenPassword, R.id.btnNormalLogin, R.id.tvRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvRegister:
                mPresenter.openRegistration();
                break;
            case R.id.tvForgottenPassword:
                mPresenter.openForgottenPassword();
                break;
            case R.id.btnNormalLogin:
                if (validateUserInput(inputEmail, inputPassword, this)) {
                    progressBarLoggingIn.setVisibility(View.VISIBLE);
                    mPresenter.loginNormal(inputEmail, inputPassword);
                }
                break;
        }
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
        requestFocus(inputEmail);
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
        requestFocus(inputPassword);
    }

    @Override
    public void showMainActivity() {
        Intent intent = new Intent(ActivityLogin.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void showForgottenPassword() {
    //    Intent intent = new Intent(ActivityLogin.this, ActivityForgotPassword.class);
    //    startActivity(intent);
    }

    @Override
    public void showRegistration() {
        Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
        startActivity(intent);
    }

    @Override
    public void showMessageLoginSuccess() {
        Toast.makeText(mContext, R.string.message_login_success, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMessageLoginFailed() {
        Toast.makeText(mContext, R.string.message_login_failed, Toast.LENGTH_SHORT).show();
        progressBarLoggingIn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setPresenter(LoginContract.Presenter mPresenter) {
        this.mPresenter = mPresenter;
    }

    //Private Methods
    private void initView() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN); //Moves up screen when pressed
        //Listener on key pressed to check if it is correct input
        inputEmail.addTextChangedListener(new MyTextWatcher(inputEmail, this));
        inputPassword.addTextChangedListener(new MyTextWatcher(inputPassword, this));
        hideSoftKeyboard(); //So it hides it in OnCreate
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void hideSoftKeyboard() {
        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    class MyTextWatcher implements TextWatcher {

        private View view;
        private ValidationLoginListener listener;

        public MyTextWatcher(View view, ValidationLoginListener listener) {
            this.view = view;
            this.listener = listener;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.inputLoginEmail:
                    validateEmail(editable.toString());
                    break;
                case R.id.inputLoginPassword:
                    validatePassword(editable.toString());
                    break;
            }
        }

        private void validatePassword(String password) {
            if (password.isEmpty()) {
                listener.validationFailedPassword();
            }
            listener.validationSuccessPassword();
        }

        private void validateEmail(String email) {
            if (email.isEmpty() || !isValidEmail(email)) {
                listener.validationFailedEmail();

            }
            listener.validationSuccessEmail();
        }
    }

}
