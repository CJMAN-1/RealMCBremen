package com.example.unit01;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;

public class Playlist_class extends MainActivity
{
    private ArrayList<MusicInfo> musics = new ArrayList<MusicInfo>();

    private ImageButton ALL_Del;
    private AlertDialog.Builder builder;
    private Intent intent;


    ArrayList tempList = new ArrayList();
    Long size;
    long pos;
    long tt, id, tim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        builder = new AlertDialog.Builder(this);
        ListView listview = (ListView)findViewById(R.id.list);
        ALL_Del = (ImageButton)findViewById(R.id.all_del) ;

        ALL_Del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle(R.string.delete)
                        .setMessage(R.string.message_delete)
                        .setIcon(R.drawable.girl)
                        .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which)
                            {
                                Log.d("PLAY", "onClick: 전부 삭제 ");
                                editor.clear();
                                noraeNum=0;
                                last=0;
                                for(int i=0; i < pos; i++){
                                    musics.remove(i);
                                }
                                finish();
                                intent = new Intent(Playlist_class.this, Playlist_class.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton(R.string.cancel, null)
                        .setCancelable(false)
                        .show();
            }
        });



        for(int i=0; i<noraeNum; i++){
            size = preferences.getLong("norae"+i+0,0);

            for(int j=0;j<size;j++){
                tt = preferences.getLong("norae"+i+(3*j +1),0);
                id = preferences.getLong("norae"+i+(3*j +2),0);
                tim = preferences.getLong("norae"+i+(3*j +3),0);
                Log.d("cjc3",""+tim);
                tempList.add(new tick((int)tt,(int)id,tim));
            }

            Log.d("cjc3","size:"+size);
            tick s = (tick)tempList.get((int)(size-1));
            Log.d("cjc3","=========="+i);
            if(tt == 2)
                musics.add(new MusicInfo(R.drawable.turned_piano, "PianoNorae "+i, (s.time/60000)+":"+(s.time/1000), tempList, R.id.play));
            else if(tt == 1)
                musics.add(new MusicInfo(R.drawable.guitar, "GuitarNorae "+i, (s.time/60000)+":"+(s.time/1000), tempList, R.id.play));
            else if(tt == 0)
                musics.add(new MusicInfo(R.drawable.maracas, "MaracasNorae "+i, (s.time/60000)+":"+(s.time/1000), tempList, R.id.play));
            tempList.clear();
        }


        listview.setAdapter(new PlayListAdapter(this, musics));
        backflag =1;
    }

    private class MusicInfo {
        int cover;
        String name;
        String play_time;
        ArrayList music;
        int play;

        MusicInfo(int cover, String name, String play_time, ArrayList m, int p) {
            this.cover = cover;
            this.name = name;
            this.play_time = play_time;
            this.music = (ArrayList) m.clone();
            this.play = p;
        }
    }
    private class PlayListAdapter extends ArrayAdapter<MusicInfo> {
        private final LayoutInflater inflater;
        PlayListAdapter(Context context, ArrayList<MusicInfo> list) {
            super(context, 0, list);
            inflater = LayoutInflater.from(context);
        }
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if(convertView == null) {
                convertView = inflater.inflate(R.layout.item, null);
                holder = new ViewHolder();
                holder.cover = (ImageView) convertView.findViewById(R.id.cover);
                holder.name = (TextView) convertView.findViewById(R.id.name);
                holder.playtime = (TextView) convertView.findViewById(R.id.play_time);
                holder.play = (ImageButton)convertView.findViewById(R.id.play);
                convertView.setTag(holder);
            }
            else {
                holder = (ViewHolder) convertView.getTag();
            }
            final MusicInfo pi = getItem(position);
            holder.cover.setImageResource(pi.cover);
            holder.name.setText(pi.name);
            holder.playtime.setText(pi.play_time);
            holder.play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    long pos = getItemId(position);
                    Log.d("PLAY", "onClick: 버튼 눌림 " + pos);
                    playArray = (ArrayList)pi.music.clone();
                    playThread.run();

                }
            });

            return convertView;
        }
    }
    private class ViewHolder {
        ImageView cover;
        TextView name;
        TextView playtime;
        ImageButton play;
    }
}
