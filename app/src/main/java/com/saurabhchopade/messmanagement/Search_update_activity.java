package com.saurabhchopade.messmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Search_update_activity extends AppCompatActivity {
Button btn_update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_update_activity);
      btn_update=findViewById(R.id.btn_Update);


        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Search_update_activity.this,Main4Activity.class);
                startActivity(intent);

            }
        });

    }
}
