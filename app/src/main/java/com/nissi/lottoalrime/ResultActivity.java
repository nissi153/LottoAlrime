package com.nissi.lottoalrime;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ResultActivity extends AppCompatActivity {

    TextView textViewNum1;
    TextView textViewNum2;
    TextView textViewNum3;
    TextView textViewNum4;
    TextView textViewNum5;
    TextView textViewNum6;
    TextView textViewNum7;
    EditText editTextTurn;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewNum1 = findViewById(R.id.textViewNum1);
        textViewNum2 = findViewById(R.id.textViewNum2);
        textViewNum3 = findViewById(R.id.textViewNum3);
        textViewNum4 = findViewById(R.id.textViewNum4);
        textViewNum5 = findViewById(R.id.textViewNum5);
        textViewNum6 = findViewById(R.id.textViewNum6);
        textViewNum7 = findViewById(R.id.textViewNum7);
        editTextTurn = findViewById(R.id.editTextTurn);
        textViewResult = findViewById(R.id.textViewResult);

    }

    public void onClickResult(View v) {
        String turn = editTextTurn.getText().toString();
        if(turn.length()<=0){
            Toast.makeText(getApplicationContext(), "회차를 입력하세요.",
                    Toast.LENGTH_LONG).show();
            return;
        }

        final String url = "http://www.nlotto.co.kr/common.do?method=getLottoNumber&drwNo=" + turn;

        new AsyncHttpTask().execute(url);
    }

    //서버의 당첨번호와 나의 등록된 번호를 비교한다.
    public void onClickResultCheck(View v) {

        SharedPreferences prefs = getSharedPreferences("Lotto", MODE_PRIVATE);
        String mynumber1 = prefs.getString("number1", "");
        String mynumber2 = prefs.getString("number2", "");
        String mynumber3 = prefs.getString("number3", "");
        String mynumber4 = prefs.getString("number4", "");
        String mynumber5 = prefs.getString("number5", "");
        String mynumber6 = prefs.getString("number6", "");
        int[] mynumber = new int[6];
        mynumber[0] = Integer.parseInt(mynumber1);
        mynumber[1] = Integer.parseInt(mynumber2);
        mynumber[2] = Integer.parseInt(mynumber3);
        mynumber[3] = Integer.parseInt(mynumber4);
        mynumber[4] = Integer.parseInt(mynumber5);
        mynumber[5] = Integer.parseInt(mynumber6);

        String lottonumber1 = textViewNum1.getText().toString();
        String lottonumber2 = textViewNum2.getText().toString();
        String lottonumber3 = textViewNum3.getText().toString();
        String lottonumber4 = textViewNum4.getText().toString();
        String lottonumber5 = textViewNum5.getText().toString();
        String lottonumber6 = textViewNum6.getText().toString();
        int[] lottonumber = new int[6];
        lottonumber[0] = Integer.parseInt(lottonumber1);
        lottonumber[1] = Integer.parseInt(lottonumber2);
        lottonumber[2] = Integer.parseInt(lottonumber3);
        lottonumber[3] = Integer.parseInt(lottonumber4);
        lottonumber[4] = Integer.parseInt(lottonumber5);
        lottonumber[5] = Integer.parseInt(lottonumber6);
        int bonusnumber = Integer.parseInt(textViewNum7.getText().toString());

        //1등   당첨번호 6개 숫자일치
        //2등	당첨번호 5개 숫자일치+보너스 숫자일치
        //3등	당첨번호 5개 숫자일치
        //4등	당첨번호 4개 숫자일치
        //5등	당첨번호 3개 숫자일치
        int match_count = 0;
        for(int i=0; i<6; i++ ) {
            for(int j=0; j<6; j++) {
                if( lottonumber[j] == mynumber[i]){
                    match_count++;
                }
            }
        }
        Toast.makeText(getApplicationContext(), "일치하는 번호갯수는 "+match_count,
                Toast.LENGTH_LONG).show();

        if(match_count==0) {
            textViewResult.setText("꽝! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==1) {
            textViewResult.setText("꽝! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==2) {
            textViewResult.setText("꽝! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==3) {
            textViewResult.setText("5등! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==4) {
            textViewResult.setText("4등! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==5) {

            int bonus_match_count = 0;
            for(int i=0; i<6; i++ ) {
                if( bonusnumber == mynumber[i]){
                    bonus_match_count++;
                }
            }
            if(bonus_match_count>0)
                textViewResult.setText("2등! 보너스번호 일치 + 일치하는 번호갯수는 " + match_count + "입니다.");
            else
                textViewResult.setText("3등! 일치하는 번호갯수는 " + match_count + "입니다.");
        }
        if(match_count==6) {
            textViewResult.setText("1등! 일치하는 번호갯수는 " + match_count + "입니다.");
        }



    }

    //이전 강의의 소스를 가져옴.
    public class AsyncHttpTask extends AsyncTask<String, Void, Integer> {

        @Override
        protected Integer doInBackground(String... params) {
            InputStream inputStream = null;

            HttpURLConnection urlConnection = null;

            Integer result = 0;
            try {
                /* forming th java.net.URL object */
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
                urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
                urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
                urlConnection.setRequestMethod("GET");

                /* Post 방식*/
//                StringBuilder tokenUri=new StringBuilder("dev=");
//                tokenUri.append(URLEncoder.encode("1","UTF-8"));
//                tokenUri.append("&slug=");
//                tokenUri.append(URLEncoder.encode("lalavel","UTF-8"));
//
//
//                urlConnection.setDoOutput(true);
//                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(urlConnection.getOutputStream());
//                outputStreamWriter.write(params.toString());
//                outputStreamWriter.flush();
                /* Post 방식*/

                int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
                if (statusCode ==  200) {

                    inputStream = new BufferedInputStream(urlConnection.getInputStream());

                    String response = convertInputStreamToString(inputStream);

                    parseResult(response);

                    result = 1; // Successful

                }else{
                    result = 0; //"Failed to fetch data!";
                }

            } catch (Exception e) {
                Log.d("", e.getLocalizedMessage());
            }

            return result; //"Failed to fetch data!";
        }


        @Override
        protected void onPostExecute(Integer result) {
            /* Download complete. Lets update UI */

            if(result == 1){


            }else{
                Log.e("", "Failed to fetch data!");
            }
        }
    }


    private String convertInputStreamToString(InputStream inputStream) throws IOException {

        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));

        String line = "";
        String result = "";

        while((line = bufferedReader.readLine()) != null){
            result += line;
        }

            /* Close Stream */
        if(null!=inputStream){
            inputStream.close();
        }

        return result;
    }
    private void parseResult(String result) {

        try{
            JSONObject response = new JSONObject(result);

            int bonusNo = response.getInt("bnusNo");
            int No1 = response.getInt("drwtNo1");
            int No2 = response.getInt("drwtNo2");
            int No3 = response.getInt("drwtNo3");
            int No4 = response.getInt("drwtNo4");
            int No5 = response.getInt("drwtNo5");
            int No6 = response.getInt("drwtNo6");


            textViewNum1.setText(String.valueOf(No1));
            textViewNum2.setText(String.valueOf(No2));
            textViewNum3.setText(String.valueOf(No3));
            textViewNum4.setText(String.valueOf(No4));
            textViewNum5.setText(String.valueOf(No5));
            textViewNum6.setText(String.valueOf(No6));
            textViewNum7.setText(String.valueOf(bonusNo));

            Toast.makeText(getApplicationContext(), "당첨번호를 가져왔습니다.",
                        Toast.LENGTH_LONG).show();

        }catch (JSONException e){
            e.printStackTrace();
        }
    }


}
