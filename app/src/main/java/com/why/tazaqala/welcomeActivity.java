package com.why.tazaqala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class welcomeActivity extends AppCompatActivity {

    Button robotnicBtm, clientBtm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        robotnicBtm = (Button)findViewById(R.id.robotnicBtm);
        clientBtm = (Button)findViewById(R.id.clientBtm);

        robotnicBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent robotnicIntent = new Intent(welcomeActivity.this,robotnicRegLogActivity.class);
                startActivity(robotnicIntent);

            }
        });
        clientBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent clientIntent = new Intent(welcomeActivity.this,clientRegLogActivity.class);
                startActivity(clientIntent);
            }
        });

    }
}