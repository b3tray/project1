package com.why.tazaqala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button ProfileSettings, PostSettings, SwapSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread()
        {
            @Override
            public void run() {
                super.run();
                {
                    try
                    {
                        sleep(2000);
                    }
                    catch(Exception e)
                    {
                        e.printStackTrace();
                    }
                    finally {
                        Intent welcomeIntent = new Intent(MainActivity.this, welcomeActivity.class);
                        startActivity(welcomeIntent);
                    }
                }

            }
        };
        thread.start();


    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}