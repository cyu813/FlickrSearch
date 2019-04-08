package network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FlickrApi {

    private static FlickrApi instance = null;
    private static final String BASE_URL = "https://api.flickr.com/services/rest/";

    private static Retrofit retrofit = null;

    private ApiService apiService;

    static final String FLICKR_RECENT = "?method=flickr.photos.search&api_key=675894853ae8ec6c242fa4c077bcf4a0";

    public static FlickrApi getInstance() {
        if (instance == null) {
            instance = new FlickrApi();
        }
        return  instance;
    }

    private FlickrApi() {
        buildRetrofit();
    }

    private void buildRetrofit() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .baseUrl(BASE_URL)
                    .build();
        }
        apiService = retrofit.create(ApiService.class);
    }

    public ApiService getApiService() {
        return apiService;
    }
}
