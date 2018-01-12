package shelter.project.com.projectshelter.retrofit;


import java.util.List;
import java.util.Map;

import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.AnimalsResponse;
import shelter.project.com.projectshelter.data.ShelterPOJO;

/**
 * Created by Primož Pesjak on 29/09/2017.
 */

public interface RetrofitCallbacks {

    interface getAnimalsCallback {
        void onAnimalsLoaded(List<AnimalPOJO> animalPOJOS);

        void onErrorGettingAnimals(String error);
    }

    interface getSheltersCallback {
        void onSheltersLoaded(List<ShelterPOJO> shelterPOJOS);

        void onError(String error);
    }
    interface changeFavouriteAnimal {
        void onSuccess();

        void onFailed(String error);
    }
}
