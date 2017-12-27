package shelter.project.com.projectshelter.retrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import shelter.project.com.projectshelter.data.AnimalsResponse;
import shelter.project.com.projectshelter.helpers.SharedPreferencesHelper;

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

      /*

       user.enqueue(new Callback<AnimalsResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                Log.d("Response Raw", response.raw() + "");
                try {
                    UserBody userBody = response.body().getBody();
                    if (userBody != null) {
                        *//*
                        String name = userBody.getFull_name();
                        String id = String.valueOf(userBody.getId());
                        *//*
                        SharedPreferencesHelper.savePreferencesFirstStart(sharedPreferences, false);
                        userRepository.saveNewUser(userBody);
                        onLoginCompletedListener.onLoginSuccess();

                    } else {
                        LoginManager.getInstance().logOut();
                        String error = response.body().getHeader().getValidation_errors().getPassword();
                        onLoginCompletedListener.onLoginFailed();
                    }
                } catch (Exception ex) {
                    Log.e("Napaka pri Parsanju", ex.toString());
                    onLoginCompletedListener.onLoginFailed();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("Error pri prijavi", t.toString());
                onLoginCompletedListener.onLoginFailed();
                // MyFunctions.myShortToast(mContext, "Preverite povezavo do spleta");
            }
        });*/

    }

    public static void getShelters() {
    }

}
