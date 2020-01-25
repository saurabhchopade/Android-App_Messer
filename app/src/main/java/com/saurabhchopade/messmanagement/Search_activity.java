package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Search_activity extends AppCompatActivity {
    Button btn_view,btn_update,btn_updatedetails;
    Dbhelper db;
    EditText mem_name,mob_num,st_date,end_date,paid_amt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_activity);
        db=new Dbhelper(this);
        btn_view=findViewById(R.id.btn_view);
       // btn_update=findViewById(R.id.btn_update);

        btn_updatedetails=findViewById(R.id.btn_updatedetails);
        mem_name=findViewById(R.id.mem_name);
        mob_num=findViewById(R.id.mob_num);
        st_date=findViewById(R.id.st_date);
        end_date=findViewById(R.id.end_date);
        paid_amt=findViewById(R.id.paid_amt);

        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name=mem_name.getText().toString();

                Cursor cursor=db.view_user(name);
                Boolean checkname=db.chkname(name);
                if(checkname==false) {
                    if (cursor.moveToFirst()) {
                        mem_name.setText(cursor.getString(0));
                        mob_num.setText(cursor.getString(1));
                        st_date.setText(cursor.getString(2));
                        end_date.setText(cursor.getString(3));
                        paid_amt.setText(cursor.getString(4));
                    }
                }else
                {
                    Toast.makeText(getApplicationContext(), "Member is not present", Toast.LENGTH_SHORT).show();

                }
            }
        });
        btn_updatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=mem_name.getText().toString();
                String mobile=mob_num.getText().toString();
                String date=st_date.getText().toString();
                String ndate=end_date.getText().toString();
                String amt=paid_amt.getText().toString();


                if (name.equals("") || mobile.equals("") || date.equals("") || ndate.equals("") ||
                        amt.equals(""))

                {
                    Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

                }
                else
                {
                   Integer a=db.updatedata(name,mobile,date,ndate,amt);
                    if(a>0) {

                        Toast.makeText(getApplicationContext(), "Member Successfully Updated", Toast.LENGTH_LONG).show();
                        Intent intent=new Intent(Search_activity.this,Main4Activity.class);
                        startActivity(intent);
                    }else
                    {
                        Toast.makeText(getApplicationContext(), "Member details not Updated", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

     /*   btn_updatedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean isupdate=db.updatedata(mem_name.getText(),mob_num.getText(),st_date,getText(),end_date.getText(),paid_amt.getText());

        });
        */
    }
}
