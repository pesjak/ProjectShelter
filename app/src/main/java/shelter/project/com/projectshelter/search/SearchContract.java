package shelter.project.com.projectshelter.search;

import java.util.List;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.ShelterPOJO;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface SearchContract {
    interface View extends BaseView<Presenter> {
        void showMessageError(String error);

        void showUserPreference();

        void showLogin();

        void showLoading();

        void hideLoading();

        void showSearchResults(List<AnimalPOJO> animalPOJO);
    }

    interface Presenter extends BasePresenter {
        void loadUserSearch();

        void sendUserSearch(String typeAnimal);

        void loadAnimalDetails(AnimalPOJO animalPOJO);


        void changeFavouriteAnimal(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse, boolean toFavourite);
    }
}
