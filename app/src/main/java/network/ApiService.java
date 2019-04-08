package network;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static network.FlickrApi.FLICKR_RECENT;

public interface ApiService {

    @GET(FLICKR_RECENT + "&extras=url_sq&format=json&nojsoncallback=1&per_page=25")
    public Observable<FlickrResponse> getSearchResults(@Query("text") String query, @Query("page") int page);
}
