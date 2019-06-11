package com.example.unit01;

import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

public class Piano_class extends MainActivity {


    private ImageButton PianoChange;
    private ImageButton GuitarChange;
    private ImageButton MaracasChange;
    private Intent intent1;
    private Intent intent2;
    private Intent intent3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piano);

        recordButton = (ImageButton) findViewById(R.id.record);
        playButton = (ImageButton) findViewById(R.id.play);
        recordButton.setOnClickListener(this);
        playButton.setOnClickListener(this);

        PianoChange = (ImageButton)findViewById(R.id.piano_change2);
        GuitarChange = (ImageButton)findViewById(R.id.guitar_change2);
        MaracasChange = (ImageButton)findViewById(R.id.maracas_change2);


        PianoChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Piano Changed");
                intent1 = new Intent(Piano_class.this, Piano_class.class);
                startActivity(intent1);
                finish();
            }
        });
        GuitarChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Guitar Changed");
                intent2 = new Intent(Piano_class.this, Guitar_class.class);
                startActivity(intent2);
                finish();
            }
        });
        MaracasChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.d("Touch", "onClick: Maracas Changed");
                intent3 = new Intent(Piano_class.this, Maracas_class.class);
                startActivity(intent3);
                finish();
            }
        });
        backflag =1;
    }
    public void mOnClick(View v){

        switch(v.getId())
        {
            case R.id.button_do:
                piano_pool.play(d1,1,1,0,0,1);
                break;
            case R.id.button_si:
                piano_pool.play(si,1,1,0,0,1);
                break;
            case R.id.button_ra:
                piano_pool.play(ra,1,1,0,0,1);
                break;
            case R.id.button_sol:
                piano_pool.play(sol,1,1,0,0,1);
                break;
            case R.id.button_pa:
                piano_pool.play(pa,1,1,0,0,1);
                break;
            case R.id.button_mi:
                piano_pool.play(mi,1,1,0,0,1);
                break;
            case R.id.button_re:
                piano_pool.play(re,1,1,0,0,1);
                break;
            case R.id.button_b1:
                piano_pool.play(dosharp,1,1,0,0,1);
                break;
            case R.id.button_b2:
                piano_pool.play(resharp,1,1,0,0,1);
                break;
            case R.id.button_b3:
                piano_pool.play(pasharp,1,1,0,0,1);
                break;
            case R.id.button_b4:
                piano_pool.play(solsharp,1,1,0,0,1);
                break;
            case R.id.button_b5:
                piano_pool.play(rasharp,1,1,0,0,1);
                break;
            case R.id.piano_change2:
            Log.d("Touch", "onClick: Piano Changed");
            intent1 = new Intent(Piano_class.this, Piano_class.class);
            startActivity(intent1);
            finish();
            break;
            case R.id.guitar_change2:
                Log.d("Touch", "onClick: Piano Changed");
                intent1 = new Intent(Piano_class.this, Guitar_class.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.maracas_change2:
                Log.d("Touch", "onClick: Piano Changed");
                intent1 = new Intent(Piano_class.this, Maracas_class.class);
                startActivity(intent1);
                finish();
                break;
        }

        if(recordFlag ==1){
            tEnd= System.currentTimeMillis();
            switch(v.getId())
            {
                case R.id.button_do:
                    timeArray.add(new tick(2,idd1,(tEnd-tStart)));
                    break;
                case R.id.button_si:
                    timeArray.add(new tick(2,idsi,(tEnd-tStart)));
                    break;
                case R.id.button_ra:
                    timeArray.add(new tick(2,idra,(tEnd-tStart)));
                    break;
                case R.id.button_sol:
                    timeArray.add(new tick(2,idsol,(tEnd-tStart)));
                    break;
                case R.id.button_pa:
                    timeArray.add(new tick(2,idpa,(tEnd-tStart)));
                    break;
                case R.id.button_mi:
                    timeArray.add(new tick(2,idmi,(tEnd-tStart)));
                    break;
                case R.id.button_re:
                    timeArray.add(new tick(2,idre,(tEnd-tStart)));
                    break;
                case R.id.button_b1:
                    timeArray.add(new tick(2,iddosharp,(tEnd-tStart)));
                    break;
                case R.id.button_b2:
                    timeArray.add(new tick(2,idresharp,(tEnd-tStart)));
                    break;
                case R.id.button_b3:
                    timeArray.add(new tick(2,idpasharp,(tEnd-tStart)));
                    break;
                case R.id.button_b4:
                    timeArray.add(new tick(2,idsolsharp,(tEnd-tStart)));
                    break;
                case R.id.button_b5:
                    timeArray.add(new tick(2,idrasharp,(tEnd-tStart)));
                    break;
            }
            Log.d ("1record",""+timeArray.size());
        }
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 29) {                //키코드 A
            piano_pool.play(d1,1,1,0,0,1);
        }
        else if (keyCode == 32) {           //키코드 D
            piano_pool.play(si,1,1,0,0,1);
        }
        else if (keyCode == 34) {           //키코드 F
            piano_pool.play(ra,1,1,0,0,1);
        }
        else if (keyCode == 35) {           //키코드 G
            piano_pool.play(sol,1,1,0,0,1);
        }
        else if (keyCode == 36) {           //키코드 H
            piano_pool.play(pa,1,1,0,0,1);
        }
        else if (keyCode == 38) {           //키코드 J
            piano_pool.play(mi,1,1,0,0,1);
        }
        else if (keyCode == 51) {           //키코드 W
            piano_pool.play(re,1,1,0,0,1);
        }
        else if (keyCode == 33) {           //키코드 E
            piano_pool.play(re,1,1,0,0,1);
        }
        else if (keyCode == 48) {           //키코드 T
            piano_pool.play(si,1,1,0,0,1);
        }
        else if (keyCode == 53) {           //키코드 Y
            piano_pool.play(ra,1,1,0,0,1);
        }
        else if (keyCode == 49) {           //키코드 U
            piano_pool.play(pa,1,1,0,0,1);
        }
        else if (keyCode == 62 ) {          //키코드 SPACE BAR
            piano_pool.play(mi,1,1,0,0,1);
        }
        else if(keyCode == event.KEYCODE_BACK){
            Log.d("백키", "onKeyDown: 종료");
            super.onBackPressed();
        }

        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }
}
