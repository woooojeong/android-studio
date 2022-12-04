package com.example.myappproject.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.myappproject.dto.MemberDto;

public class MemberDao {
    SQLiteDatabase db;

    public MemberDao(SQLiteDatabase db){
        this.db = db;
    }

    /* 로그인 */
    public int login(MemberDto memberDto){
        int result = 0;
        String sql = "select count(email) from member where email='" +
                memberDto.getEmail()  +"' and pass='"+ memberDto.getPass() +"'";

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            result = cursor.getInt(0);
        }
        return result;
    }

    /* 회원가입 : insert */
    public void insert(MemberDto memberDto){
        String sql = "insert into member(email,pass) " +
                        " values('" + memberDto.getEmail() + "'," +
                        " '"+ memberDto.getPass() +"')";
        db.execSQL(sql);
    }


    /* Member 테이블생성 */
    public void createTable(){
        db.execSQL("create table if not exists member (" +
                " email text primary key, " +
                " pass  text  not null," +
                " phone text " +
                ")");
    }

}
