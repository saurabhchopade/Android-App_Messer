package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {

EditText et_email,et_pass;
Button btn_login;
Dbhelper db;




TextView tv_noaccountyet,tv_createone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        db=new Dbhelper(this);


        et_email=findViewById(R.id.et_email);
        et_pass=findViewById(R.id.et_pass);
        btn_login=findViewById(R.id.btn_login);
        tv_noaccountyet=findViewById(R.id.tv_noaccountyet);
        tv_createone=findViewById(R.id.tv_createone);




        //just go in register activity only for user interface

tv_noaccountyet.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);

    }
});

//just go in only register page
tv_createone.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent intent=new Intent(Main2Activity.this,Main3Activity.class);
        startActivity(intent);
    }
});



        // this is the login buttton and ists action on click in it string value are collect and checked
        //into the database is it correct or not if correct then we generate a toast below the successfully login

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=et_email.getText().toString();
                String password=et_pass.getText().toString();


//if the value of field is vacant then it shows the this toast login fiels
                if(email.equals("")|| password.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();

                }
                //ckecking email id and password in database to login
                 else {
                    //check amil function is called and retuns value true or falues and we check the other conditions
                    Boolean chkemailpass = db.emailpassword(email, password);
                    if (chkemailpass == true) {
                        //if the record is found in the database

                        Toast.makeText(getApplicationContext(), "successfully Login", Toast.LENGTH_SHORT).show();

                 //if login succesfuuly  then we go to home ativity and whew add meber delete member is written in main activity4 is written
                        Intent intent = new Intent(Main2Activity.this, Main4Activity.class);
                        startActivity(intent);
                        //edited finished then back activity is not opend again
                        finish();
                    } else {

                        //if password and not correct in databse then show this toast password and id is not corrected
                        //if id and password is not  is found in database
                        Toast.makeText(getApplicationContext(), "Incorrect id or password", Toast.LENGTH_SHORT).show();


                    }
                }
            }
        });




    }
}
