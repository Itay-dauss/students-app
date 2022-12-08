package com.example.assignment2_students_app.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
    public String name;
    public String id;
    public long phone;
    public String address;
    public String avatarUrl;
    public Boolean cb;

    public Student(String name, String id, long phone, String address, String avatarUrl, Boolean cb) {
        this.name = name;
        this.id = id;
        this.phone = phone;
        this.address = address;
        this.avatarUrl = avatarUrl;
        this.cb = cb;
    }


    protected Student(Parcel in) {
        name = in.readString();
        id = in.readString();
        phone = in.readLong();
        address = in.readString();
        avatarUrl = in.readString();
        byte tmpCb = in.readByte();
        cb = tmpCb == 0 ? null : tmpCb == 1;
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(id);
        parcel.writeLong(phone);
        parcel.writeString(address);
        parcel.writeString(avatarUrl);
        parcel.writeByte((byte) (cb == null ? 0 : cb ? 1 : 2));
    }
}
