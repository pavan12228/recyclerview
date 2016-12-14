package com.example.ravi.restful.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ravi.restful.Models.Movies;
import com.example.ravi.restful.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

/**
 * Created by Ravi on 02-12-2016.
 */
public class CustomAdapter1 extends RecyclerView.Adapter<CustomAdapter1.CustomViewHolder>  {

    private ArrayList<Movies> moviesArrayList=new ArrayList<>();
       private Context context;
    public CustomAdapter1(ArrayList<Movies> moviesArrayList , Context  context){

        this.moviesArrayList=moviesArrayList;
        this.context=context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.card_layout, viewGroup, false);

        return new CustomViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));

        Movies movies= moviesArrayList.get(i);
        customViewHolder.tvRating.setText(""+(int) movies.getRating());
        customViewHolder.tvYear.setText(""+movies.getYear());
        customViewHolder.tvtitle.setText(movies.getTitlte());
        customViewHolder.tvgenre.setText((CharSequence) movies.getGenre());
        imageLoader.displayImage(movies.getImage(),customViewHolder.ivimage);






    }

    @Override
    public int getItemCount() {
        return moviesArrayList.size();
    }


    public static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView tvRating,tvtitle,tvYear,tvgenre;
        ImageView ivimage;
        public CustomViewHolder(View view) {
            super(view);

            tvRating= (TextView) view.findViewById(R.id.Rating);
             tvtitle= (TextView) view.findViewById(R.id.title);
            tvYear= (TextView) view.findViewById(R.id.year);
            tvgenre= (TextView) view.findViewById(R.id.genre);
            ivimage= (ImageView) view.findViewById(R.id.imageView);
        }
    }
}
