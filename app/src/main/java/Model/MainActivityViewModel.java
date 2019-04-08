package Model;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PagedList;

import network.ApiService;
import network.FlickrApi;
import network.FlickrDataSourceFactory;
import network.Photo;

public class MainActivityViewModel extends ViewModel {

    private LiveData<PagedList<Photo>> photos = new MutableLiveData<>();

    private ApiService apiService;
    private FlickrDataSourceFactory factory;

    public MainActivityViewModel() {
        this.apiService = FlickrApi.getInstance().getApiService();
    }

    public void searchQuery(String query, int page) {
        factory = new FlickrDataSourceFactory(apiService, query, page);
        photos = new LivePagedListBuilder<>(factory, 27).build();
    }

    public LiveData<PagedList<Photo>> getResults() {
        return photos;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void reset() {
        if (photos.getValue() != null) {
            photos.getValue().clear();
        }
    }
}
