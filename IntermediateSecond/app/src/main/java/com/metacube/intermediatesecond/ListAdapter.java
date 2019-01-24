package com.metacube.intermediatesecond;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends ArrayAdapter<MovieModelREST> {

    private Context context;
    private List<MovieModelREST> movieModelRESTS;

    private static class ViewHolder {
        ImageView movieImg;
        TextView movieTitle;
    }

    public ListAdapter(Context context, int resources, List<MovieModelREST> movieModelRESTS) {
        super(context, resources, movieModelRESTS);
        this.context = context;
        this.movieModelRESTS = movieModelRESTS;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item, null, false);
        ImageView movieImg = view.findViewById(R.id.movie_img);
        TextView movieTitle = view.findViewById(R.id.movie_title);
        MovieModelREST modelREST = movieModelRESTS.get(position);
        movieImg.setImageBitmap(modelREST.getThumb());
        movieTitle.setText(modelREST.getTitle());
        return view;
    }
}
