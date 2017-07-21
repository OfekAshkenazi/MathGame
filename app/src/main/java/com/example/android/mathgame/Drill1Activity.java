package com.example.android.mathgame;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Drill1Activity extends AppCompatActivity {
    TextView questionTV;
    EditText answerET;
    Button checkBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill1);
        questionTV=(TextView) findViewById(R.id.questionTV);
        answerET=(EditText) findViewById(R.id.answerDrill1);
        checkBtn=(Button) findViewById(R.id.checkDrill1);

    }

    public void setCheckBtn(Button checkBtn) {
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

                }
            }
        });
    }

    public String generateDrill(){
        String str="";
        Random random =new Random();
        int x=random.nextInt();
        int y=random.nextInt();
        str = str+x+"+"+y+"=?";
        return str;
    }
    public long getAnswer(String str){
        int x=Integer.parseInt(str.substring(0,str.indexOf('+')));
        int y=Integer.parseInt(str.substring(str.indexOf('+')+1,str.indexOf('=')));
        return x+y;
    }
}
