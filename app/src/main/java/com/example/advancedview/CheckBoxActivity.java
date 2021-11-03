package com.example.advancedview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class CheckBoxActivity extends AppCompatActivity
                            implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    TextView text1;
    CheckBox[] checkArr = new CheckBox[3];
    Switch myswitch;
    Button setCheckbtn;
    Button showStatus;
    Button clearCheckbtn;
    Button reverseCheckStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check);
        //뷰의 주소값을 가지고 오기 - 26버전부터 캐스팅하지 않아도 된다.
        text1 = findViewById(R.id.checktxt);
        checkArr[0] = findViewById(R.id.check1);
        checkArr[1] = findViewById(R.id.check2);
        checkArr[2] = findViewById(R.id.check3);
        myswitch = findViewById(R.id.switch1);
        setCheckbtn = findViewById(R.id.btncheck1);
        showStatus = findViewById(R.id.btncheck2);
        clearCheckbtn = findViewById(R.id.btncheck3);
        reverseCheckStatus = findViewById(R.id.btncheck4);

        //체크박스, 버튼에 리스너 설정하기
        for(int i=0;i<checkArr.length;i++){
            checkArr[i].setOnCheckedChangeListener(this);
        }
        setCheckbtn.setOnClickListener(this);
        showStatus.setOnClickListener(this);
        clearCheckbtn.setOnClickListener(this);
        reverseCheckStatus.setOnClickListener(this);
        myswitch.setOnCheckedChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btncheck1:
                //모든체크박스 설정
                setCheckVal(true);
                break;
            case R.id.btncheck2:
                //모든체크박스의 상태를 TextView에 출력
                getCheckStatus();
                break;
            case R.id.btncheck3:
                //모든체크박스 해제
                setCheckVal(false);
                break;
            case R.id.btncheck4:
                //체크박스가 선택되어 있으면 해제, 해제되어 있으면 선택
                reverseStatus();
                break;
        }
    }


    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.d("park",buttonView.toString()+":::::::::"+isChecked+"::::::::::"+buttonView.getTag());
        if(buttonView instanceof CheckBox){
            display(Integer.parseInt(buttonView.getTag()+""), isChecked);
        }else{
           // 스위치가 선택되면 Toast로 "XXXX체크 박스 선택", 해제되면 "XXXX"체크박스 해제
            if(buttonView.getId()==R.id.switch1){
                String msg = "";
                if(buttonView.isChecked()){ // isChecked()는 체크 상태인지 ?
                    msg="활성상태";
                }else{
                    msg="비활성상태";
                }
                Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
            }
        }
    }
    // 체크박스의 상태가 변경될때 호출되는 메소드
    // => 체크박스와
    // => 스위치도 체크해제에 따라 토스트를 출력하기
    // => TextView출력
    // checkStatus => 선택유무값
    // index => 선택되거나 해제된 체크박스를 구분할 수 있는 태그값
    public void display(int index,boolean checkStatus){
        if(checkStatus){
            text1.setText(index + "체크박스가 선택");
        }else{
            text1.setText(index + "체크박스가 해제");
        }
    }


    // 모든 체크박스의 상태를 체크 상태로 설정 - 매개변수로 이용해서 설정 및 해제
    public void setCheckVal(boolean chkval){
        //모든 체크박스가 체크가 되거나 체크가 해제될 수 있도록 코드를 구현
        //체크상태설정 버튼과 체크상태해제 버튼을 누르면 실행될 수 있도록 구현
        for(int i=0;i<checkArr.length;i++){
            checkArr[i].setChecked(chkval);
        }
    }


    //모든체크박스의 상태를 TextView에 출력
    public void getCheckStatus() {
        text1.setText("");
        for(int i=0;i<checkArr.length;i++){
            if(checkArr[i].isChecked()){
                String tag =(String)checkArr[i].getTag();
                text1.append(tag+"번 체크박스의 체크가 설정\n");
            }else{
                String tag =(String)checkArr[i].getTag();
                text1.append(tag+"번 체크박스의 체크가 해제\n");
            }
        }
    }


    //체크박스가 선택되어 있으면 해제, 해제되어 있으면 선택
    public void reverseStatus() {
        for(int i=0;i<checkArr.length;i++){
            checkArr[i].toggle();
        }
    }
}