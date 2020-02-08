package com.example.nicks.myfoodfirst.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nicks.foodfirst.R;

import java.util.List;


/**
 * Created by Meet on 16-10-2017.
 */

public class SubjectAdapter1 extends RecyclerView.Adapter<SubjectAdapter1.MyViewHolder> {

private List<Subject1> moviesList;

public class MyViewHolder extends RecyclerView.ViewHolder {
    public TextView etname,etprice;


    public MyViewHolder(View view) {
        super(view);

        etname=(TextView) view.findViewById(R.id.name);
        etprice=(TextView) view.findViewById(R.id.price);





    }
}


    public SubjectAdapter1(List<Subject1> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.usersubject1, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
       final  Subject1 movie = moviesList.get(position);
        holder.etname.setText(movie.name);
        holder.etprice.setText(movie.price);






}

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
