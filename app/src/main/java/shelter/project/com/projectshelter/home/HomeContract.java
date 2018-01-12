package shelter.project.com.projectshelter.home;

import java.util.List;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.ShelterPOJO;
import shelter.project.com.projectshelter.listeners.OnAnimalFavouriteResponse;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showMessageError(String error);

        void showAnimals(List<AnimalPOJO> animalPOJOList);

        void showLogin();

        void showShelters(List<ShelterPOJO> shelterPOJOS);

        void hideProgressBarAnimals();
        void hideProgressBarShelters();
    }

    interface Presenter extends BasePresenter {
        void loadListAnimals(int species, int town);

        void loadListShelters();

        void changeFavouriteAnimal(AnimalPOJO animalPOJO, OnAnimalFavouriteResponse onAnimalFavouriteResponse, boolean toFavourite);

        void openAnimal(AnimalPOJO animalPOJO);

        void openShelter(ShelterPOJO shelterPOJO);
    }
}
