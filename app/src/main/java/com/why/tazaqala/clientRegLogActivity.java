package com.why.tazaqala;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class clientRegLogActivity extends AppCompatActivity {

    TextView clientStatus,question;
    Button signInBtm,signUpBtm;
    EditText emailET, passwordET;

    FirebaseAuth mAuth;
    ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_reg_log);

        clientStatus = (TextView) findViewById(R.id.statusCustomer);
        question = (TextView) findViewById(R.id.accountCreateCustomer);
        signInBtm = (Button) findViewById(R.id.signInCustomer);
        signUpBtm = (Button) findViewById(R.id.signUpCustomer);
        emailET = (EditText) findViewById(R.id.customerEmail);
        passwordET = (EditText) findViewById(R.id.customerPassword);

        mAuth = FirebaseAuth.getInstance();
        loadingBar = new ProgressDialog(this);

        signUpBtm.setVisibility(View.INVISIBLE);
        signUpBtm.setEnabled(false);

        question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInBtm.setVisibility(View.INVISIBLE);
                question.setVisibility(View.INVISIBLE);
                signUpBtm.setVisibility(View.VISIBLE);
                signUpBtm.setEnabled(true);
                clientStatus.setText("Регистрация для пользователя");
            }
        });

        signUpBtm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();

                RegisterClient(email, password);
            }
        });

    signInBtm.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String email = emailET.getText().toString();
            String password = passwordET.getText().toString();

            SignInClient(email, password);
        }
    });
}

    private void SignInClient(String email, String password) {
        loadingBar.setTitle("Вход пользователя");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(clientRegLogActivity.this, "Успешный вход!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
                else{
                    Toast.makeText(clientRegLogActivity.this, "Ошибка, попробуйте снова", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }

    private void RegisterClient(String email, String password) {
        loadingBar.setTitle("Регистрация клиента");
        loadingBar.setMessage("Пожалуйста, дождитесь загрузки");
        loadingBar.show();

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(clientRegLogActivity.this, "Регистрация прошла успешно!", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();

                }
                else{
                    Toast.makeText(clientRegLogActivity.this, "Ошибка", Toast.LENGTH_SHORT).show();
                    loadingBar.dismiss();
                }
            }
        });
    }
}