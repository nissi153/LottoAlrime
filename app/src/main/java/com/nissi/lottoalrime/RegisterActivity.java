package com.nissi.lottoalrime;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText editTextNum1;
    EditText editTextNum2;
    EditText editTextNum3;
    EditText editTextNum4;
    EditText editTextNum5;
    EditText editTextNum6;
    EditText editTextTurn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextNum3 = findViewById(R.id.editTextNum3);
        editTextNum4 = findViewById(R.id.editTextNum4);
        editTextNum5 = findViewById(R.id.editTextNum5);
        editTextNum6 = findViewById(R.id.editTextNum6);
        editTextTurn = findViewById(R.id.editTextTurn);

        editTextNum1.setText("");
        editTextNum2.setText("");
        editTextNum3.setText("");
        editTextNum4.setText("");
        editTextNum5.setText("");
        editTextNum6.setText("");
        editTextTurn.setText("");
    }

    public void onClickSave(View v) {

        int number1 = 0;
        int number2 = 0;
        int number3 = 0;
        int number4 = 0;
        int number5 = 0;
        int number6 = 0;
        if(editTextNum1.getText().toString().length()>0){
            number1 = Integer.parseInt(editTextNum1.getText().toString());
        }
        if(editTextNum2.getText().toString().length()>0){
            number2 = Integer.parseInt(editTextNum2.getText().toString());
        }
        if(editTextNum3.getText().toString().length()>0){
            number3 = Integer.parseInt(editTextNum3.getText().toString());
        }
        if(editTextNum4.getText().toString().length()>0){
            number4 = Integer.parseInt(editTextNum4.getText().toString());
        }
        if(editTextNum5.getText().toString().length()>0){
            number5 = Integer.parseInt(editTextNum5.getText().toString());
        }
        if(editTextNum6.getText().toString().length()>0){
            number6 = Integer.parseInt(editTextNum6.getText().toString());
        }

        //폰의 파일시스템(플래시메모리)에 번호를 저장함.
        SharedPreferences prefs = getSharedPreferences("Lotto", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("number1", String.valueOf(number1));
        editor.putString("number2", String.valueOf(number2));
        editor.putString("number3", String.valueOf(number3));
        editor.putString("number4", String.valueOf(number4));
        editor.putString("number5", String.valueOf(number5));
        editor.putString("number6", String.valueOf(number6));
        editor.putString("turn", editTextTurn.getText().toString());
        editor.commit();  //실제 저장을 수행하는 명령임.

        Toast.makeText(getApplicationContext(), "저장되었습니다.",
                Toast.LENGTH_LONG).show();
    }

    public void onClickLoad(View v) {
        //저장된 로또 번호를 가져오는 메소드
        SharedPreferences prefs = getSharedPreferences("Lotto", MODE_PRIVATE);
        String number1 = prefs.getString("number1", "");
        String number2 = prefs.getString("number2", "");
        String number3 = prefs.getString("number3", "");
        String number4 = prefs.getString("number4", "");
        String number5 = prefs.getString("number5", "");
        String number6 = prefs.getString("number6", "");
        String turn = prefs.getString("turn", "");

        editTextNum1.setText("");
        editTextNum2.setText("");
        editTextNum3.setText("");
        editTextNum4.setText("");
        editTextNum5.setText("");
        editTextNum6.setText("");
        editTextTurn.setText("");

        //edittext에 로또 번호를 표시함
        if( number1.length()>0)
            editTextNum1.setText(number1);
        if( number2.length()>0)
            editTextNum2.setText(number2);
        if( number3.length()>0)
            editTextNum3.setText(number3);
        if( number4.length()>0)
            editTextNum4.setText(number4);
        if( number5.length()>0)
            editTextNum5.setText(number5);
        if( number6.length()>0)
            editTextNum6.setText(number6);

        editTextTurn.setText(turn);

    }

}
