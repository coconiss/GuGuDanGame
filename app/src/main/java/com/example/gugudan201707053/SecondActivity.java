package com.example.gugudan201707053;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private static final int START = 1;
    private static final int RESTART = 2;
    private static final int EXIT = 3;

    TextView textView1, textView2, textView3;
    ProgressBar pb;
    int nResult;
    int nu=0;
    String anw="";
    private Button button1,button2,button3,button4,button5,button6,button7,button8,button9,button0,button_ca,button_en;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent i = getIntent();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        pb = (ProgressBar)findViewById(R.id.progressbar);
        pb.setMax(60);
        pb.setProgress(0);

        textView1 = (TextView)findViewById(R.id.tx_quest);//문제출제
        textView2 = (TextView) findViewById(R.id.tx_answer);//정답입력
        textView3 = (TextView) findViewById(R.id.tx_num);//정답횟수


        button0 = (Button)findViewById(R.id.btn0);
        button1 = (Button)findViewById(R.id.btn1);
        button2 = (Button)findViewById(R.id.btn2);
        button3 = (Button)findViewById(R.id.btn3);
        button4 = (Button)findViewById(R.id.btn4);
        button5 = (Button)findViewById(R.id.btn5);
        button6 = (Button)findViewById(R.id.btn6);
        button7 = (Button)findViewById(R.id.btn7);
        button8 = (Button)findViewById(R.id.btn8);
        button9 = (Button)findViewById(R.id.btn9);
        button_ca = (Button)findViewById(R.id.button_can);
        button_en = (Button)findViewById(R.id.button_en);
        makeQuiz();

        handler = new Handler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                switch (msg.what){
                    case START:
                        if(pb.getProgress()<60) {
                            pb.incrementProgressBy(1);
                            sendEmptyMessageDelayed(START,1000);
                        }else if(pb.getProgress() >= 60){
                            sendEmptyMessage(EXIT);
                        }
                }

                if(msg.what==RESTART){
                    pb.setProgress(0);
                    anw="";
                    textView2.setText(anw);
                    nu=0;
                    textView3.setText(String.valueOf(nu));
                    sendEmptyMessage(START);
                }else if(msg.what==EXIT){
                    pb.setProgress(0);
                    Intent i = getIntent();
                    i.putExtra("nu",nu);
                    setResult(RESULT_OK,i);
                    finish();
                }
            }
        };

        handler.sendEmptyMessage(START);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="1";
                textView2.setText(anw);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="2";
                textView2.setText(anw);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="3";
                textView2.setText(anw);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="4";
                textView2.setText(anw);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="5";
                textView2.setText(anw);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="6";
                textView2.setText(anw);
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="7";
                textView2.setText(anw);
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="8";
                textView2.setText(anw);
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="9";
                textView2.setText(anw);
            }
        });
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw +="0";
                textView2.setText(anw);
            }
        });
        button_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                anw="";
                textView2.setText(anw);
            }
        });
        button_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strAnswer = textView2.getText().toString();
                int answer = Integer.parseInt(strAnswer);
                if(answer == nResult){
                    nu=nu+1;
                    textView3.setText(String.valueOf(nu));
                    anw="";
                    textView2.setText(anw);
                    makeQuiz();
                }
                else {
                    anw="";
                    textView2.setText(anw);
                    makeQuiz();
                }
            }
        });
    }
    public void makeQuiz() {
        int left = getRandom (8, 2);
        int right = getRandom (9, 1);
        textView1.setText(left + " * " + right + " = ?");
        nResult = left * right;
    }
    public int getRandom (int max, int offset) {
        int nResult = (int)(Math.random() * max) + offset;
        return nResult;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Intent intent = new Intent(this, SecondActivity.class);
        switch (item.getItemId()) {
            case R.id.exit:
                handler.sendEmptyMessage(EXIT);
                break;

            case R.id.restart:
                handler.removeMessages(START);
                handler.sendEmptyMessage(RESTART);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu2, menu);
        return super.onCreateOptionsMenu(menu);
    }
}