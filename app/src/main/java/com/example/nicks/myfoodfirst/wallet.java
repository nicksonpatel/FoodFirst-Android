package com.example.nicks.myfoodfirst;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.rengwuxian.materialedittext.MaterialEditText;

public class wallet extends AppCompatActivity {



    Button btn;
    MaterialEditText etamt;
    TextView balance,trans;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        back=(ImageView)findViewById(R.id.back);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),myhome.class);
                startActivity(i);
            }
        });
        btn=(Button)findViewById(R.id.btn);
        balance=(TextView)findViewById(R.id.balance);
        etamt=(MaterialEditText)findViewById(R.id.etamt);

        balance.setText(Myapp.userdata.get("balance")+"");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    int netamt= Integer.parseInt(etamt.getText().toString());
                    int oldamt=Integer.parseInt(Myapp.userdata.get("balance")+"");
                    Myapp.ref.child("users").child(Myapp.mynumber).child("balance").setValue(""+(netamt+oldamt)).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Myapp.showSucc("successfully added ");
                            balance.setText(Myapp.userdata.get("balance")+"");
                            etamt.setText("");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Myapp.showError("try again");
                        }
                    });
                }
                catch(Exception e)
                {
                    Myapp.showError("try again");
                }
            }
        });
    }

}
