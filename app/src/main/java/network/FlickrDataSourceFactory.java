package network;

import android.arch.paging.DataSource;

public class FlickrDataSourceFactory extends DataSource.Factory<Integer, Photo> {
    private String query;
    private int page;
    private ApiService apiService;


    public FlickrDataSourceFactory(ApiService apiService, String query, int page) {
        this.query = query;
        this.page = page;
        this.apiService = apiService;
    }

    @Override
    public DataSource<Integer, Photo> create() {
        return new FlickrSearchDataSource(apiService, query, page);
    }
}
