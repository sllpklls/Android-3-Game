package com.example.btl.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.example.btl.R;
import com.example.btl.g2048.Adapter_shape;
import com.example.btl.g2048.DataGame;

public class g2048Activity extends AppCompatActivity {
    private GridView gdvGame;
    private Adapter_shape adapter;
    private View.OnTouchListener listener;
    private float X, Y;
    TextView Max, Points;
    Button Bt_Clear, Bt_Undo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_g2048);
        mapping();
        init();
        setData();
    }

    private void mapping() {
        gdvGame = (GridView) findViewById(R.id.area);
    }

    private void init() {
        DataGame.getDataGame().intt(g2048Activity.this);
        adapter = new Adapter_shape(g2048Activity.this, 0, DataGame.getDataGame().getArr());
        Max = (TextView) findViewById(R.id.max);
        Points = (TextView) findViewById(R.id.points);
        Bt_Clear = (Button) findViewById(R.id.clear);
        Bt_Undo = (Button) findViewById(R.id.undo);
        Max.setText(String.valueOf(DataGame.getDataGame().getMax()));
        Points.setText(String.valueOf(DataGame.getDataGame().getPoints()));

        Bt_Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(g2048Activity.this)
                        .setTitle("Attention")
                        .setMessage("Do you want to clear this game?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                mapping();
                                init();
                                setData();
                            }
                        })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        Bt_Undo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataGame.getDataGame().undoData();

                mapping();
                setData();
            }
        });


        listener = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Max.setText(String.valueOf(DataGame.getDataGame().getMax()));
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        X = motionEvent.getX();
                        Y = motionEvent.getY();
                        break;
                    }
                    case MotionEvent.ACTION_UP: {
                        if (Math.abs(motionEvent.getX() - X) > Math.abs(motionEvent.getY() - Y)) {
                            if (motionEvent.getX() > X) {
                                if ((motionEvent.getX() - X) > 150) {
                                    DataGame.getDataGame().toRight(1);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                if ((motionEvent.getX() - X) < -150) {
                                    DataGame.getDataGame().toLeft(1);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            if (motionEvent.getY() > Y) {
                                if ((motionEvent.getY() - Y) > 180) {
                                    DataGame.getDataGame().toUp(1);
                                    adapter.notifyDataSetChanged();
                                }
                            } else {
                                if ((motionEvent.getY() - Y) < -180) {
                                    DataGame.getDataGame().toDown(1);
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }
                        Max.setText(String.valueOf(DataGame.getDataGame().getMax()));
                        Points.setText(String.valueOf(DataGame.getDataGame().getPoints()));
                        if (DataGame.getDataGame().checkLose() == true) {
                            new AlertDialog.Builder(g2048Activity.this)
                                    .setTitle("You lose!")
                                    .setMessage("Do you want to play again?")
                                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int which) {
                                            DataGame.getDataGame().intt(g2048Activity.this);
                                        }
                                    })
                                    .setNegativeButton(android.R.string.no, null)
                                    .setIcon(android.R.drawable.ic_dialog_alert)
                                    .show();
                        }
                    }
                }
                return true;
            }
        };

    }

    private void setData() {
        gdvGame.setAdapter(adapter);
        gdvGame.setOnTouchListener(listener);
    }

}
