package utils;

import android.content.Context;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroHelper {
    private static final String BASEURL = "http://api.androidhive.info/json/";
    private static Context mContext;



    public static Retrofit getAdapter(Context ctx, String serverUrl) {
        mContext = ctx;
        Gson gson = new Gson();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL + serverUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit;
    }
}