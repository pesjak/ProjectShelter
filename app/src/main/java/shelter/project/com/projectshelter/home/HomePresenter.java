package shelter.project.com.projectshelter.home;

import java.util.List;

import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.retrofit.RetrofitCallbacks;
import shelter.project.com.projectshelter.retrofit.RetrofitHelper;

/**
 * Created by primo on 7. 11. 2017.
 */

public class HomePresenter implements HomeContract.Presenter {
    private final String TAG = "MainPresenter";

    private final HomeContract.View mView;

    public HomePresenter(HomeContract.View mView) {
        this.mView = mView;
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
    public void removeFromFavourites(AnimalPOJO animalPOJO) {

    }

    @Override
    public void addFavourites(AnimalPOJO animalPOJO) {

    }

    @Override
    public void openAnimal(AnimalPOJO animalPOJO) {

    }
}
