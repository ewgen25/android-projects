package com.example.retrofitdiag;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonApi {
    @GET("all")
    Call<List<Country>> getPosts();
}
