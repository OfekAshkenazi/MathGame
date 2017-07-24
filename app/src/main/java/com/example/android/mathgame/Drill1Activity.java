package com.example.android.mathgame;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

import Objects.MyImageView;

public class Drill1Activity extends AppCompatActivity {
    TextView questionTV;
    EditText answerET;
    Button checkBtn;
    MyImageView lifeIV1,lifeIV2,lifeIV3;
    MyImageView[] lifeViews=new MyImageView[3];
    AlertDialog gameOverDialog;
    Context context;
    TextView nameTV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill1);
        context=this;
        questionTV=(TextView) findViewById(R.id.questionTV);
        answerET=(EditText) findViewById(R.id.answerDrill1);
        checkBtn=(Button) findViewById(R.id.checkDrill1);
        questionTV.setText(generateDrill());
        setNameTV();
        setCheckBtn();
        setLifeIV();
    }

    public void setNameTV() {
        nameTV= (TextView) findViewById(R.id.Drill1NameTV);
        nameTV.setText(ActivitySignUp.getUser().getName());
    }

    public void setGameOverDialog() {
        gameOverDialog=new AlertDialog.Builder(this).create();
        gameOverDialog.setTitle("Game Over");
        gameOverDialog.setMessage("You're out of lives");
        gameOverDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent=new Intent(context,GameOverActivity.class);
                startActivity(intent);
            }
        });
    }

    public void setLifeIV() {
        lifeIV1=(MyImageView) findViewById(R.id.LifeIV1);
        lifeIV2=(MyImageView) findViewById(R.id.LifeIV2);
        lifeIV3=(MyImageView) findViewById(R.id.LifeIV3);
        lifeViews[0] = lifeIV1;
        lifeViews[1]=lifeIV2;
        lifeViews[2]=lifeIV3;
    }

    public void setCheckBtn() {
        checkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent nextActivity;
                if (answerET.getText().toString().equals("")){
                    Toast.makeText(Drill1Activity.this, "please make sure you entered an answer", Toast.LENGTH_SHORT).show();
                    return;
                }
                long answer=Long.parseLong(answerET.getText().toString());
                if (answer==getAnswer(questionTV.getText().toString())){
                    nextActivity=new Intent(view.getContext(),Drill2Activity.class);
                    nextActivity.putExtra("lives",livesCount(lifeViews));
                    startActivity(nextActivity);
                }
                else{
                    if (fault(lifeViews)){
                        return;
                    }
                    else{
                        Intent intent=new Intent(view.getContext(),GameOverActivity.class);
                        startActivity(intent);
                    }
                }
            }
        });
    }

    public String generateDrill(){
        String str="";
        Random random =new Random();
        int x=random.nextInt(150);
        int y=random.nextInt(150);
        str = str+x+"+"+y+"=?";
        return str;
    }
    public long getAnswer(String str){
        int x=Integer.parseInt(str.substring(0,str.indexOf('+')));
        int y=Integer.parseInt(str.substring(str.indexOf('+')+1,str.indexOf('=')));
        return x+y;
    }
    public static boolean fault(MyImageView[] lifeViews){
        for (MyImageView view:lifeViews){
            if (view.isAlive()==true){
                view.kill();
                view.setImageResource(android.R.drawable.ic_delete);
                return true;
            }
        }
        return false;
    }
    public static int livesCount(MyImageView[] livesViews){
        int count=0;
        for (MyImageView view:livesViews){
            if (view.isAlive()==true)
                count++;
        }
        return count;
    }

    @Override
    public void onBackPressed() {

    }
}
