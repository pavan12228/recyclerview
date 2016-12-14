package com.example.ravi.restful.Layouts;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.ravi.restful.Adapters.CustomAdapter1;
import com.example.ravi.restful.Models.Movies;
import com.example.ravi.restful.R;
import com.example.ravi.restful.Services.RestApi;
import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import utils.Util;

public class MainActivity extends AppCompatActivity {
    LinearLayout linearLayout;
    ArrayList<Movies> moviesArrayList=new ArrayList<>();
    RecyclerView recList;
    Util  util;
    SwipeRefreshLayout refreshLayout;
    public static final String Root_url="http://api.androidhive.info/json";
     Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        util=new Util();
        linearLayout= (LinearLayout) findViewById(R.id.activity_main);
        refreshLayout= (SwipeRefreshLayout) findViewById(R.id.swipe_refreshing);
        recList = (RecyclerView) findViewById(R.id.lvitems);
       // toolbar= (Toolbar) findViewById(R.id.toolbar);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
        inserUser();
        setSupportActionBar(toolbar);
        refreshLayout.post(new Runnable() {
            @Override
            public void run() {

                refreshLayout.setRefreshing(true);
                   inserUser();

            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                inserUser();
            }
        });



    }



    private void inserUser() {



        if (util.isNetworkAvailable(getApplicationContext())) {

            RestAdapter adapter = new RestAdapter.Builder().setEndpoint(Root_url).build();

            RestApi api = adapter.create(RestApi.class);

            api.Mymeth(new Callback<JsonArray>() {
                @Override
                public void success(JsonArray jsonElements, Response response) {
                    for (int i = 0; i < jsonElements.size(); i++) {
                       try {

                            JsonObject jsonObject = jsonElements.get(i).getAsJsonObject();
                            String mTitle = jsonObject.get("title").getAsString();
                            String mImage = jsonObject.get("image").getAsString();
                            String mRating = jsonObject.get("rating").getAsString();
                            String mReleaseYear = jsonObject.get("releaseYear").getAsString();

                           JsonArray jsonElements1=jsonObject.get("genre").getAsJsonArray();
                           for (int i1 = 0; i1 < jsonElements1.size(); i1++) {

                             String mname=jsonElements1.get(i1).getAsString();



                               Movies movies = new Movies();
                               movies.setTitlte(mTitle);
                               movies.setImage(mImage);
                               movies.setGenre(mname);
                               movies.setRating(Double.parseDouble(mRating));
                               movies.setYear(Integer.parseInt(mReleaseYear));
                               moviesArrayList.add(movies);

                           }

                        }
                        catch (JsonIOException e )
                        {
                            e.printStackTrace();
                        }

                    }
                    CustomAdapter1 customAdapter = new CustomAdapter1(moviesArrayList, getApplicationContext());
                                 recList.setAdapter(customAdapter);

                   // customAdapter.notifyDataSetChanged();
                          refreshLayout.setRefreshing(false);


                }


                @Override
                public void failure(RetrofitError error) {

                    callToast("Retrofit error " +error.toString());
                    Log.d("Error",error.toString());
                    refreshLayout.setRefreshing(false);
                }
            });
            





        }
        else {
            Snackbar snackbar = Snackbar
                    .make(linearLayout, "No internet Connection Retry!!!!", Snackbar.LENGTH_LONG);

            snackbar.show();

        }
    }

    public void callToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }


}