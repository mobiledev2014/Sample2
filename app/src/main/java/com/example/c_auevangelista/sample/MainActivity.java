package com.example.c_auevangelista.sample;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.c_auevangelista.sample.adapter.GitHubRecycler;
import com.example.c_auevangelista.sample.api.GitHubService;
import com.example.c_auevangelista.sample.models.GitHubModel;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.orm.SugarDb;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    private RecyclerView mRecyclerView;
    private Button btnSync;

    private Context context;

    private CompositeDisposable Disposable;

    private GitHubRecycler mAdapter;

    private List<GitHubModel> AList = new ArrayList<>();
    String title,url,thumbnail,_id,albumId;
    private String TAG = this.getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        Disposable = new CompositeDisposable();
        initRecyclerView();
        btnSync.setOnClickListener(v -> loadJSON());


    }

    private void initRecyclerView() {

        btnSync = (Button) findViewById(R.id.btn_sync);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(context, 2);
        mRecyclerView.setLayoutManager(layoutManager);

        //Testing3

    }

    private void loadJSON() {

        GitHubService requestInterface = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(GitHubService.class);

        Disposable.add(requestInterface.register()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(List<GitHubModel> androidList) {
        Log.e(TAG, "handleResponse: " + androidList.toString() );
        AList = androidList;
        mAdapter = new GitHubRecycler(context, AList);
        mRecyclerView.setAdapter(mAdapter);

        Log.e(TAG, "handleResponse: " + androidList.size() );
        for (GitHubModel data : androidList){
            Log.e(TAG, "handleResponse2: "+data.toString() );
            GitHubModel gitHubModel = new GitHubModel(
//                    data.thumbnailUrl,
//                    dat a.albumId,
//                    data._id,
//                    data.title,
//                    data.url
            );
            gitHubModel.setThumbnailUrl(data.getThumbnailUrl());
            gitHubModel.setAlbumId(data.getAlbumId());
            gitHubModel.set_id(data.get_id());
            gitHubModel.setTitle(data.getTitle());
            gitHubModel.setUrl(data.getUrl());

            gitHubModel.save();
        }


    }

    private void handleError(Throwable e) {

        Toast.makeText(this, "Error " + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Disposable.clear();
    }


}

