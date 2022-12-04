package com.example.myappproject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myappproject.dto.MemberDto;

public class SignupActivity extends Activity {
    EditText editEmail, editPass, editCpass;
    ImageView ivSignUp, homeSignUp;
    CheckBox chkSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        editEmail = (EditText) findViewById(R.id.editEmail);
        editPass = (EditText)  findViewById(R.id.editPass);
        editCpass = (EditText) findViewById(R.id.editCpass);
        ivSignUp = (ImageView) findViewById(R.id.ivSignUp);
        homeSignUp = (ImageView) findViewById(R.id.homeSignUp);
        chkSignUp = (CheckBox) findViewById(R.id.chkSignUp);

        //회원가입 이벤트
        ivSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valiCheck()){
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(editEmail.getText().toString());
                    memberDto.setPass(editPass.getText().toString());

                    //memberDao에 insert
                    MainActivity.memberDao.insert(memberDto);
                    Toast.makeText(getApplicationContext(), "회원가입 성공!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //home(로그인) 이벤트 처리
        homeSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "회원가입이 종료됩니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


    }//onCreate method

    //valiCheck
    public boolean valiCheck(){
        if(editEmail.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            editEmail.requestFocus();
            return false;
        }else if(editPass.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
            editPass.requestFocus();
            return false;
        }else if(editCpass.getText().length() == 0){
            Toast.makeText(getApplicationContext(), "패스워드 확인을 입력해주세요", Toast.LENGTH_SHORT).show();
            editCpass.requestFocus();
            return false;
        }else if(!chkSignUp.isChecked()){
            Toast.makeText(getApplicationContext(), "회원가입 동의를 체크해주세요", Toast.LENGTH_SHORT).show();
            chkSignUp.requestFocus();
            return false;
        }else{
            //패스워드 확인
            if(editPass.getText().toString().equals(editCpass.getText().toString())){
                return true;
            }else{
                Toast.makeText(getApplicationContext(), "패스워드가 일치하지 않습니다. 다시 입력해주세요", Toast.LENGTH_SHORT).show();
                editPass.setText("");
                editCpass.setText("");
                editPass.requestFocus();
                return false;
            }

        }
    }




}//MainActivity class
