package shelter.project.com.projectshelter.main_activity;

import android.content.SharedPreferences;

import shelter.project.com.projectshelter.helpers.SharedPreferencesHelper;

/**
 * Created by primo on 7. 11. 2017.
 */

public class MainPresenter implements MainContract.Presenter {
    private final String TAG = "MainPresenter";

    private final MainContract.View mView;
    SharedPreferences sharedPreferences;

    public MainPresenter(SharedPreferences sharedPreferences, MainContract.View mView) {
        this.mView = mView;
        this.sharedPreferences = sharedPreferences;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        if (SharedPreferencesHelper.isFirstTime(sharedPreferences)) {
            SharedPreferencesHelper.saveFirstTime(sharedPreferences,false);
            mView.showIntro();
        } else {
            mView.setupNavigation();
            mView.showHome();
        }
    }


    @Override
    public void loadHome() {
        mView.showHome();
    }

    @Override
    public void loadSearch() {
        mView.showSearch();

    }

    @Override
    public void loadFavourite() {
        mView.showFavourite();

    }

    @Override
    public void loadMyAccount() {
        mView.showMyAccount();

    }


}
