package com.example.nicks.myfoodfirst.adpter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nicks.myfoodfirst.Myapp;
import com.example.nicks.myfoodfirst.R;
import com.example.nicks.myfoodfirst.rest_content;
import com.example.nicks.myfoodfirst.restaurents;

import java.util.List;
import java.util.Map;


/**
 * Created by Meet on 16-10-2017.
 */

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {

    private List<Subject> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView area, title, price, name,status;
        public ImageView iv, ivcall, ivmap,vegnonveg;
        public Button btn;

        public MyViewHolder(View view) {
            super(view);
            area=(TextView)view.findViewById(R.id.manjalpur);
            name = (TextView) view.findViewById(R.id.name);
            price = (TextView) view.findViewById(R.id.price);
            ivcall = (ImageView) view.findViewById(R.id.phone);
            ivmap = (ImageView) view.findViewById(R.id.location);
            iv = (ImageView) view.findViewById(R.id.img_piz);
            btn = (Button) view.findViewById(R.id.more);
            vegnonveg=(ImageView)view.findViewById(R.id.imageView3);
            status=(TextView)view.findViewById(R.id.status);

        }
    }


    public SubjectAdapter(List<Subject> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rest_subject, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Subject movie = moviesList.get(position);
        final Map<String, Object> data = movie.data;

        holder.price.setText("APPROX :"+ data.get("price")+"INR PR  COUPLE");
        holder.area.setText(data.get("area")+"");
        holder.name.setText(data.get("title")+"");
        if(data.get("status").equals("off"))
        {
            holder.status.setText("Closed");
        }
        else
        {
            holder.status.setText("Open");
        }
        Glide.with(holder.iv.getContext()).load(data.get("url") + "")
                .override(100, 100)
                .fitCenter()
                .into(holder.iv);


        if(data.get("type").toString().equals("Non Vegetarian"))
        {
            holder.vegnonveg.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.icon_nonveg));
        }
        else
        {

            holder.vegnonveg.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.icon_veg));
        }


        holder.ivmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + data.get("contact")));
                Activity ac = (Activity) restaurents.ac;
                ac.startActivity(callIntent);
                }
            });
        holder.ivmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Myapp.showSucc("opening location");
            }
        });

        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(Myapp.con,rest_content.class);
                i.putExtra("key",movie.key);
                i.putExtra("userid",data.get("userid")+"");
                Activity ac=(Activity) restaurents.ac;
                ac.startActivity(i);


            }
        });



}

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
