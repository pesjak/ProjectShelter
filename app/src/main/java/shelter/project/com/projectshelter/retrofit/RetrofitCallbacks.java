package shelter.project.com.projectshelter.retrofit;


import java.util.List;
import java.util.Map;

import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.AnimalsResponse;

/**
 * Created by Primož Pesjak on 29/09/2017.
 */

public interface RetrofitCallbacks {

    interface getAnimalsCallback {
        void onAnimalsLoaded(List<AnimalPOJO> estates);

        void onErrorGettingAnimals(String error);
    }
    interface changeFavouriteAnimal {
        void onSuccess();

        void onFailed(String error);
    }
}
