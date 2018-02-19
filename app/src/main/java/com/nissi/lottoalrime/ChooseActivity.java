package com.nissi.lottoalrime;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class ChooseActivity extends AppCompatActivity {

    TextView textViewNum1;
    TextView textViewNum2;
    TextView textViewNum3;
    TextView textViewNum4;
    TextView textViewNum5;
    TextView textViewNum6;

    EditText editTextNum1;
    EditText editTextNum2;
    EditText editTextNum3;
    EditText editTextNum4;
    EditText editTextNum5;
    EditText editTextNum6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);

        textViewNum1 = findViewById(R.id.textViewNum1);
        textViewNum2 = findViewById(R.id.textViewNum2);
        textViewNum3 = findViewById(R.id.textViewNum3);
        textViewNum4 = findViewById(R.id.textViewNum4);
        textViewNum5 = findViewById(R.id.textViewNum5);
        textViewNum6 = findViewById(R.id.textViewNum6);

        editTextNum1 = findViewById(R.id.editTextNum1);
        editTextNum2 = findViewById(R.id.editTextNum2);
        editTextNum3 = findViewById(R.id.editTextNum3);
        editTextNum4 = findViewById(R.id.editTextNum4);
        editTextNum5 = findViewById(R.id.editTextNum5);
        editTextNum6 = findViewById(R.id.editTextNum6);

        textViewNum1.setText("");
        textViewNum2.setText("");
        textViewNum3.setText("");
        textViewNum4.setText("");
        textViewNum5.setText("");
        textViewNum6.setText("");

        editTextNum1.setText("");
        editTextNum2.setText("");
        editTextNum3.setText("");
        editTextNum4.setText("");
        editTextNum5.setText("");
        editTextNum6.setText("");
    }

    public void onClickChoose(View v) {

        int[] manual_numbers = new int[6];
        manual_numbers[0] = 0;
        manual_numbers[1] = 0;
        manual_numbers[2] = 0;
        manual_numbers[3] = 0;
        manual_numbers[4] = 0;
        manual_numbers[5] = 0;

        int manual_numbers_count = 0;
        if(editTextNum1.getText().toString().length()>0){
            manual_numbers[0] = Integer.parseInt(editTextNum1.getText().toString());
            manual_numbers_count++;
        }
        if(editTextNum2.getText().toString().length()>0){
            manual_numbers[1] = Integer.parseInt(editTextNum2.getText().toString());
            manual_numbers_count++;
        }
        if(editTextNum3.getText().toString().length()>0){
            manual_numbers[2] = Integer.parseInt(editTextNum3.getText().toString());
            manual_numbers_count++;
        }
        if(editTextNum4.getText().toString().length()>0){
            manual_numbers[3] = Integer.parseInt(editTextNum4.getText().toString());
            manual_numbers_count++;
        }
        if(editTextNum5.getText().toString().length()>0){
            manual_numbers[4] = Integer.parseInt(editTextNum5.getText().toString());
            manual_numbers_count++;
        }
        if(editTextNum6.getText().toString().length()>0){
            manual_numbers[5] = Integer.parseInt(editTextNum6.getText().toString());
            manual_numbers_count++;
        }


        getLotto(manual_numbers_count, manual_numbers);
    }

    private void getLotto(int manual_numbers_count, int[] manual_numbers)
    {
        int[] numbers = new int[6];

        for(int k=0; k<manual_numbers_count; k++) {
            numbers[k] = manual_numbers[k];
        }

        //numbers안에 랜덤숫자를 1~45까지 발생시켜 넣으시오.
        //중복안되게 하고,

        Random random = new Random();
        for(int i=manual_numbers_count; i<6; i++){
            numbers[i] = random.nextInt(45)+1;

            for(int j=0; j<i; j++) {
                if(numbers[i] == numbers[j]) {
                    //중복됨.
                    i--;
                    System.out.println("다시뽑음!");
                }
            }
        }
        //오름차순 정렬후, 값을 설정하시오.
        int temp;
        for(int i=0; i<numbers.length-1; i++) {
            for(int j=i+1; j<numbers.length; j++) {
                if(numbers[i] > numbers[j]) {
                    temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }

            }
        }


        textViewNum1.setText(String.valueOf(numbers[0]));
        textViewNum2.setText(String.valueOf(numbers[1]));
        textViewNum3.setText(String.valueOf(numbers[2]));
        textViewNum4.setText(String.valueOf(numbers[3]));
        textViewNum5.setText(String.valueOf(numbers[4]));
        textViewNum6.setText(String.valueOf(numbers[5]));

    }

}
