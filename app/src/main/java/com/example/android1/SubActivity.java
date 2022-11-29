package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class SubActivity extends AppCompatActivity {

    Button button2;
    Button MoveBack;
    Button btn_delete;
    Button modify_sub;
    Button remove_btn;
    String uriToString;
    EditText EditText1;
    ImageView imageView;
    String str;
    String receiveData;
    SharedPreferences shared;
    private final int GET_IMAGE_FOR_IMAGEVIEW = 0;


    ArrayList<String> data;
    ArrayList<AdapterItem> data2 = new ArrayList<>();
    String logTag = "SubLog";
    int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sub);

        imageView = findViewById(R.id.imageView);
        EditText1 = findViewById(R.id.EditText1);  //에딧텍스트
       // str = EditText1.getText().toString();   //에딧텍스트 str에 값 저장  (to 스트링화 해서)



        Intent intent = getIntent();
        pos = intent.getExtras().getInt("pos");
        Log.e(logTag, "포지션 인트값 : " + pos);



        data= (ArrayList<String>) intent.getSerializableExtra("main_put_str");
        Log.e(logTag , "메인 액티비티에서 받아온 어레이 리스트 값 : "+data);

        receiveData = intent.getStringExtra("putdata");
        Log.e(logTag, "receiveData 불러오기 : "+ receiveData);
        EditText1.setText(receiveData);



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, GET_IMAGE_FOR_IMAGEVIEW);
            }
        });


        MoveBack = findViewById(R.id.MoveBack);
        MoveBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });
        modify_sub = findViewById(R.id.modify_sub);
        modify_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                data.set(pos, EditText1.getText().toString());
                Log.e(logTag, "수정된 리스트 확인 : "+ data);
                Intent intent = new Intent( SubActivity.this , MainActivity.class);
                intent.putExtra("str1" , data);
                startActivity(intent);
            }
        });



        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                str = EditText1.getText().toString();   //에딧텍스트 str에 값 저장  (to 스트링화 해서)


                Log.e(logTag, "에딧텍스트에 적은 값 : " + str);
                Log.e(logTag , "uri to stirng 확인 : " + uriToString);
                data2.add(new AdapterItem(str,uriToString));


                Intent intent = new Intent(SubActivity.this, MainActivity.class);
                intent.putParcelableArrayListExtra("write-main_send", data2);
                Log.e(logTag , "putExtra data2 :  " + data2);



                startActivity(intent);

            }


        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Uri selectedImageUri;
        RequestOptions option1 = new RequestOptions().circleCrop();
        MultiTransformation option2 = new MultiTransformation(new CenterCrop(), new RoundedCorners(8));
        MultiTransformation option3 = new MultiTransformation(new FitCenter(), new RoundedCorners(30));

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null && data.getData() != null) {
            switch(requestCode) {
                case GET_IMAGE_FOR_IMAGEVIEW:
                    selectedImageUri = data.getData();
                    uriToString = selectedImageUri.toString();
                    Glide.with(getApplicationContext()).load(selectedImageUri).apply(option1).into(imageView);
                    break;
            }
        }
    }




}
