package com.example.unit01;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.TextView;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;

import java.util.ArrayList;


public class Guitar_class extends MainActivity  {



    private float y;
    private float pre_y;
    private int code = 0;
    private int area = 100;
    private ImageButton C;
    private ImageButton F;
    private ImageButton G;
    private ImageButton Am;
    private ImageButton PianoChange;
    private ImageButton GuitarChange;
    private ImageButton MaracasChange;
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;


    /////
    private Context mContext;
    private DrawerLayout drawerLayout;
    private View drawerView;
    private ImageButton buttonCloseDrawer;
    private LinearLayout instrumentView;
    private PopupWindow mPopupWindow;
    private LinearLayout mLinearlayout;

    ///
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guitar); //여기 XML파일(activity_main)에 설정된대로 화면이 구성됨


        //////////////////////////////////////////////////////////////////////
        //뷰 연결 , 오른쪽 drawer 버튼 연결
        mContext = getApplicationContext();
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerView = (View) findViewById(R.id.drawer);
        instrumentView = (LinearLayout) findViewById(R.id.instrument_layout);
        mLinearlayout = (LinearLayout) findViewById(R.id.instrument_layout);

        recordButton = (ImageButton) findViewById(R.id.record);
        playButton = (ImageButton) findViewById(R.id.play);
        recordButton.setOnClickListener(this);
        playButton.setOnClickListener(this);
        //뷰 연결 , 오른쪽 drawer 버튼 연결
        //////////////////////////////////////////////////////////////////////

        /* 이거 지우면 안돼요~
        buttonCloseDrawer = (ImageButton) findViewById(R.id.play);
        buttonCloseDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.custom_layout, null);
                mPopupWindow = new PopupWindow(
                        customView,
                        RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                if (Build.VERSION.SDK_INT >= 22) {
                    mPopupWindow.setElevation(5.0f);
                }

                ImageButton closeButton = (ImageButton) customView.findViewById(R.id.ib_close);

                // Set a click listener for the popup window close button
                closeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Dismiss the popup window
                        mPopupWindow.dismiss();
                    }
                });

                mPopupWindow.showAtLocation(mLinearlayout, Gravity.CENTER, 0, 0);

            }
        });*/

        //////////////////////////////////////////////////////////////////////
        //adjust touch event
        drawerLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        drawerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Guitar_class.super.onTouchEvent(event);
                return true;
            }
        });

        //instrument touch event
        mLinearlayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                y = event.getRawY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        Log.d("Touch", "onTouch: ACTION Down");

                        break;
                    case MotionEvent.ACTION_MOVE:
                        Log.d("Touch", "onTouch: ACTION MOVE");
                        Log.d("Touch", "onTouch: y" + y);
                        if (Math.abs(y - pre_y) > 150) {
                            if (Math.abs(y - 280) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C1, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F1, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G1, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am1, 1, 1, 0, 0, 1);
                                        break;
                                }

                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC1,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF1,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG1,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm1,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            else if (Math.abs(y - 450) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C2, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F2, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G2, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am2, 1, 1, 0, 0, 1);
                                        break;
                                }

                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC2,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF2,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG2,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm2,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            else if (Math.abs(y - 650) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C3, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F3, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G3, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am3, 1, 1, 0, 0, 1);
                                        break;
                                }

                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC3,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF3,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG3,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm3,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            else if (Math.abs(y - 800) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C4, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F4, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G4, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am4, 1, 1, 0, 0, 1);
                                        break;
                                }

                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC4,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF4,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG4,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm4,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            else if (Math.abs(y - 960) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C5, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F5, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G5, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am5, 1, 1, 0, 0, 1);
                                        break;
                                }

                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC5,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF5,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG5,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm5,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            else if (Math.abs(y - 1130) < area) {
                                switch (code) {
                                    case 0:
                                        guitar_pool.play(C6, 1, 1, 0, 0, 1);
                                        break;
                                    case 1:
                                        guitar_pool.play(F6, 1, 1, 0, 0, 1);
                                        break;
                                    case 2:
                                        guitar_pool.play(G6, 1, 1, 0, 0, 1);
                                        break;
                                    case 3:
                                        guitar_pool.play(Am6, 1, 1, 0, 0, 1);
                                        break;
                                }
                                if(recordFlag ==1){
                                    tEnd= System.currentTimeMillis();
                                    switch (code) {
                                        case 0:
                                            timeArray.add(new tick(1,idC6,(tEnd-tStart)));
                                            break;
                                        case 1:
                                            timeArray.add(new tick(1,idF6,(tEnd-tStart)));
                                            break;
                                        case 2:
                                            timeArray.add(new tick(1,idG6,(tEnd-tStart)));
                                            break;
                                        case 3:
                                            timeArray.add(new tick(1,idAm6,(tEnd-tStart)));
                                            break;
                                    }
                                    Log.d ("1record",""+timeArray.size());
                                }
                            }
                            pre_y = y;
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        Log.d("Touch", "onTouch: ACTION UP");
                        pre_y = 0;
                        break;
                }
                return true;
            }
        });
        //adjust touch event
        //////////////////////////////////////////////////////////////////////


        //////////////////////////////////////////////////////////////////////
        //코드 버튼
        C = (ImageButton)findViewById(R.id.code_c);
        F = (ImageButton)findViewById(R.id.code_f);
        G = (ImageButton)findViewById(R.id.code_g);
        Am = (ImageButton)findViewById(R.id.code_am);

        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: C");
                C.setImageResource(R.drawable.code_c_push);
                F.setImageResource(R.drawable.code_f);
                G.setImageResource(R.drawable.code_g);
                Am.setImageResource(R.drawable.code_am);
                code = 0;
            }
        });
        F.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: F");
                C.setImageResource(R.drawable.code_c);
                F.setImageResource(R.drawable.code_f_push);
                G.setImageResource(R.drawable.code_g);
                Am.setImageResource(R.drawable.code_am);
                code = 1;
            }
        });
        G.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: G");
                C.setImageResource(R.drawable.code_c);
                F.setImageResource(R.drawable.code_f);
                G.setImageResource(R.drawable.code_g_push);
                Am.setImageResource(R.drawable.code_am);
                code = 2;
            }
        });
        Am.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Am");
                C.setImageResource(R.drawable.code_c);
                F.setImageResource(R.drawable.code_f);
                G.setImageResource(R.drawable.code_g);
                Am.setImageResource(R.drawable.code_am_push);
                code = 3;
            }
        });
        //코드 버튼
        //////////////////////////////////////////////////////////////////////

        //////////////////////////////////////////////////////////////////////
        //왼쪽 drawer 버튼
        PianoChange = (ImageButton)findViewById(R.id.piano_change);
        GuitarChange = (ImageButton)findViewById(R.id.guitar_change);
        MaracasChange = (ImageButton)findViewById(R.id.maracas_change);

        PianoChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Piano Changed");
                intent1 = new Intent(Guitar_class.this, Piano_class.class);
                startActivity(intent1);
                finish();
            }
        });
        GuitarChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Guitar Changed");
                intent2 = new Intent(Guitar_class.this, Guitar_class.class);
                startActivity(intent2);
                finish();
            }
        });
        MaracasChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Maracas Changed");
                intent3 = new Intent(Guitar_class.this, Maracas_class.class);
                startActivity(intent3);
                finish();
            }
        });
        //왼쪽 drawer 버튼
        //////////////////////////////////////////////////////////////////////




        backflag =1;
    }
    //End of onCreate

}
