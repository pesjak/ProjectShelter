package shelter.project.com.projectshelter.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shelter.project.com.projectshelter.data.AnimalsResponse;
import shelter.project.com.projectshelter.data.SheltersResponse;
import shelter.project.com.projectshelter.data.UserResponse;

/**
 * Created by primo on 21. 11. 2017.
 */

public interface ShelterProjectAPIService {
    @GET("login.php")
    Call<UserResponse> login(@Query("email") String email, @Query("password") String pass);

    @GET("search.php")
    Call<AnimalsResponse> getAnimals(@Query("species") int speciesID, @Query("town") int townID);

    @GET("search.php") //todo
    Call<AnimalsResponse> getFavouriteAnimals(@Query("email") String email);

    @GET("AddFavorites.php")
    Call<UserResponse> addFavourite(@Query("email") String email, @Query("Id_Animals") String id);

    @GET("RemoveFavorites.php")
    Call<UserResponse> removeFavourite(@Query("email") String email, @Query("Id_Animals") String id);

    @GET("GetShelters.php")
    Call<SheltersResponse> getShelters();

    @GET("SearchAnimalsByType.php")
    Call<AnimalsResponse> getSearchAnimals(@Query("name") String typeAnimal);
}
