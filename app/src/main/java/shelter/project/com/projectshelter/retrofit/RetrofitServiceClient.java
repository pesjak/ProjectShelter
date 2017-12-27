package shelter.project.com.projectshelter.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Primož Pesjak on 12/11/2017.
 */

class RetrofitServiceClient {

    public static final String BASE_URL = "http://mojamalica.com/";

    private static Retrofit retrofit = null;

    static <S> S createService(
            Class<S> serviceClass) {
        retrofit = getClient();
        return retrofit.create(serviceClass);
    }

    private static Retrofit getClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }
}