package network;

import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class FlickrSearchDataSource extends PageKeyedDataSource<Integer, Photo> {

    private String query;
    private int page;
    private ApiService apiService;


    public FlickrSearchDataSource(ApiService apiService, String query, int page) {
        this.query = query;
        this.page = page;
        this.apiService = apiService;
    }


    @Override
    public void loadInitial(@NonNull final LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Photo> callback) {
        Log.i("FlickrDataSource", "Data source loadInitial");
        apiService.getSearchResults(query, page)
                .subscribe(new Observer<FlickrResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlickrResponse flickrResponse) {
                        int initPage = page == 0 ? null : page-1;
                        callback.onResult(flickrResponse.getPhotos().getPhoto(), initPage, page+1);
                        page++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Flickr Api", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Photo> callback) {
        Log.i("FlickrDataSource", "Data source loadBefore");
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Photo> callback) {
        Log.i("FlickrDataSource", "Data source loadAfter");

        apiService.getSearchResults(query, page)
                .subscribe(new Observer<FlickrResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FlickrResponse flickrResponse) {
                        int initPage = page == 0 ? null : page-1;
                        callback.onResult(flickrResponse.getPhotos().getPhoto(), page+1);
                        page++;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Flickr Api", e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }



}
