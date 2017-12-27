package shelter.project.com.projectshelter.retrofit;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import shelter.project.com.projectshelter.data.AnimalsResponse;

/**
 * Created by primo on 21. 11. 2017.
 */

public interface ShelterProjectAPIService {
    @GET("search.php")
    Call<AnimalsResponse> getAnimals(@Query("species")int speciesID, @Query("town")int townID);
}
