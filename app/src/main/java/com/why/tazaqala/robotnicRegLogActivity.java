package com.why.tazaqala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class robotnicRegLogActivity extends AppCompatActivity {

    TextView robotnicStatus,question;
    Button signInBtm,signUpBtm;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robotnic_reg_log);

        robotnicStatus = (TextView) findViewById(R.id.statusRobotnic);
        question = (TextView) findViewById(R.id.accountCreate);
        signInBtm = (Button) findViewById(R.id.signInRobotnic);
        signUpBtm = (Button) findViewById(R.id.signUpRobotnic);
        emailET = (EditText) findViewById(R.id.robotnicEmail);
        passwordET = (EditText) findViewById(R.id.robotnicPassword);
        signUpBtm.setVisibility(View.INVISIBLE);
        signUpBtm.setEnabled(false);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInBtm.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtm.setVisibility(View.VISIBLE);
                signUpBtm.setEnabled(true);
                robotnicStatus.setText("Регистрация для сборщика");
            }
        });

        signUpBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterRobotnic(email, password);
            }
        });

        signInBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                SignInRobotnic(email, password);
            }
        });
    }

    private void SignInRobotnic(String email, String password) {
        loadingBar.setTitle("Вход сборщика");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(robotnicRegLogActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                    Intent robotnicIntent = new Intent(robotnicRegLogActivity.this, RobotnicMapActivity.class);
                    startActivity(robotnicIntent);
                }
                else{
                    Toast.makeText(robotnicRegLogActivity.this, "Ошибка, попробуйте снова", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterRobotnic(String email, String password) {
        loadingBar.setTitle("Регистрация сборщика");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(robotnicRegLogActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                    Intent robotnicIntent = new Intent(robotnicRegLogActivity.this, RobotnicMapActivity.class);
                    startActivity(robotnicIntent);
                }
                else{
                    Toast.makeText(robotnicRegLogActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}