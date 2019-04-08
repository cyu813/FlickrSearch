package com.example.flickrsearch.ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.example.flickrsearch.R;

import java.util.List;

import Model.MainActivityViewModel;
import butterknife.BindView;
import butterknife.ButterKnife;
import network.Photo;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;

    @BindView(R.id.search_field)
    public EditText searchField;

    @BindView(R.id.search_button)
    public Button searchButton;

    @BindView(R.id.recycler_view)
    public RecyclerView recyclerView;

    FlickrAdapter flickrAdapter;

    int currentPage = 1;
    GridLayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        flickrAdapter = new FlickrAdapter();
        recyclerView.setAdapter(flickrAdapter);
        mLayoutManager = (GridLayoutManager) recyclerView.getLayoutManager();

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage = 1;
                resetRecyclerView();

                //hide keyboard when user hits search
                viewModel.searchQuery(searchField.getText().toString(), currentPage);
                observeViewModel();
            }
        });

        observeViewModel();
    }

    private void resetRecyclerView() {
        viewModel.reset();
    }

    private void observeViewModel() {
        viewModel.getResults().observe(this, new Observer<PagedList<Photo>>() {
            @Override
            public void onChanged(@Nullable PagedList<Photo> photos) {
                flickrAdapter.submitList(photos);
            }
        });
    }
}
