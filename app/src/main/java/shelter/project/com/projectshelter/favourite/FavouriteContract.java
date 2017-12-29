package shelter.project.com.projectshelter.favourite;

import java.util.List;

import shelter.project.com.projectshelter.BasePresenter;
import shelter.project.com.projectshelter.BaseView;
import shelter.project.com.projectshelter.data.AnimalPOJO;

/**
 * Created by primo on 7. 11. 2017.
 */

public interface FavouriteContract {
    interface View extends BaseView<Presenter> {
        void showListAnimals(List<AnimalPOJO> animalPOJOList);

        void showMessageError(String error);

        //   void showListShelters(List<Shelter> animalPOJOList);
    }

    interface Presenter extends BasePresenter {

        void loadFavouriteAnimals();

        void removeFavouriteAnimal();
    }
}
