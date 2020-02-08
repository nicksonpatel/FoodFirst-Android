package com.example.nicks.myfoodfirst.adpter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nicks.foodfirst.Myapp;
import com.example.nicks.foodfirst.R;

import java.util.List;
import java.util.Map;


/**
 * Created by Meet on 16-10-2017.
 */

public class SubjectAdapter2 extends RecyclerView.Adapter<SubjectAdapter2.MyViewHolder> {

    private List<Subject> moviesList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,time,status,code,person;
        public ImageView iv;

        public MyViewHolder(View view) {
            super(view);
            time=(TextView)view.findViewById(R.id.time);
            title = (TextView) view.findViewById(R.id.title);
            code = (TextView) view.findViewById(R.id.code);
              iv = (ImageView) view.findViewById(R.id.img);
            status=(TextView)view.findViewById(R.id.status);
            person=(TextView)view.findViewById(R.id.person);

        }
    }


    public SubjectAdapter2(List<Subject> moviesList) {
        this.moviesList = moviesList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_request, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Subject movie = moviesList.get(position);
        final Map<String, Object> data = movie.data;

        holder.status.setText(data.get("status")+"");
        holder.time.setText(data.get("time")+"");
        holder.person.setText("booking for "+data.get("nop")+" person");
        holder.title.setText(data.get("rest")+"");
        if(data.get("status").toString().equals("pending"))
        {
            holder.iv.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.pending));
            holder.code.setVisibility(View.GONE);
        }
        else if(data.get("status").toString().equals("accept"))
        {
            holder.iv.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.accept));
            holder.code.setVisibility(View.VISIBLE);
            holder.code.setText(data.get("code")+"");
        }else {
            holder.code.setVisibility(View.GONE);
            holder.iv.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.denide));

        }



}

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
