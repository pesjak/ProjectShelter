package shelter.project.com.projectshelter.retrofit;

import android.content.SharedPreferences;
import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.AnimalsResponse;
import shelter.project.com.projectshelter.data.SheltersResponse;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.helpers.SharedPreferencesHelper;
import shelter.project.com.projectshelter.mvp_register.listeners.OnRegisterCompletedListener;

/**
 * Created by Primož on 27/12/2017.
 */

public class RetrofitHelper {
    private static final String TAG = "RetrofitHelper";


    public static void getAnimals(final RetrofitCallbacks.getAnimalsCallback callback, int species, int town) {
        // http://mojamalica.com/search.php?species=2&town=1000

        final ShelterProjectAPIService apiService = RetrofitServiceClient.createService(ShelterProjectAPIService.class);
        Call<AnimalsResponse> call = apiService.getAnimals(species, town);
        call.enqueue(new Callback<AnimalsResponse>() {
            @Override
            public void onResponse(Call<AnimalsResponse> call, Response<AnimalsResponse> response) {
                callback.onAnimalsLoaded(response.body().getData());
            }

            @Override
            public void onFailure(Call<AnimalsResponse> call, Throwable t) {
                callback.onErrorGettingAnimals(t.getMessage());
            }
        });

    }

    public static void getShelters(RetrofitCallbacks.getSheltersCallback callback) {
        //  http://mojamalica.com/GetShelters.php

        final ShelterProjectAPIService apiService = RetrofitServiceClient.createService(ShelterProjectAPIService.class);
        Call<SheltersResponse> call = apiService.getShelters();
        call.enqueue(new Callback<SheltersResponse>() {
            @Override
            public void onResponse(Call<SheltersResponse> call, Response<SheltersResponse> response) {
                callback.onSheltersLoaded(response.body().getData());
            }

            @Override
            public void onFailure(Call<SheltersResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });


    }

    public static void setAnimalFavourite(RetrofitCallbacks.changeFavouriteAnimal changeFavouriteAnimal, AnimalPOJO animalPOJO, boolean changeToFavourite) {

    }

    public static void registerUser(UserRepository mUserRepository, SharedPreferences sharedPreferences, String email, String password, OnRegisterCompletedListener onRegisterCompletedListener) {

    }

    public static void loginUserNormalToServer(UserRepository mUserRepository, SharedPreferences sharedPreferences, String email, String password, OnLoginCompletedListener onLoginCompletedListener) {

    }
}
