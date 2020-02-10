package com.example.nicks.myfoodfirst;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.nicks.myfoodfirst.adpter.Subject;
import com.example.nicks.myfoodfirst.adpter.SubjectAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class restaurents extends AppCompatActivity {
    RecyclerView recy;
    SubjectAdapter adapter;
    List<Subject> list,alllist;
    public static Context ac;
    TextView tit;
    Map<String,Object> alldata;
    List<String> allkeys;
    List<String> sug;
    RelativeLayout searchpor;

    AutoCompleteTextView name;
    Button btn;
    ArrayAdapter<String> sugadapter;
    String ttype;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurents);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ac=this;
        getWindow().setSoftInputMode(WindowManager.
                LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        searchpor=(RelativeLayout)findViewById(R.id.rel1);
        tit=(TextView)findViewById(R.id.tv_wallet);
        btn=(Button)findViewById(R.id.btn);
        name=(AutoCompleteTextView)findViewById(R.id.name1);
        sug=new ArrayList<>();
        alllist=new ArrayList<>();
        Intent i=getIntent();
        ttype=i.getStringExtra("type");
        sugadapter= new ArrayAdapter<String>(this,
               android.R.layout.simple_dropdown_item_1line, sug);

         name.setAdapter(sugadapter);

        recy=(RecyclerView)findViewById(R.id.recy);
        list=new ArrayList<>();
        adapter=new SubjectAdapter(list);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals(""))
                {
                    name.setError("Enter name");
                }
                else
                {
                    if(allkeys==null)
                    {
                        Myapp.showError("No data found");
                    }
                    else
                    {
                        setSearch(name.getText().toString());


                    }
                }
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Myapp.con);
        recy.setLayoutManager(mLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.setAdapter(adapter);

        if(ttype.equals("all"))
        {
            tit.setText("R E S T A U R E N T S");
            getData();
            searchpor.setVisibility(View.VISIBLE);
        }
        else
        {
            tit.setText(ttype);
            getData(ttype);
            searchpor.setVisibility(View.GONE);
        }




    }

    void setSearch(String s)
    {
        if(sug.contains(s))
        {
            list.clear();
            adapter.notifyDataSetChanged();

            for(int i=0;i<alldata.size();i++)
            {

                Map<String,Object> oo=(Map<String, Object>)alldata.get(allkeys.get(i));
                if(oo.get("title").toString().equals(s))
                {
                    list.add(new Subject(allkeys.get(i),oo));
                    adapter.notifyDataSetChanged();
                }



            }

            Myapp.showSucc(list.size()+" search found");
        }
        else
        {
            Myapp.showError("No data found");
        }

    }

    void getData()
    {
        list.clear();
        Myapp.ref.child("allres").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Myapp.ref.child("allres").removeEventListener(this);
                if(dataSnapshot.getValue()==null)
                {
                    Myapp.showError("No data found");

                }
                else
                {
                    alldata=(Map<String, Object>)dataSnapshot.getValue();
                    allkeys=new ArrayList<>(alldata.keySet());

                    for(int i=0;i<allkeys.size();i++)
                    {
                        Map<String,Object> temp=(Map<String, Object>) alldata.get(allkeys.get(i));
                        sug.add(temp.get("title")+"");
                        list.add(new Subject(allkeys.get(i),temp));
                        alllist.add(new Subject(allkeys.get(i),temp));
                    }
                    adapter.notifyDataSetChanged();
                   sugadapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    void getData(String st)
    {
        list.clear();
        Myapp.ref.child("res_food").child(st).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Myapp.ref.child("allres").removeEventListener(this);
                if(dataSnapshot.getValue()==null)
                {
                    Myapp.showError("No data found");

                }
                else
                {
                    alldata=(Map<String, Object>)dataSnapshot.getValue();
                    allkeys=new ArrayList<>(alldata.keySet());

                    for(int i=0;i<allkeys.size();i++)
                    {
                        Map<String,Object> temp=(Map<String, Object>) alldata.get(allkeys.get(i));
                        sug.add(temp.get("title")+"");
                        list.add(new Subject(allkeys.get(i),temp));
                        alllist.add(new Subject(allkeys.get(i),temp));
                    }
                    adapter.notifyDataSetChanged();
                    sugadapter.notifyDataSetChanged();
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


}
