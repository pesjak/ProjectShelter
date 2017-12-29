package shelter.project.com.projectshelter.home;

import java.util.List;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;
import shelter.project.com.projectshelter.data.AnimalPOJO;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void showMessageError(String error);

        void showFavouriteAnimals(List<AnimalPOJO> animalPOJOList);
    }

    interface Presenter extends BasePresenter {
        void loadListAnimals(int species, int town);

        void loadListShelters();

        void removeFromFavourites(AnimalPOJO animalPOJO);

        void addFavourites(AnimalPOJO animalPOJO);

        void openAnimal(AnimalPOJO animalPOJO);
    }
}
