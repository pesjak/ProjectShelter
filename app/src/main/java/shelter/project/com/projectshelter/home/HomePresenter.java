package shelter.project.com.projectshelter.home;

import android.util.Log;

import java.util.List;

import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.RealmUser;
import shelter.project.com.projectshelter.data.ShelterPOJO;
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
    RealmUser loggedIn;

    public HomePresenter(HomeContract.View mView, UserRepository mUserRepository) {
        this.mView = mView;
        this.mUserRepository = mUserRepository;
        mView.setPresenter(this);
    }

    @Override
    public void start() {

        /*
        * TODO
        * Check if search saved in local base
        * Send that search to request
        * If no search show random animals
        *
        * */


        loadListShelters();
        mUserRepository.getUser(new UserDataSource.GetLoggedInUserCallback() {
            @Override
            public void onUserLoggedIn(RealmUser user) {
                loggedIn = user;
            }

            @Override
            public void noUsers() {

            }
        });

        loadListAnimals();

    }


    @Override
    public void loadListAnimals() {
        RetrofitHelper.getAnimalsSearch(new RetrofitCallbacks.getAnimalsCallback() {
            @Override
            public void onAnimalsLoaded(List<AnimalPOJO> animalPOJOS) {
                if (animalPOJOS != null) {
                    mView.showAnimals(animalPOJOS);
                    mView.hideProgressBarAnimals();
                } else {
                    mView.showMessageError("Nič nismo našli :(");
                }
            }

            @Override
            public void onErrorGettingAnimals(String error) {
                mView.showMessageError(error);
            }
        }, "vseeno");
    }

    @Override
    public void loadListShelters() {
        RetrofitHelper.getShelters(new RetrofitCallbacks.getSheltersCallback() {
            @Override
            public void onSheltersLoaded(List<ShelterPOJO> shelterPOJOS) {
                mView.showShelters(shelterPOJOS);
                mView.hideProgressBarShelters();
            }

            @Override
            public void onError(String error) {
                mView.showMessageError(error);

            }
        });

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
            }, loggedIn.getEmail(), animalPOJO, toFavourite);
        } else {
            mView.showLogin();
        }
    }

    @Override
    public void loadAnimalDetails(AnimalPOJO animalPOJO) {
        mView.showMessageError("Opened animalPOJO");
    }

    @Override
    public void openShelter(ShelterPOJO shelterPOJO) {
        mView.showMessageError("Opened Shelter");
    }
}
