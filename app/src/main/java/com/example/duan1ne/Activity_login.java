package com.example.duan1ne;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_login extends AppCompatActivity {
    private EditText edtemail,edtpassword;
    private Button btnlogin,btnregister;
    private TextView txtreset,txtCheckRsl;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        mAuth=FirebaseAuth.getInstance();
        edtemail = findViewById(R.id.edtgmail);
        edtpassword = findViewById(R.id.edtpassword);
        btnlogin = findViewById(R.id.btnregister);
        btnregister = findViewById(R.id.btnlogin);
        txtreset = findViewById(R.id.txtresetpass);
        txtCheckRsl = findViewById(R.id.txtCheckRsl);
        txtreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Activity_login.this,Reset_password.class);
                startActivity(i);
            }
        });

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Login();
            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
    }
    private void Login(){
        Intent intent = new Intent(Activity_login.this,Activity_signin.class);
        startActivity(intent);
    }
    private void Register(){
        String email,password;
        email = edtemail.getText().toString();
        password = edtpassword.getText().toString();
        if(TextUtils.isEmpty(email)){
            txtCheckRsl.setText("* Vui lòng nhập email *");
            txtCheckRsl.setVisibility(View.VISIBLE);
            return;
        }
        if(TextUtils.isEmpty(password)){
            txtCheckRsl.setText("* Vui lòng nhập mật khẩu *");
            txtCheckRsl.setVisibility(View.VISIBLE);
            return;
        }
        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(Activity_login.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(Activity_login.this,MainActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    txtCheckRsl.setText("* Đăng nhập thất bại *");
                    txtCheckRsl.setVisibility(View.VISIBLE);
                }
            }
        });

    }
}