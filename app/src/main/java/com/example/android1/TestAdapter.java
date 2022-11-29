package com.example.android1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    //인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int position) ;
    }


    ArrayList<AdapterItem> localData;
    String TAG = "TestAdapter";
    String uriToString;
    Context context;

    //뷰홀더
    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView1;
        ImageView imageView;
        Button modify_btn;
        Button remove_btn;


        public ViewHolder(@NonNull View itemview) {
            super(itemview);
            textView1 = itemview.findViewById(R.id.textview_activitysub); //리사이클러뷰_아이템 텍스트뷰
            imageView = itemview.findViewById(R.id.imageview_activitysub);


           remove_btn = itemview.findViewById(R.id.remove_btn);
           remove_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   int pos = getAdapterPosition();
                   if(pos != RecyclerView.NO_POSITION) {

                       localData.remove(pos);

                       Log.e(TAG, "LocalDat(전체 어레이리스트 ) : " + localData);

                       notifyDataSetChanged();
                   }


               }
           });



            modify_btn = itemview.findViewById(R.id.modify_btn);
            modify_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e(TAG, "수정버튼  눌러짐");
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION) {
                        String resultdata = localData.get(pos).getComment();
                        Log.e(TAG, "resultdate : " + resultdata);

                        Intent intent = new Intent(context.getApplicationContext() , MainActivity2.class);   // 특이점.. context.getApplicationContext()
                        intent.putExtra("uri",uriToString);
//                        intent.putExtra("putdata" , resultdata);
//                        intent.putExtra( "pos", pos);
//                        intent.putExtra("main_put_str", localData);
//                        Log.e(TAG,"putdata 전송" + resultdata);
//                        Log.e(TAG, "Main_put_str  LocalData 어레이리스트 값 : " + localData );

                        context.startActivity(intent);
                    }

                }
            });


        }
    }
    //생성자를 통해서 데이터 받게 함
    public TestAdapter(ArrayList<AdapterItem> dataSet, Context context){
        this.context = context;
        localData = new ArrayList<>();
        localData = dataSet;
        Log.e("text", ""+localData);
    }

    @NonNull
    @Override  //뷰 홀더 객체를 생성하여 리턴한다
    public TestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);

        TestAdapter.ViewHolder viewHolder = new TestAdapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    //뷰홀더 안의 내용을 포지션에 해당하는 데이터로 교체한다.
    public void onBindViewHolder(@NonNull TestAdapter.ViewHolder holder, int position){
        String str = localData.get(position).getComment();
        Log.e("text", "어댑터에서 내용 받아옴?" + str);
        uriToString = localData.get(position).getIdNum();
        Uri uri = Uri.parse(uriToString);
        holder.textView1.setText(str);
        Glide.with(context).load(uri).into(holder.imageView);



    }

    //전체 데이터 개수를 리턴한다.
    public int getItemCount(){

        return localData.size();
    }



}


