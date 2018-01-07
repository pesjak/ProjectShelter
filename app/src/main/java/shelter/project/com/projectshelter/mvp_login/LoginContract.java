package shelter.project.com.projectshelter.mvp_login;

import android.content.Intent;
import android.widget.EditText;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;


/**
 * Created by primo on 7. 11. 2017.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void showMainActivity();

        void showForgottenPassword();

        void showRegistration();

        void showMessageLoginSuccess();

        void showMessageLoginFailed();
    }

    interface Presenter extends BasePresenter {

        void loginNormal(EditText email, EditText password);

        void openMainActivity();

        void openForgottenPassword();

        void openRegistration();

    }
}
