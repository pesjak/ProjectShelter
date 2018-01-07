package shelter.project.com.projectshelter.mvp_register;

import android.content.Intent;
import android.widget.EditText;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void showMainActivity();

        void showMessageRegisterSuccess();

        void showMessageRegisterFailed();
    }

    interface Presenter extends BasePresenter {

        void registerNormal(EditText email, EditText password);

        void openMainActivity();

    }
}
