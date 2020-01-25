package com.saurabhchopade.messmanagement;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.opengl.Visibility;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;


public class Add_activity extends AppCompatActivity {




    Button btn_addmember;
     EditText et_membername,et_mobilenumber,et_startdate,et_enddate,et_paidamount;
    Dbhelper db;
DatePickerDialog.OnDateSetListener mDateSetListener;
    DatePickerDialog.OnDateSetListener mDateSetListener2;
TextView tv_dateformat,tv_dateformat2;






    @Override
     protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_activity);
        db = new Dbhelper(this);

        et_membername = (EditText) findViewById(R.id.et_membername);
        et_mobilenumber = (EditText) findViewById(R.id.et_mobilenumber);

        et_startdate = (EditText) findViewById(R.id.et_startdate);

        et_enddate = (EditText) findViewById(R.id.et_startdate);
        et_paidamount = (EditText) findViewById(R.id.et_Paidamount);
        btn_addmember = (Button) findViewById(R.id.btn_addmember);
        tv_dateformat = (TextView) findViewById(R.id.tv_dateformat);
        tv_dateformat2 = (TextView) findViewById(R.id.tv_dateformat2);


        et_startdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(Add_activity.this, android.R.style.Theme_Material_Dialog_MinWidth, mDateSetListener, year, month, day);


                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                dialog.show();



            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month = month + 1;

                Log.d("tag", "ondateset:dd/mm/yyy:" + dayOfMonth + "/" + month + "/" + year);
                String date = dayOfMonth + "/" +month + "/" + year;

                et_startdate.setText(date);

            }
        };







    btn_addmember.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        String membername2 =et_membername.getText().toString();
        String mobilenumber2 = et_mobilenumber.getText().toString();
        String startdate2 = et_startdate.getText().toString();
        String enddate2 = et_enddate.getText().toString();
        String paidamount2 = et_paidamount.getText().toString();


       if (membername2.equals("") || mobilenumber2.equals("") || startdate2.equals("") || enddate2.equals("") ||
                paidamount2.equals(""))

        {
            Toast.makeText(getApplicationContext(), "Enter details", Toast.LENGTH_SHORT).show();

         }

         else
            {

                Boolean checkname=db.chkname(membername2);
                if (checkname==true)
                {


                    boolean ipi=db.insert1(membername2,mobilenumber2,startdate2,enddate2,paidamount2);



                    if(ipi==true)
                    {
                        Toast.makeText(getApplicationContext(), "Successfully member added", Toast.LENGTH_LONG).show();

                        Intent intent = new Intent(Add_activity.this, Main4Activity.class);
                        startActivity(intent);

                    }
                    else {
                        Toast.makeText(getApplicationContext(), "member is not added", Toast.LENGTH_SHORT).show();

                    }

                }

                else
                {
                    Toast.makeText(getApplicationContext(), "member is not added", Toast.LENGTH_SHORT).show();

               }



        }
        }
});

    }
}
