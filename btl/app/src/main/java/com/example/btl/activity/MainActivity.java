package com.example.btl.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.btl.R;

import soup.neumorphism.NeumorphCardView;

public class MainActivity extends AppCompatActivity {
NeumorphCardView snake, g2048, terit, logout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initControl();
    }

    private void initControl() {
        snake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SnakeActivity.class);
                startActivity(intent);
            }
        });
        g2048.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), g2048Activity.class);
                startActivity(intent);
            }
        });
        terit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TeritActivity.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),DangNhapActivity.class);
                startActivity(intent);
            }
        });
    }


    private void initView() {
       snake = findViewById(R.id.neu_snake);
       g2048 = findViewById(R.id.neu_2048);
       terit = findViewById(R.id.neu_terit);
        logout = findViewById(R.id.neu_out);
    }
}