package com.example.joaquinchou.bikesguide;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText input_name = null;
    private  EditText input_password = null;
    private Button _signupButton = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        input_name = findViewById(R.id.input_name);
        input_password = findViewById(R.id.input_password);
        _signupButton = findViewById(R.id.btn_signup);
        _signupButton.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {
        String account = input_name.getText().toString();
        String password = input_password.getText().toString();
        Log.v("AAAAAAA","account:"+account+"\npassword:"+password);
            Toast.makeText(SignUpActivity.this,"注册成功！",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignUpActivity.this,ShowMapActivity.class);
            startActivity(intent);
    }
}
