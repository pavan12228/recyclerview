package com.example.ravi.restful.Services;

import com.google.gson.JsonArray;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Ravi on 17-11-2016.
 */

public interface RestApi
{
    @GET("/movies.json")
    public void Mymeth(Callback<JsonArray> callback);



}
