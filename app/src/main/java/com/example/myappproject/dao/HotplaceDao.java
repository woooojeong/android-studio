package com.example.myappproject.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myappproject.R;
import com.example.myappproject.dto.HotplaceDto;

public class HotplaceDao {
    SQLiteDatabase db;
    Integer[] pidList = {R.drawable.hp_01, R.drawable.hp_02, R.drawable.hp_03, R.drawable.hp_04,
            R.drawable.hp_05,R.drawable.hp_06};
    String[] pnameList = {"Bangkok","Bangkok","Bangkok","Bangkok","Bangkok","Bangkok"};

    public HotplaceDao(SQLiteDatabase db){ //db확장
        this.db = db;
        //insert();
    }

    //Hotplace 테이블 생성
    public void createTable(){
        db.execSQL("create table if not exists hotplace (" +
                " pid integer primary key," +
                " pname text," +
                " pcontent text" +
                ")");
    }

    //insert
    public void insert(){
        for(int i=0; i<pidList.length; i++) {
            String sql = "insert into hotplace(pid,pname) " +
                    " values('" + pidList[i] + "'," +
                    " '" + pnameList[i] + "')";
            db.execSQL(sql);
        }
    }

    //select : pidList에 넣을 데이터 가져오기
    public Integer[] select() {
        Integer[] pidList = new Integer[6];
        int count = 0;

        String sql ="select pid from hotplace";
        Cursor cursor = db.rawQuery(sql,null);
        while (cursor.moveToNext()){
            pidList[count] = cursor.getInt(0);
            count++;
        }
        return pidList;
    }
}//HotplaceDao
