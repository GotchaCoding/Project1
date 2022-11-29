package com.example.android1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    String value ;
    SharedPreferences shared;
//    TextView textView3;

    android.widget.Button btn_move;   // 버튼 선언
    Button remove_btn;
    Button modify_btn;


    ArrayList<AdapterItem> testDataSet;
    ArrayList<String> arrayListTest ;
    String logTag = "MainLog";


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        Log.e(logTag , " on creat 실행 ");
        setContentView(R.layout.activity_main);

//sharedPreference 데이터 불러오기
        shared = getSharedPreferences("sd" , MODE_PRIVATE);
        shared.getString("save" , "데이터 없을때 표시") ;

        Log.e(logTag, " shared get 으로 받아오는지 test " + shared.getString("save", "데이터 없을때"));
        value = shared.getString("save", "");
        Log.e(logTag, "value 값 확인 : " + value);



        if(value == null){
            Log.e(logTag, "value 확인 : " + value);
        }

        String[] sharedResult = value.split("구분문자");

        Log.e(logTag , " sharedresult 확인  : "+ sharedResult[0]);


//        Log.e(logTag , " sharedresult 확인  : "+ sharedResult[1]);
//        for( int i = 0 ; i<sharedResult.length ; i++){
//            testDataSet.add(sharedResult[i]);
//
//        Log.e(logTag, " sharedResult  쪼개서 저장 됫는지 확인 : " + sharedResult[0]);
//        Log.e(logTag, " sharedResult  쪼개서 저장 됫는지 확인 : " + sharedResult[1]);
//        ///sharedPref 종료




        testDataSet = new ArrayList<>();
        //데이터 옮기기
//        Intent intent1=getIntent();
//        ArrayList<String> data= (ArrayList<String>) intent1.getSerializableExtra("profile");
//        test5=data.get(0);


        //리사이클러 뷰 테스트 더미 생성
//        ArrayList<String> testDataSet = new ArrayList<>();


//            testDataSet.add("Test DATA");
//            testDataSet.add("Okay");
//        Log.e("text3", ""+testDataSet);




        Intent intent = getIntent();
        testDataSet = intent.getParcelableArrayListExtra("write-main_send");



        Log.e(logTag , "서브 액티비티에서 인텐트로 데이터를 받아 왔을 때 어레이 리스트 값 : "+ testDataSet);

        if (testDataSet == null){
            testDataSet = new ArrayList<>();
            Log.e(logTag, "인텐트로 받아오는 값이 없을 떄 어레이 리스트 초기화 후 값 : " + testDataSet);
//        TextView2.setText(str);  // str데이터 넣기
//            testDataSet.add(str);
        }



        RecyclerView recyclerView = findViewById(R.id.recyclerView_activitymain);

        //LayoutManager   종류 3  1. LinearLayoutManager  2. GridLayoutManager  3.StaggerGridLayoutManager

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);  // 레이아웃 메니져 설

        if(testDataSet != null){
            for(int i = 0; i < testDataSet.size(); i++){
                Log.e("text1","" + testDataSet.get(i).getComment());
            }
            TestAdapter customAdapter = new TestAdapter(testDataSet, MainActivity.this);

            recyclerView.setAdapter(customAdapter);
        }

//        TestAdapter customAdapter = new TestAdapter(testDataSet);
//        recyclerView.setAdapter(customAdapter);  //어뎁터 설정


//        textView3 = findViewById(R.id.textView3);
//        Intent intent = getIntent();
//        String str = intent.getStringExtra("str1");
//
//        textView3.setText(str);  // str데이터 넣기








        btn_move = findViewById(R.id.btn_move);  // btn_move 의 아이디 찾아와라
        btn_move.setOnClickListener(new View.OnClickListener() {   // 눌럿을때 안쪽 내용 실행
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this , SubActivity.class);   //인텐트

                intent.putExtra("main_put_str", testDataSet);

                // 토스트 메세지
                Context context = getApplicationContext();
                CharSequence text = " 버튼 눌럿음 ";
                int duration = Toast.LENGTH_LONG;
                Toast.makeText(context , text, duration).show();
                //* 토스트 메세지 종료


                startActivity(intent);
            }
        });











    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(logTag , " onstart 실행 ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(logTag , " onresume 실행 ");
    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.e(logTag , " on pasue 실행 ");


        Log.e(logTag , " on ㅇdestroy 실행 ");

        SharedPreferences shared = getSharedPreferences("sd" , Context.MODE_PRIVATE);
        shared = this.shared;
        SharedPreferences.Editor editor = shared.edit();
//                editor.putString()
        String value = ""; // 지역변수는 값을 초기화 하고 사용해야함... 전역변수랑 다름.
        for(int i = 0;  i<testDataSet.size() ; i++) {
            Log.e(logTag, "size 몇 번?? " + i);
            testDataSet.get(i).toString();
            Log.e(logTag, " to string 확인 : " + testDataSet.get(i));

            value += testDataSet.get(i) + "구분문자";
            this.value = value;
            Log.e(logTag, "어레이리스트  취합된거 확인 : " + value);
        }
        editor.putString("save" , value);
        editor.commit();


        Log.e(logTag, "shared 데이터 넘어가나? : " + shared.toString());


        editor.commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(logTag , " on stop 실행 ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(logTag , " onre strat 실행 ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();    //데이터 폴더 사라짐  value 스플릿 하면 될듯 . .
//        Log.e(logTag , " on ㅇdestroy 실행 ");
//
//        SharedPreferences shared = getSharedPreferences("sd" , Context.MODE_PRIVATE);
//        shared = this.shared;
//                SharedPreferences.Editor editor = shared.edit();
////                editor.putString()
//                String value = ""; // 지역변수는 값을 초기화 하고 사용해야함... 전역변수랑 다름.
//                for(int i = 0;  i<testDataSet.size() ; i++) {
//                    Log.e(logTag, "size 몇 번?? " + i);
//                    testDataSet.get(i).toString();
//                    Log.e(logTag, " to string 확인 : " + testDataSet.get(i));
//
//                    value += testDataSet.get(i) + "구분문자";
//                    this.value = value;
//                    Log.e(logTag, "어레이리스트  취합된거 확인 : " + value);
//                }
//                    editor.putString("save" , value);
//                    editor.commit();
//
//
//                Log.e(logTag, "shared 데이터 넘어가나? : " + shared.toString());
//
//
//                editor.commit();



    }
}