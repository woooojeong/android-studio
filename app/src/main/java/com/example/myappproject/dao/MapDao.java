package com.example.myappproject.dao;

import android.database.sqlite.SQLiteDatabase;

public class MapDao {
    SQLiteDatabase db;

    public MapDao(SQLiteDatabase db){
        this.db = db;
    }

    //Map 테이블 생성
    public void createTable(){
        db.execSQL("create table if not exists map (" +
                " mid integer primary key," +
                " city text not null," +
                " lat text," +
                " lng text)");
    }

}//MapDao
