package com.example.android.mathgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ActivitySignUp extends AppCompatActivity {
    Button startBtn;
    ImageButton imageButton;
    EditText nameET;
    ImageView imageView;
    static final int CAMERA_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        startBtn= (Button) findViewById(R.id.startBtn);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        nameET=(EditText) findViewById(R.id.nameET);
        imageView=(ImageView) findViewById(R.id.imageView);
        setImageButton();

    }

    public void setImageButton(){
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,CAMERA_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CAMERA_REQUEST&&resultCode==RESULT_OK){
            Bitmap bitmap= (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(bitmap);
        }
    }
}
