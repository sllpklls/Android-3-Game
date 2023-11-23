package com.example.btl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.btl.R;
import com.example.btl.retrofit.RetrofitClient;
import com.example.btl.retrofit.callApi;
import com.example.btl.utils.Utils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class DangKiActivity extends AppCompatActivity {
    EditText email, pass, repass, mobile,username;
    AppCompatButton button;
    callApi api;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        initView();
        initControll();
    }
    private void initControll() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dangKi();
            }
        });
    }
    private void dangKi(){
        String str_user = username.getText().toString().trim();
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_repass = repass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();

        if (TextUtils.isEmpty(str_email)){
            Toast.makeText(getApplicationContext(), "ban chua nhap email", Toast.LENGTH_SHORT).show();
        }else  if (TextUtils.isEmpty(str_pass)){
            Toast.makeText(getApplicationContext(), "ban chua nhap pass", Toast.LENGTH_SHORT).show();
        }else  if (TextUtils.isEmpty(str_repass)){
            Toast.makeText(getApplicationContext(), "ban chua nhap repass", Toast.LENGTH_SHORT).show();
        }else  if (TextUtils.isEmpty(str_mobile)){
            Toast.makeText(getApplicationContext(), "ban chua nhap mobile", Toast.LENGTH_SHORT).show();
        }else  if (TextUtils.isEmpty(str_user)){
            Toast.makeText(getApplicationContext(), "ban chua nhap username", Toast.LENGTH_SHORT).show();
        }
        else {
            if (str_pass.equals(str_repass)){
                //post data
                compositeDisposable.add(api.dangKi(str_email,str_pass,str_user,str_mobile)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                adminModel -> {
                                    if (adminModel.isSuccess()){
                                        Utils.admin_current.setEmail(str_email);
                                        Utils.admin_current.setPass(str_pass);
                                        Intent intent= new Intent(getApplicationContext(), DangNhapActivity.class);
                                        startActivity(intent);
                                        finish();
//                                Toast.makeText(getApplicationContext(), "thanh cong", Toast.LENGTH_SHORT).show();

                                    }else {
                                        Toast.makeText(getApplicationContext(), adminModel.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }, throwable -> {
                                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                        ));
            }else {
                Toast.makeText(getApplicationContext(), "pass k khop", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void initView() {
       api = RetrofitClient.getInstance(Utils.BASE_URL).create(callApi.class);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.pass);
        repass = findViewById(R.id.repass);
        button = findViewById(R.id.btndangky);
        mobile = findViewById(R.id.mobile);
        username= findViewById(R.id.username);
    }
    @Override
    protected void onDestroy(){
        compositeDisposable.clear();
        super.onDestroy();
    }
}