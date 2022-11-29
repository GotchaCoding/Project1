package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity2 extends AppCompatActivity {

    String strUri;
    String TAG = "MainActivity2";
    ImageView imageView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imageView2 = findViewById(R.id.imageView2);

        Intent intent = getIntent();
        strUri = intent.getExtras().getString("uri");

        Log.e(TAG, "받아온 값 : " + strUri);

        Uri uri = Uri.parse(strUri);


        Glide.with(getApplicationContext()).load(uri).into(imageView2);

    }
}