package com.example.c_auevangelista.sample.api;

import com.example.c_auevangelista.sample.models.GitHubModel;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHubService {
    @GET("photos")
    Observable<List<GitHubModel>> register();
}
