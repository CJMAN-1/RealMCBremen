package com.example.unit01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.support.v4.widget.DrawerLayout;
import android.view.*;
import android.widget.TextView;

import java.util.ArrayList;

public class Maracas_class extends MainActivity implements SensorEventListener, View.OnClickListener {
    private SensorManager sensorManager;
    private float[] accelerometer = new float[3];
    private float[] pre_sensor = new float[2];
    private float area1;

    private ImageButton PianoChange;
    private ImageButton GuitarChange;
    private ImageButton MaracasChange;
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;

    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maracas);      //잘 바꾸자 제발

        img = (ImageView)findViewById(R.id.mara);

        sensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
        Sensor accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);

        recordButton = (ImageButton)findViewById(R.id.record);
        playButton = (ImageButton)findViewById(R.id.play);

        recordButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        ////////////////////
      
        PianoChange = (ImageButton)findViewById(R.id.piano_change3);
        GuitarChange = (ImageButton)findViewById(R.id.guitar_change3);
        MaracasChange = (ImageButton)findViewById(R.id.maracas_change3);

        PianoChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Piano Changed");
                intent1 = new Intent(Maracas_class.this, Piano_class.class);
                startActivity(intent1);
                finish();
            }
        });
        GuitarChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Guitar Changed");
                intent2 = new Intent(Maracas_class.this, Guitar_class.class);
                startActivity(intent2);
                finish();
            }
        });
        MaracasChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Maracas Changed");
                intent3 = new Intent(Maracas_class.this, Maracas_class.class);
                startActivity(intent3);
                finish();
            }
        });

        editor.clear();
        backflag =1;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch(event.sensor.getType())
        {
            case Sensor.TYPE_ACCELEROMETER:
                accelerometer[0] = event.values[0];
                accelerometer[1] = event.values[1];
                accelerometer[2] = event.values[2];
                break;
        }

        area1 = accelerometer[0] - pre_sensor[0];
        if(area1  > 10){
            Log.d("swing", "onSensorChanged: 1번");
            img.setImageResource(R.drawable.mara2);
            maracas_pool.play(chaka1,1,1,0,0,1);
            area1 = 0;
            /////////////////////////////////
            if(recordFlag ==1){
                tEnd= System.currentTimeMillis();
                timeArray.add(new tick(0,0,(tEnd-tStart)));
                Log.d ("1record",""+timeArray.size());
            }
        }
        else {
            Log.d("swing", "onSensorChanged: 없음");
            img.setImageResource(R.drawable.mara1);
        }
        pre_sensor[0] = accelerometer[0];
        pre_sensor[1] = accelerometer[1];
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }


}
