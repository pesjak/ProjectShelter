package shelter.project.com.projectshelter.favourite;

import shelter.project.com.projectshelter.home.HomeContract;

/**
 * Created by primo on 7. 11. 2017.
 */

public class FavouritePresenter implements FavouriteContract.Presenter {
    private final String TAG = "MainPresenter";

    private final FavouriteContract.View mView;

    public FavouritePresenter(FavouriteContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        loadFavouriteAnimals();
    }


    @Override
    public void loadFavouriteAnimals() {

       /* RetrofitHelper.getAnimals(new RetrofitCallbacks.getAnimalsCallback() {
            @Override
            public void onAnimalsLoaded(List<AnimalPOJO> animalPOJOList) {
                mView.showListAnimals(animalPOJOList);
            }

            @Override
            public void onErrorGettingAnimals(String error) {
                mView.showMessageError(error);
            }
        }, species, town);*/
    }

    @Override
    public void removeFavouriteAnimal() {

    }

}
