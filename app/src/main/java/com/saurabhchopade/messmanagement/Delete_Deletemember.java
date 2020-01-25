package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class Delete_Deletemember extends AppCompatActivity {
EditText et_membername;
Button btn_deletemember;
Dbhelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete__deletemember);
        db=new Dbhelper(this);

        et_membername=findViewById(R.id.et_membername);
        btn_deletemember=findViewById(R.id.btn_Deletemember);





        btn_deletemember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String d=et_membername.getText().toString();


                if(d.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Enter name",Toast.LENGTH_SHORT).show();

                }


else {
                    Integer deleterows = db.deletedata(d);
                    if (deleterows > 0) {
                        Toast.makeText(getApplicationContext(), "Member deleted", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Delete_Deletemember.this, Main4Activity.class);
                        startActivity(intent);


                    } else {

                        Toast.makeText(getApplicationContext(), "Member not exist", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });






    }
}
