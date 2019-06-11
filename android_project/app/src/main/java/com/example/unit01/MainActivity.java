package com.example.unit01;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.Image;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends Activity implements View.OnClickListener {

    private ImageButton Exit;
    private ImageButton Piano;
    private ImageButton Guitar;
    private ImageButton Maracas;
    private ImageButton Playlist;

    private static final int Dial_Exit = 1;

    private Intent intent1;
    private Intent intent2;
    private Intent intent3;
    private Intent intent4;

    /////////////////////////////////////
    public int recordFlag;
    public Button assembleButton;
    public Button beatButton;
    public ImageButton playButton;
    public ImageButton recordButton;
    public long tStart=0;
    public long tEnd =0;
    public long temp1 =0;
    public long temp2 =0;
    public ArrayList timeArray = new ArrayList();
    public ArrayList playArray = new ArrayList();
    public ExampleThread playThread = new ExampleThread();

    public SoundPool maracas_pool;
    public int chaka1;
    public int chaka2;

    SoundPool guitar_pool;
    public int C1, C2, C3, C4, C5, C6, Am1, Am2, Am3, Am4, Am5, Am6, F1, F2, F3, F4, F5, F6, G1, G2, G3, G4, G5, G6;

    public static final int idC1=1, idC2=2, idC3=3, idC4=4, idC5=5, idC6=6, idAm1=7, idAm2=8, idAm3=9,
            idAm4=10, idAm5=11, idAm6=12, idF1=13, idF2=14, idF3=15, idF4=16, idF5=17, idF6=18, idG1=19,
            idG2=20, idG3=21, idG4=22, idG5=23, idG6=24;

    SoundPool piano_pool;
    int d1,re, mi, pa, sol, ra, si, dosharp, resharp, pasharp, solsharp, rasharp;
    public static final int idd1=1,idre=2, idmi=3, idpa=4, idsol=5, idra=6, idsi=7,
            iddosharp=8, idresharp=9, idpasharp=10, idsolsharp=11, idrasharp=12
            ;
    public int backflag =0;
    SharedPreferences preferences ;
    SharedPreferences.Editor editor;

    int noraeNum = 0;
    int last = 0;
    int just =0;
    //////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //여기 XML파일(activity_main)에 설정된대로 화면이 구성됨

        Exit = (ImageButton) findViewById(R.id.exit);
        Piano = (ImageButton) findViewById(R.id.piano);
        Guitar = (ImageButton) findViewById(R.id.guitar);
        Maracas = (ImageButton) findViewById(R.id.maracas);
        Playlist = (ImageButton) findViewById(R.id.playlist);

        Exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Exit");
                showDialog(Dial_Exit);
            }
        });
        Piano.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Piano");
                intent1 = new Intent(MainActivity.this, Piano_class.class);
                startActivity(intent1);
            }
        });
        Guitar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Guitar");
                intent2 = new Intent(MainActivity.this, Guitar_class.class);
                startActivity(intent2);
            }
        });
        Maracas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Maracas");
                intent3 = new Intent(MainActivity.this, Maracas_class.class);
                startActivity(intent3);
            }
        });
        Playlist.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Playlist");
                intent4 = new Intent(MainActivity.this, Playlist_class.class);
                startActivity(intent4);
            }
        });


        maracas_pool=new SoundPool(4, AudioManager.STREAM_MUSIC,0);

        chaka1=maracas_pool.load(this,R.raw.chaka_1,1);
        chaka2=maracas_pool.load(this,R.raw.chaka_2,1);
        //////////////////////////////////////////////////////////////////////
        //기타 소리 불러오기
        guitar_pool = new SoundPool(4, AudioManager.STREAM_MUSIC, 0);

        C1 = guitar_pool.load(this, R.raw.guitar_c1, 1);
        C2 = guitar_pool.load(this, R.raw.guitar_c2, 1);
        C3 = guitar_pool.load(this, R.raw.guitar_c3, 1);
        C4 = guitar_pool.load(this, R.raw.guitar_c4, 1);
        C5 = guitar_pool.load(this, R.raw.guitar_c5, 1);
        C6 = guitar_pool.load(this, R.raw.guitar_c6, 1);

        F1 = guitar_pool.load(this, R.raw.guitar_f1, 1);
        F2 = guitar_pool.load(this, R.raw.guitar_f2, 1);
        F3 = guitar_pool.load(this, R.raw.guitar_f3, 1);
        F4 = guitar_pool.load(this, R.raw.guitar_f4, 1);
        F5 = guitar_pool.load(this, R.raw.guitar_f5, 1);
        F6 = guitar_pool.load(this, R.raw.guitar_f6, 1);

        G1 = guitar_pool.load(this, R.raw.guitar_g1, 1);
        G2 = guitar_pool.load(this, R.raw.guitar_g2, 1);
        G3 = guitar_pool.load(this, R.raw.guitar_g3, 1);
        G4 = guitar_pool.load(this, R.raw.guitar_g4, 1);
        G5 = guitar_pool.load(this, R.raw.guitar_g5, 1);
        G6 = guitar_pool.load(this, R.raw.guitar_g6, 1);

        Am1 = guitar_pool.load(this, R.raw.guitar_a1, 1);
        Am2 = guitar_pool.load(this, R.raw.guitar_a2, 1);
        Am3 = guitar_pool.load(this, R.raw.guitar_a3, 1);
        Am4 = guitar_pool.load(this, R.raw.guitar_a4, 1);
        Am5 = guitar_pool.load(this, R.raw.guitar_a5, 1);
        Am6 = guitar_pool.load(this, R.raw.guitar_a6, 1);
        //기타 소리 불러오기
        //////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////
        //피아노 소리 불러오기
        piano_pool=new SoundPool(4, AudioManager.STREAM_MUSIC,0);
        d1 = piano_pool.load(this,R.raw.white2,1);
        re = piano_pool.load(this,R.raw.white3,1);
        mi = piano_pool.load(this,R.raw.white1,1);
        pa = piano_pool.load(this,R.raw.white4,1);
        sol = piano_pool.load(this,R.raw.white5,1);
        ra = piano_pool.load(this,R.raw.white6,1);
        si = piano_pool.load(this,R.raw.white7,1);
        dosharp = piano_pool.load(this,R.raw.black1,1);
        resharp = piano_pool.load(this,R.raw.black2,1);
        pasharp = piano_pool.load(this,R.raw.black3,1);
        solsharp = piano_pool.load(this,R.raw.black4,1);
        rasharp = piano_pool.load(this,R.raw.black5,1);
        //피아노 소리 불러오기
        //////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////////////////
        //shared preference


        preferences = getSharedPreferences("Bremen",MODE_PRIVATE);
        editor = preferences.edit();
        editor.clear();


        backflag =0;
    }


        @Override
        public  void onBackPressed(){
            super.onBackPressed();
            if(backflag ==0)
                showDialog(Dial_Exit);
        }


    @Override
    protected Dialog onCreateDialog(int id, Bundle args)
    {
        switch(id)
        {
            case Dial_Exit:
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.title)
                        .setMessage(R.string.message)
                        .setIcon(R.drawable.boy)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .show();
            }
        }
        return super.onCreateDialog(id, args);
    }



    //type 0: 마라카스 1: 기타 2: 피아노
    public class tick{
        public int type;
        public int id;
        public long time;
        public tick(int tt, int i, long t){
            type = tt;
            id = i;
            time = t;
        }
    }

    ///////////////////////////
    public class ExampleThread extends Thread {
        public static final String TAG = "ExampleThread";

        public ExampleThread() {
            // 초기화 작업
        }

        public void run() {
            // 스레드에게 수행시킬 동작들 구현
            int num = playArray.size();
            Log.d ("1record","재생:"+playArray.size());
            int i=0;
            temp1 = System.currentTimeMillis();
            tick temp3;
            while(true){
                Log.d ("1record","재생:"+i);
                if(i==num) {
                    Log.d ("1record","break:"+i);
                    break;
                }
                temp2 = System.currentTimeMillis() - temp1;
                temp3 = (tick) playArray.get(i);
                Log.d ("2record","type:"+temp3.type);
                if(temp2 >= temp3.time){
                    switch(temp3.type){
                        case 0://마라카스
                            Log.d ("2record","break:"+i);
                            maracas_pool.play(chaka1,1,1,0,0,1);
                            break;
                        case 1://기타
                            switch (temp3.id) {
                                case idC1:
                                    guitar_pool.play(C1, 1, 1, 0, 0, 1);
                                    break;
                                case idF1:
                                    guitar_pool.play(F1, 1, 1, 0, 0, 1);
                                    break;
                                case idG1:
                                    guitar_pool.play(G1, 1, 1, 0, 0, 1);
                                    break;
                                case idAm1:
                                    guitar_pool.play(Am1, 1, 1, 0, 0, 1);
                                    break;
                                case idC2:
                                    guitar_pool.play(C2, 1, 1, 0, 0, 1);
                                    break;
                                case idF2:
                                    guitar_pool.play(F2, 1, 1, 0, 0, 1);
                                    break;
                                case idG2:
                                    guitar_pool.play(G2, 1, 1, 0, 0, 1);
                                    break;
                                case idAm2:
                                    guitar_pool.play(Am2, 1, 1, 0, 0, 1);
                                    break;
                                case idC3:
                                    guitar_pool.play(C3, 1, 1, 0, 0, 1);
                                    break;
                                case idF3:
                                    guitar_pool.play(F3, 1, 1, 0, 0, 1);
                                    break;
                                case idG3:
                                    guitar_pool.play(G3, 1, 1, 0, 0, 1);
                                    break;
                                case idAm3:
                                    guitar_pool.play(Am3, 1, 1, 0, 0, 1);
                                    break;
                                case idC4:
                                    guitar_pool.play(C4, 1, 1, 0, 0, 1);
                                    break;
                                case idF4:
                                    guitar_pool.play(F4, 1, 1, 0, 0, 1);
                                    break;
                                case idG4:
                                    guitar_pool.play(G4, 1, 1, 0, 0, 1);
                                    break;
                                case idAm4:
                                    guitar_pool.play(Am4, 1, 1, 0, 0, 1);
                                    break;
                                case idC5:
                                    guitar_pool.play(C5, 1, 1, 0, 0, 1);
                                    break;
                                case idF5:
                                    guitar_pool.play(F5, 1, 1, 0, 0, 1);
                                    break;
                                case idG5:
                                    guitar_pool.play(G5, 1, 1, 0, 0, 1);
                                    break;
                                case idAm5:
                                    guitar_pool.play(Am5, 1, 1, 0, 0, 1);
                                    break;
                                case idC6:
                                    guitar_pool.play(C6, 1, 1, 0, 0, 1);
                                    break;
                                case idF6:
                                    guitar_pool.play(F6, 1, 1, 0, 0, 1);
                                    break;
                                case idG6:
                                    guitar_pool.play(G6, 1, 1, 0, 0, 1);
                                    break;
                                case idAm6:
                                    guitar_pool.play(Am6, 1, 1, 0, 0, 1);
                                    break;
                            }
                            break;
                        case 2://피아노
                            switch(temp3.id) {
                                case idd1:
                                    piano_pool.play(d1, 1, 1, 0, 0, 1);
                                    break;
                                case idsi:
                                    piano_pool.play(si, 1, 1, 0, 0, 1);
                                    break;
                                case idra:
                                    piano_pool.play(ra, 1, 1, 0, 0, 1);
                                    break;
                                case idsol:
                                    piano_pool.play(sol, 1, 1, 0, 0, 1);
                                    break;
                                case idpa:
                                    piano_pool.play(pa, 1, 1, 0, 0, 1);
                                    break;
                                case idmi:
                                    piano_pool.play(mi, 1, 1, 0, 0, 1);
                                    break;
                                case idre:
                                    piano_pool.play(re, 1, 1, 0, 0, 1);
                                    break;
                                case iddosharp:
                                    piano_pool.play(dosharp, 1, 1, 0, 0, 1);
                                    break;
                                case idresharp:
                                    piano_pool.play(resharp, 1, 1, 0, 0, 1);
                                    break;
                                case idpasharp:
                                    piano_pool.play(pasharp, 1, 1, 0, 0, 1);
                                    break;
                                case idsolsharp:
                                    piano_pool.play(solsharp, 1, 1, 0, 0, 1);
                                    break;
                                case idrasharp:
                                    piano_pool.play(rasharp, 1, 1, 0, 0, 1);
                                    break;
                            }
                            break;
                    }
                    i++;
                    Log.d ("1record","i++:"+i);
                }

            }
            playArray.clear();
        }
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.record:
                if(recordFlag ==0){
                    tStart= System.currentTimeMillis();
                    recordFlag = 1;
                    recordButton.setImageResource(R.drawable.boy);
                    break;
                }
                else {
                    tEnd= System.currentTimeMillis();
                    recordFlag = 0;
                    recordButton.setImageResource(R.drawable.button_record);

                    int size = timeArray.size();

                    editor.putLong("norae"+last+0 , size);
                    for(int k=0;k<size;k++){

                        tick ttemp = (tick)timeArray.get(k);

                        editor.putLong("norae"+last+(3*k +1) , ttemp.type);
                        editor.putLong("norae"+last+(3*k +2) , ttemp.id);
                        editor.putLong("norae"+last+(3*k +3) , ttemp.time);
                    }
                    editor.commit();
                    noraeNum++;
                    last++;
                    break;
                }

            case R.id.play:


                playThread.run();

                break;
        }
    }
////////////////////////////////
}

