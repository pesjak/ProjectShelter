package shelter.project.com.projectshelter.main_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import butterknife.BindView;
import butterknife.ButterKnife;
import shelter.project.com.projectshelter.R;
import shelter.project.com.projectshelter.account.AccountFragment;
import shelter.project.com.projectshelter.favourite.FavouriteFragment;
import shelter.project.com.projectshelter.home.HomeFragment;
import shelter.project.com.projectshelter.intro.IntroActivity;
import shelter.project.com.projectshelter.search.SearchFragment;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.navigation)
    BottomNavigationViewEx navigation;



    private MainPresenter mPresenter;
    final String TAG_FRAGMENT_HOME = "home";
    final String TAG_FRAGMENT_SEARCH = "search";
    final String TAG_FRAGMENT_FAVORITE = "favourite";
    final String TAG_FRAGMENT_ACCOUNT = "account";

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mPresenter.loadHome();
                    return true;
                case R.id.navigation_search:
                    mPresenter.loadSearch();
                    return true;
                case R.id.navigation_favourite:
                    mPresenter.loadFavourite();
                    return true;
                case R.id.navigation_account:
                    mPresenter.loadMyAccount();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(getSharedPreferences(getString(R.string.preference_file), MODE_PRIVATE),this);
        mPresenter.start();
    }

    @Override
    public void setupNavigation() {
        navigation.enableShiftingMode(false);
        navigation.enableItemShiftingMode(false);
        navigation.setTextVisibility(false);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    @Override
    public void showHome() {
        HomeFragment homeFragment = (HomeFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_HOME);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // If fragment doesn't exist yet, create one
        if (homeFragment == null) {
            transaction.add(R.id.fragment_container, new HomeFragment(), TAG_FRAGMENT_HOME);
        } else { // re-use the old fragment
            transaction.replace(R.id.fragment_container, homeFragment, TAG_FRAGMENT_HOME);
        }
        transaction.commit();
    }

    @Override
    public void showSearch() {
        SearchFragment searchFragment = (SearchFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_SEARCH);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // If fragment doesn't exist yet, create one
        if (searchFragment == null) {
            transaction.add(R.id.fragment_container, new SearchFragment(), TAG_FRAGMENT_SEARCH);
        } else { // re-use the old fragment
            transaction.replace(R.id.fragment_container, searchFragment, TAG_FRAGMENT_SEARCH);
        }
        transaction.commit();
    }

    @Override
    public void showFavourite() {
        FavouriteFragment favouriteFragment = (FavouriteFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_FAVORITE);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // If fragment doesn't exist yet, create one
        if (favouriteFragment == null) {
            transaction.add(R.id.fragment_container, new FavouriteFragment(), TAG_FRAGMENT_FAVORITE);
        } else { // re-use the old fragment
            transaction.replace(R.id.fragment_container, favouriteFragment, TAG_FRAGMENT_FAVORITE);
        }
        transaction.commit();
    }

    @Override
    public void showMyAccount() {
        AccountFragment accountFragment = (AccountFragment) getSupportFragmentManager().findFragmentByTag(TAG_FRAGMENT_ACCOUNT);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        // If fragment doesn't exist yet, create one
        if (accountFragment == null) {
            transaction.add(R.id.fragment_container, new AccountFragment(), TAG_FRAGMENT_ACCOUNT);
        } else { // re-use the old fragment
            transaction.replace(R.id.fragment_container, accountFragment, TAG_FRAGMENT_ACCOUNT);
        }
        transaction.commit();
    }

    @Override
    public void showIntro() {
        Intent intent = new Intent(MainActivity.this, IntroActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void setPresenter(MainContract.Presenter mPresenter) {

    }
}
