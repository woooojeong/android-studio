package com.example.myappproject;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappproject.dao.HotplaceDao;
import com.example.myappproject.dao.MapDao;
import com.example.myappproject.dao.MemberDao;

public class MainActivity extends AppCompatActivity {

    //DB선언
    SQLiteDatabase db;
    static MemberDao memberDao;  //클래스명.객체명, 클래스명.메소드명
    static HotplaceDao hotplaceDao;
    static MapDao mapDao;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB생성
        db = openOrCreateDatabase("gotour", MODE_PRIVATE, null);
        memberDao = new MemberDao(db);
        hotplaceDao = new HotplaceDao(db);
        mapDao = new MapDao(db);

        memberDao.createTable();
        hotplaceDao.createTable();
        mapDao.createTable();

        ImageView tvStart = (ImageView) findViewById(R.id.tvStart);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "고투어 시작", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(loginIntent);
            }
        });

        
    }
}