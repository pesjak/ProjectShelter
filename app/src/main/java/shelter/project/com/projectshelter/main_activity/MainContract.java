package shelter.project.com.projectshelter.main_activity;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface MainContract {
    interface View extends BaseView<Presenter> {
        void setupNavigation();

        void showHome();

        void showSearch();

        void showFavourite();

        void showMyAccount();

        void showIntro();

    }

    interface Presenter extends BasePresenter {
        void loadHome();

        void loadSearch();

        void loadFavourite();

        void loadMyAccount();
    }
}
