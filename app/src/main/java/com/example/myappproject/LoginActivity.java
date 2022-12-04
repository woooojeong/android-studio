package com.example.myappproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myappproject.dto.MemberDto;

public class LoginActivity extends Activity {
    EditText editLoginEmail, editLoginPass;
    ImageView btnSignUp, ivLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        btnSignUp = (ImageView) findViewById(R.id.btnSignUp);
        ivLogin = (ImageView) findViewById(R.id.ivLogin);
        editLoginEmail = (EditText) findViewById(R.id.editLoginEmail);
        editLoginPass = (EditText) findViewById(R.id.editLoginPass);
        
        //회원가입 이동
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signupIntent = new Intent(getApplicationContext(), com.example.myappproject.SignupActivity.class);
                startActivity(signupIntent);
            }
        });
        
        //로그인
        ivLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(valiCheck()){
                    MemberDto memberDto = new MemberDto();
                    memberDto.setEmail(editLoginEmail.getText().toString());
                    memberDto.setPass(editLoginPass.getText().toString());

                    int result = MainActivity.memberDao.login(memberDto);
                    if(result == 1){
                        Intent hotplaceIntent = new Intent(getApplicationContext(),HotplaceActivity.class);
                        //데이터 객체 전송 : hotplaceIntent.putExtra(key,value);
                        startActivity(hotplaceIntent);
                    }else{
                        AlertDialog.Builder dlg = new AlertDialog.Builder(LoginActivity.this);
                        dlg.setTitle("로그인 결과");
                        dlg.setMessage("아이디 또는 패스워드가 다릅니다");
                        dlg.setNegativeButton("닫기",null);
                        dlg.show();
                    }
                }


//                Intent hotplaceIntent = new Intent(getApplicationContext(), com.example.myappproject.HotplaceActivity.class);
//                startActivity(hotplaceIntent);
            }
        });
    }//onCreate method

    //valiCheck
    public boolean valiCheck(){
        if(editLoginEmail.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"이메일을 입력해주세요", Toast.LENGTH_SHORT).show();
            editLoginEmail.requestFocus();
            return false;
        }else if(editLoginPass.getText().length() == 0){
            Toast.makeText(getApplicationContext(),"패스워드를 입력해주세요", Toast.LENGTH_SHORT).show();
            editLoginPass.requestFocus();
            return false;
        }else{
            return true;
        }
    }//valiCheck method

}//LoginActivity class
