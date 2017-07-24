package com.example.android.mathgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import Objects.User;

public class ActivitySignUp extends AppCompatActivity {
    Button startBtn;
    ImageButton imageButton;
    EditText nameET;
    ImageView imageView;
    static final int CAMERA_REQUEST=1;
    private static User user=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        startBtn= (Button) findViewById(R.id.startBtn);
        imageButton=(ImageButton) findViewById(R.id.imageButton);
        nameET=(EditText) findViewById(R.id.nameET);
        imageView=(ImageView) findViewById(R.id.imageView);
        setImageButton();
        setStartBtn();

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
    public void setStartBtn() {
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nameET.getText().toString().equals("")||!hasImage(imageView)) {
                    Toast.makeText(ActivitySignUp.this, "please make sure you entered your name and took a photo", Toast.LENGTH_LONG).show();
                    return;
                }
                String name=nameET.getText().toString();
                Bitmap photo=((BitmapDrawable)imageView.getDrawable()).getBitmap();
                user.setName(name);
                user.setPhoto(photo);
                Intent intent=new Intent(view.getContext(),Drill1Activity.class);
                startActivity(intent);
            }
        });
    }

    private boolean hasImage(@NonNull ImageView view) {
        Drawable drawable = view.getDrawable();
        boolean hasImage = (drawable != null);

        if (hasImage && (drawable instanceof BitmapDrawable)) {
            hasImage = ((BitmapDrawable) drawable).getBitmap() != null;
        }

        return hasImage;
    }

    public static User getUser() {
        return user;
    }
}

