package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity2 extends AppCompatActivity {

    TextView TextView2;
    Button Home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);


        TextView2 = findViewById(R.id.TextView2);
        Intent intent = getIntent();
        String str = intent.getStringExtra("str1");

        TextView2.setText(str);  // str데이터 넣기

        Home = findViewById(R.id.Home);
        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SubActivity2.this , MainActivity.class);
                startActivity(intent);
            }
        });

    }
}