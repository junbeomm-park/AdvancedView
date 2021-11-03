package com.example.advancedview;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class RadioActivity extends AppCompatActivity {
    // 뷰의 주소값을 담을 참조변수
    RadioButton radio3, radio4;
    RadioGroup group1, group2;
    TextView text1, text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radio);
        // 뷰의 주소 값을 얻어온다.
        radio3 = (RadioButton)findViewById(R.id.radioButton3);
        radio4 = (RadioButton)findViewById(R.id.radioButton4);
        group1 = (RadioGroup)findViewById(R.id.group1);
        group2 = (RadioGroup)findViewById(R.id.group2);
        text1 = (TextView)findViewById(R.id.textView);
        text2 = (TextView)findViewById(R.id.textView2);

        //이벤트연결 - 라디오그룹에 리스너를 연결
        RadioListener listener = new RadioListener();
        group1.setOnCheckedChangeListener(listener);
        group2.setOnCheckedChangeListener(listener);
    }
    public void radioCheck(View v){
        radio3.setChecked(true);
        radio4.setChecked(true);
    }
    public void getCheckStatus(View v){
        //라디오그룹내에서 선택되어 있는 라디오버튼을 반환 - id값을 반환
        int id1 = group1.getCheckedRadioButtonId();
        int id2 = group2.getCheckedRadioButtonId();
        text1.setText(id1+"라디오버튼이 선택");
        text2.setText(id2+"라디오버튼이 선택");
    }
   class RadioListener implements RadioGroup.OnCheckedChangeListener{

       @Override
       public void onCheckedChanged(RadioGroup group, int checkedId) {
           Log.d("park",group.getId()+"::::::::::"+checkedId);
           switch (group.getId()){
               case R.id.group1:
                   switch (checkedId){
                       case R.id.radioButton:
                           Log.d("park","1번 그룹의 1-1버튼");
                   }
                   break;
               case R.id.group2:
                   switch (checkedId){
                       case R.id.radioButton4:
                           Log.d("park","2번 그룹의 2-1버튼");
                   }
                   break;
           }
       }
   }
}










