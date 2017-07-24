package com.example.android.mathgame;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Drill3Activity extends AppCompatActivity {
    AlertDialog dialog;
    Context context=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill3);
        setAlertDialog();
    }
    boolean isEnd(){
        int lives=getIntent().getExtras().getInt("lives");
        return lives>=3;
    }
    void setAlertDialog(){
        dialog=new AlertDialog.Builder(this).create();
        dialog.setTitle("Question 3");
        dialog.setMessage("1500*13=18500?");
        dialog.setButton(Dialog.BUTTON_POSITIVE, "YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(!isEnd()){
                    Intent intent=new Intent(context,Drill2Activity.class);
                    intent.putExtra("lives",getIntent().getExtras().getInt("lives")+1);
                    startActivity(intent);
                }
                else {
                    Intent intent=new Intent(context,GameOverActivity.class);
                    startActivity(intent);
                }
            }
        });
        dialog.setButton(Dialog.BUTTON_NEGATIVE, "NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(context,ActivityEnd.class);
                startActivity(intent);
            }
        });
        dialog.show();
    }
}
