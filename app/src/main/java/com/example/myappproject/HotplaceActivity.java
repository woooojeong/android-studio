package com.example.myappproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.Toast;

public class HotplaceActivity extends Activity {

    Gallery gallery;
    Integer[] hpId = MainActivity.hotplaceDao.select();
    ImageView ivMap,ivPerson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hotplace);
        gallery = (Gallery) findViewById(R.id.hp_gallery);
        ivMap = (ImageView) findViewById(R.id.ivMap);
        ivPerson = (ImageView) findViewById(R.id.ivPerson);

        MyGalleryAdapter adapter = new MyGalleryAdapter(this);
        gallery.setAdapter(adapter);

        //gallery의 아이템을 클릭하면 ivPoster 창에 영화포스터 출력하는 이벤트 처리
        gallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "ddd", Toast.LENGTH_SHORT).show();
//                ivPoster.setImageResource(posterId[position]);
//                ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
        });

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapIntent = new Intent(getApplicationContext(), com.example.myappproject.MapActivity.class);
                startActivity(mapIntent);
            }
        });

        ivPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //대화상자
                AlertDialog.Builder dlg = new AlertDialog.Builder(HotplaceActivity.this);
                dlg.setTitle("confirm exit");
                dlg.setIcon(R.drawable.exit);
                dlg.setMessage("Are you sure you want to exit?");
                dlg.setPositiveButton("exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish(); //현재 액티비티만 종료
                        finishAffinity();
                        System.runFinalization();
                        System.exit(0);
                    }
                });
                dlg.setNegativeButton("logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent loginIntent = new Intent(getApplicationContext(),LoginActivity.class);
                        startActivity(loginIntent);
                    }
                });

                dlg.show();

            }
        });

    }

    //MyGalleryAdapter 클래스 정의 : inner class
    class MyGalleryAdapter extends BaseAdapter {  //MainActivity$MyGalleryAdapter
        Context context;

        public MyGalleryAdapter(){}
        public MyGalleryAdapter(Context context){
            this.context = context;
        }

        //Adapter 기능을 구현하기 위한 4가지 메소드 정의 --> API를 참조하여 Adapter 클래스의 메소드
        public int getCount(){
            return hpId.length;
        }

        public Object getItem(int position){
            return null;
        }

        public long getItemId(int position){
            return 0;
        }

        public View getView(int position, View counterView, ViewGroup parent){
            ImageView imgView = new ImageView(context);
            imgView.setLayoutParams(new Gallery.LayoutParams(100, 150));
            imgView.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imgView.setImageResource(hpId[position]);

            return imgView;
        }

    }//MyGalleryAdapter
}
