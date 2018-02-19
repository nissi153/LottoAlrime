package com.nissi.lottoalrime;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickChoose(View v) {
        Intent intent=new Intent(MainActivity.this,ChooseActivity.class);
        startActivity(intent);
    }
    public void onClickRegister(View v) {
        Intent intent=new Intent(MainActivity.this,RegisterActivity.class);
        startActivity(intent);
    }
    public void onClickResult(View v) {
        Intent intent=new Intent(MainActivity.this,ResultActivity.class);
        startActivity(intent);
    }
}
