package shelter.project.com.projectshelter.retrofit;

import android.content.SharedPreferences;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shelter.project.com.projectshelter.data.AnimalPOJO;
import shelter.project.com.projectshelter.data.AnimalsResponse;
import shelter.project.com.projectshelter.data.SheltersResponse;
import shelter.project.com.projectshelter.data.UserRepository;
import shelter.project.com.projectshelter.data.UserResponse;
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

    public static void getAnimalsSearch(final RetrofitCallbacks.getAnimalsCallback callback, String typeAnimal) {
        // http://mojamalica.com/search.php?species=2&town=1000

        final ShelterProjectAPIService apiService = RetrofitServiceClient.createService(ShelterProjectAPIService.class);
        Call<AnimalsResponse> call = apiService.getSearchAnimals(typeAnimal);
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

    public static void setAnimalFavourite(RetrofitCallbacks.changeFavouriteAnimal changeFavouriteAnimal, String email, AnimalPOJO animalPOJO, boolean changeToFavourite) {
        final ShelterProjectAPIService apiService = RetrofitServiceClient.createService(ShelterProjectAPIService.class);
        Call<UserResponse> call;
        final String id = animalPOJO.getId();
        if (changeToFavourite) {
            call = apiService.addFavourite(email, id);
        } else {
            call = apiService.removeFavourite(email, id);
        }

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                changeFavouriteAnimal.onSuccess();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                changeFavouriteAnimal.onFailed(t.getMessage());
            }
        });
    }

    public static void registerUser(UserRepository mUserRepository, SharedPreferences sharedPreferences, String email, String password, OnRegisterCompletedListener onRegisterCompletedListener) {

    }

    public static void loginUserNormalToServer(UserRepository mUserRepository, String email, String password, OnLoginCompletedListener onLoginCompletedListener) {
        final ShelterProjectAPIService apiService = RetrofitServiceClient.createService(ShelterProjectAPIService.class);
        Call<UserResponse> call = apiService.login(email, password);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                mUserRepository.saveNewUser(email);
                onLoginCompletedListener.onLoginSuccess();
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                onLoginCompletedListener.onLoginFailed();
            }
        });
    }
}
