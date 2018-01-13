package shelter.project.com.projectshelter.search;

import android.util.Log;

import java.util.List;

import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.RealmUser;
import shelter.project.com.projectshelter.data.UserDataSource;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;
import shelter.project.com.projectshelter.retrofit.RetrofitCallbacks;
import shelter.project.com.projectshelter.retrofit.RetrofitHelper;

/**
 * Created by primo on 7. 11. 2017.
 */

public class SearchPresenter implements SearchContract.Presenter {
    private final String TAG = "SearchPresenter";

    private final SearchContract.View mView;
    private UserRepository mUserRepository;
    private RealmUser loggedInUser;

    public SearchPresenter(SearchContract.View mView, UserRepository mUserRepository) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mUserRepository.getUser(new UserDataSource.GetLoggedInUserCallback() {
            @Override
            public void onUserLoggedIn(RealmUser user) {
                loggedInUser = user;
            }

            @Override
            public void noUsers() {

            }
        });
    }


    @Override
    public void loadUserSearch() {

    }

    @Override
    public void sendUserSearch(String typeAnimal) {
        Log.d(TAG, "sendUserSearch: " + typeAnimal);
        //   RetrofitHelper.sendUserSearch();
        mView.showLoading();

        RetrofitHelper.getAnimalsSearch(new RetrofitCallbacks.getAnimalsCallback() {
            @Override
            public void onAnimalsLoaded(List<AnimalPOJO> animalPOJOS) {
                if (animalPOJOS != null) {
                    mView.showSearchResults(animalPOJOS);
                } else {
                    mView.showMessageError("Nič nismo našli :(");
                    mView.showSearchResults(null);
                }
                mView.hideLoading();
            }

            @Override
            public void onErrorGettingAnimals(String error) {
                mView.showMessageError(error);
                mView.hideLoading();
            }
        }, typeAnimal);
    }

    @Override
    public void loadAnimalDetails(AnimalPOJO animalPOJO) {
        mView.showMessageError("Opened animalPOJO");
    }

    @Override
    public void changeFavouriteAnimal(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse, boolean toFavourite) {
        if (mUserRepository.isUserLoggedIn()) {
            RetrofitHelper.setAnimalFavourite(new RetrofitCallbacks.changeFavouriteAnimal() {
                @Override
                public void onSuccess() {
                    onAnimalFavouriteResponse.onSuccess();
                }

                @Override
                public void onFailed(String error) {
                    Log.e(TAG, "onFailed: " + error);
                    onAnimalFavouriteResponse.onFailed();
                }
            }, loggedInUser.getEmail(), animalPOJO, toFavourite);
        } else {
            mView.showLogin();
        }
    }


}
