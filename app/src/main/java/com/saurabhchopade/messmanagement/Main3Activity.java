package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    Dbhelper db;

EditText et_username,et_password,et_confirmpassword;
Button btn_register;
TextView tv_alreadymember,tv_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        db=new Dbhelper(this);
        et_username=findViewById(R.id.et_username);
        et_password=findViewById(R.id.et_password);
        et_confirmpassword=findViewById(R.id.et_confirmpassword);

        btn_register=findViewById(R.id.btn_register);
        tv_alreadymember=findViewById(R.id.tv_alreadymember);
        tv_login=findViewById(R.id.tv_login);

     //alredy member textview

        //just go to login activity again
     tv_alreadymember.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
    startActivity(intent);
         }
     });

     //it is a text view just go to login activity

        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
startActivity(intent);
            }
        });


        // register and data store in database
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  et1=et_username.getText().toString();
                String  et2=et_password.getText().toString();
                String  et3=et_confirmpassword.getText().toString();

//if field is null then irt show login field toast
   if(et1.equals("")|| et2.equals("")||et3.equals(""))
   {
       Toast.makeText(getApplicationContext(),"Login failed",Toast.LENGTH_SHORT).show();

   }
   else
   {

       //it check  through checkmail function return in dbhelper class to email is alredy register or not

       if(et2.equals(et3))
       {
           //checkmail function to written the check the email is alredy registered or not
           Boolean chkmail=db.chkmail(et1);

           if (chkmail==true)
           {      //if email is already registed then it insert into the table insert
               Boolean insertq=db.insert(et1,et2);
               if (insertq==true)
               {

                   Toast.makeText(getApplicationContext(),"registred",Toast.LENGTH_SHORT).show();
                   Intent intent=new Intent(Main3Activity.this,Main2Activity.class);
                   startActivity(intent);


               }
           }
           else
           {
               Toast.makeText(getApplicationContext(),"Email already in use",Toast.LENGTH_SHORT).show();

           }
       }
       else {
           Toast.makeText(getApplicationContext(), "Enter correct password", Toast.LENGTH_SHORT).show();
       }
   }

            }
        });





    }
}
