package com.example.stefan.newapp3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageFullScreen extends AppCompatActivity {
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_full_screen);
        img=findViewById(R.id.fullscreen_image);
        Picasso.with(getApplicationContext()).load(getIntent().getStringExtra("img")).into(img);
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
    }
}
