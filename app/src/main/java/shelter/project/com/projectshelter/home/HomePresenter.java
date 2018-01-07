package shelter.project.com.projectshelter.home;

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

public class HomePresenter implements HomeContract.Presenter {
    private final String TAG = "MainPresenter";

    private final HomeContract.View mView;
    private UserRepository mUserRepository;

    public HomePresenter(HomeContract.View mView, UserRepository mUserRepository) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        /*
        * Check if search saved in local base
        * Send that search to request
        * If no search show random animals
        *
        * */
        loadListShelters();
        loadListAnimals(2, 1000);
    }


    @Override
    public void loadListAnimals(int species, int town) {
        RetrofitHelper.getAnimals(new RetrofitCallbacks.getAnimalsCallback() {
            @Override
            public void onAnimalsLoaded(List<AnimalPOJO> animalPOJOList) {
                mView.showFavouriteAnimals(animalPOJOList);
            }

            @Override
            public void onErrorGettingAnimals(String error) {
                mView.showMessageError(error);
            }
        }, species, town);
    }

    @Override
    public void loadListShelters() {

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
            }, animalPOJO, toFavourite);
        }else{
            mView.showLogin();
        }
    }

    @Override
    public void openAnimal(AnimalPOJO animalPOJO) {

    }
}
