package com.example.android1;

import android.os.Parcel;
import android.os.Parcelable;

public class AdapterItem implements Parcelable{


    String comment; //내용
    String idNum;  //프사

    public AdapterItem(String comment1 , String idNum1 ){
        comment = comment1;
        idNum = idNum1 ;
    }

    //parcel code
    protected AdapterItem(Parcel in) {
        this.comment = in.readString();
        this.idNum = in.readString();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }



    public static final Parcelable.Creator<AdapterItem> CREATOR = new Parcelable.Creator<AdapterItem>() {
        @Override
        public AdapterItem createFromParcel(Parcel in) {
            return new AdapterItem(in);
        }

        @Override
        public AdapterItem[] newArray(int size) {
            return new AdapterItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(comment);
        parcel.writeString(idNum);
    }
}
