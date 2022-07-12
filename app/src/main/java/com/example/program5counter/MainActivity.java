package com.example.program5counter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public Button start ,stop;
    public TextView countervalue;
    public int counter=0;
    public  boolean running=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start=findViewById(R.id.start);
        stop=findViewById(R.id.stop);
        countervalue=findViewById(R.id.value);

    }
    public void start(View view){
     counter=0;
     running=true;
     new mycounter().start();
     start.setEnabled(false);
     stop.setEnabled(true);
    }

    public void stop(View view){
        this.running=false;
        start.setEnabled(true);
        stop.setEnabled(false);
    }


    Handler handler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message mes){
          countervalue.setText(String.valueOf(mes.what));
        }
    };

    class mycounter extends Thread {
        public void run(){
            while (running){
                counter++;
                handler.sendEmptyMessage(counter);
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}