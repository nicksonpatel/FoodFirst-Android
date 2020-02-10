package com.example.nicks.myfoodfirst;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.nicks.myfoodfirst.adpter.Subject1;
import com.example.nicks.myfoodfirst.adpter.SubjectAdapter1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class rest_content extends AppCompatActivity {

    RecyclerView recy;
    static SubjectAdapter1 adapter;
    static List<Subject1> list;
    SwitchDateTimeDialogFragment dateTimeFragment;
    private static final String TAG = "Sample";

    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    private static final String STATE_TEXTVIEW = "STATE_TEXTVIEW";
    Button book;
    TextView time;
    String uid;
    ProgressDialog pd;
    Map<String, Object> data;
    public TextView area, title, price, name, status;
    public ImageView iv, ivcall, ivmap, vegnonveg;
    List<String> menu,noplist;
    Map<String,Object> allmenu;
    Spinner spin;
    Spinner nop;
    ArrayAdapter<String> adp,adpnos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_content);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pd = new ProgressDialog(rest_content.this);
        pd.setMessage("please wait");
        pd.setCancelable(false);
        nop=(Spinner)findViewById(R.id.nop);
        time=(TextView)findViewById(R.id.time);
        area=(TextView)findViewById(R.id.manjalpur);
        spin=(Spinner)findViewById(R.id.spn);
        book=(Button)findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!time.getText().toString().equals("select time"))
                {
                    int no=Integer.parseInt(nop.getSelectedItem().toString());
                    int amt=Integer.parseInt(Myapp.userdata.get("balance")+"");

                    if((no*10)>amt)
                    {
                        Myapp.showError("No enough balance");
                    }
                    else {
                        setBooking(amt,no*10);
                    }
                }
                else
                {
                    Myapp.showError("please select time");
                }
            }
        });

        menu=new ArrayList<>();
        noplist=new ArrayList<>();
        adpnos=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,noplist);
        nop.setAdapter(adpnos);
        adp=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_dropdown_item,menu);
        spin.setAdapter(adp);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here

               // Myapp.showSucc("show Data  of "+menu.get(position));
setvalues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });

        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        ivcall = (ImageView) findViewById(R.id.phone);
        ivmap = (ImageView) findViewById(R.id.location);
        iv = (ImageView) findViewById(R.id.img_piz);

        vegnonveg = (ImageView) findViewById(R.id.imageView3);
        status = (TextView) findViewById(R.id.status);
        Intent i = getIntent();
        uid = i.getStringExtra("userid");

        recy=(RecyclerView)findViewById(R.id.recy);
        list=new ArrayList<>();
        adapter=new SubjectAdapter1(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(Myapp.con);
        recy.setLayoutManager(mLayoutManager);
        recy.setItemAnimator(new DefaultItemAnimator());
        recy.setAdapter(adapter);



        dateTimeFragment = (SwitchDateTimeDialogFragment) getSupportFragmentManager().findFragmentByTag(TAG_DATETIME_FRAGMENT);
        if(dateTimeFragment == null) {
            dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                    getString(R.string.label_datetime_dialog),
                    getString(android.R.string.ok),
                    getString(android.R.string.cancel)
                    // Optional
            );
        }
        getAlldata();
        settime();
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dateTimeFragment.startAtCalendarView();
                dateTimeFragment.setDefaultDateTime(Calendar.getInstance().getTime());
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);

            }
        });



    }

    void settime()
    {
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("d MMM yyyy HH:mm a", Locale.getDefault());
        // Assign unmodifiable values
        dateTimeFragment.set24HoursMode(false);
        dateTimeFragment.setMinimumDateTime(Calendar.getInstance().getTime());
        GregorianCalendar gc = new GregorianCalendar();
        gc.add(Calendar.DATE, 7);
        dateTimeFragment.setMaximumDateTime(gc.getTime());

        // Define new day and month format
        try {
            dateTimeFragment.setSimpleDateMonthAndDayFormat(new SimpleDateFormat("MMMM dd", Locale.getDefault()));
        } catch (SwitchDateTimeDialogFragment.SimpleDateMonthAndDayFormatException e) {

        }

        // Set listener for date
        // Or use dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonClickListener() {
        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {


            @Override
            public void onPositiveButtonClick(Date date) {
                time.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {
                // Do nothing
            }

            @Override
            public void onNeutralButtonClick(Date date) {
                // Optional if neutral button does'nt exists

            }
        });
    }

    void setvalues()
    {
        list.clear();
        adapter.notifyDataSetChanged();
        try {
            Map<String, Object> oo = (Map<String, Object>)allmenu.get(spin.getSelectedItem()+"") ;
            List<String> keys = new ArrayList<>(oo.keySet());

            for (int i = 0; i < keys.size(); i++) {
                Map<String, String> data = (Map<String, String>) oo.get(keys.get(i));
                list.add(new Subject1(keys.get(i), data.get("name"), data.get("price")));
            }
            adapter.notifyDataSetChanged();
        }
        catch(Exception e)
        {
            Myapp.showError("No data found");
        }



    }


    void getAlldata() {
        pd.show();

        Myapp.ref.child("rest").child(uid).child("res").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pd.dismiss();
                Myapp.ref.child("rest").child(uid).removeEventListener(this);
                if (dataSnapshot.getValue() == null) {
                    Myapp.showError("Something went wrong");
                } else {
                    data = (Map<String, Object>) dataSnapshot.getValue();

                    if(data.containsKey("menu"))
                    {
                        allmenu=(Map<String, Object>)data.get("menu");
                        menu.addAll(allmenu.keySet());
                        adp.notifyDataSetChanged();
                    }

                    try{
                        for(int j=1;j<=Integer.parseInt(data.get("capacity").toString());j++)
                        {
                            noplist.add(""+j);
                        }
                        adpnos.notifyDataSetChanged();
                    }
                    catch(Exception e)
                    {

                    }


                    setValuess();
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                pd.dismiss();
                Myapp.showError("No data found");
            }
        });

    }



    void setValuess() {

        price.setText("APPROX :" + data.get("price") + "INR PR  COUPLE");
        area.setText(data.get("area") + "");
        name.setText(data.get("title") + "");
        if (data.get("status").equals("off")) {
            status.setText("Closed");
        } else {
            status.setText("Open");
        }
        Glide.with(iv.getContext()).load(data.get("url") + "")
                .override(100, 100)
                .fitCenter()
                .into(iv);


        if (data.get("type").toString().equals("Non Vegetarian")) {
            vegnonveg.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.icon_nonveg));
        } else {

            vegnonveg.setImageDrawable(Myapp.con.getResources().getDrawable(R.drawable.icon_veg));
        }


        ivmap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(data.containsKey("location"))
                {

                }
                else
                {
                    Myapp.showError("No location available");
                }


            }
        });
        ivcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + data.get("contact")));

                if (ActivityCompat.checkSelfPermission(rest_content.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                   Myapp.showError("Call Permission");
                    return;
                }
                startActivity(callIntent);
        }
    });
    ivmap.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Myapp.showSucc("opening location");
        }
    });

}

void setBooking(final int ori, final int amt)
{
    pd.show();
    final String key=Myapp.ref.child("rest").child(uid).child("request").push().getKey();
    final Map<String,String> rdata=new HashMap<>();
    rdata.put("username",Myapp.myname);
    rdata.put("userid",Myapp.mynumber);
    rdata.put("aid",uid);
    rdata.put("key",key);
    rdata.put("rest",data.get("title")+"");
    rdata.put("nop",nop.getSelectedItem()+"");
    rdata.put("time",time.getText().toString());
    rdata.put("status","pending");
    Myapp.ref.child("rest").child(uid).child("request").child(key).setValue(rdata).addOnCompleteListener(new OnCompleteListener<Void>() {
        @Override
        public void onComplete(@NonNull Task<Void> task) {

            Myapp.ref.child("users").child(Myapp.mynumber).child("request").child(key).setValue(rdata).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
               pd.dismiss();
                    Myapp.ref.child("users").child(Myapp.mynumber).child("balance").setValue((ori-amt)+"");
               Myapp.showSucc("Successfully Send Request\n"+amt+" deduct from ewallet");
               Myapp.sendNotificationTo(uid,"Booking Request on "+time.getText().toString(),Myapp.myname+"("+Myapp.mynumber+")");
               rest_content.super.onBackPressed();
                }
            });


        }
    }).addOnFailureListener(new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            pd.dismiss();
       Myapp.showError("try again later");
        }
    });
}

}