package com.example.unit01;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ListView listview = (ListView)findViewById(R.id.list);
        for(int i=0; i<100; i++)
            musics.add(new MusicInfo(R.drawable.music, "갑돌이", "4"));

        listview.setAdapter(new PlayListAdapter(this, musics));
        backflag =1;
    }

    private class MusicInfo {
        int cover;
        String name;
        String play_time;
        MusicInfo(int cover, String name, String play_time) {
            this.cover = cover;
            this.name = name;
            this.play_time = play_time;
        }
    }
    private class PlayListAdapter extends ArrayAdapter<MusicInfo> {
        private final LayoutInflater inflater;
        PlayListAdapter(Context context, ArrayList<MusicInfo> list) {
            super(context, 0, list);
            inflater = LayoutInflater.from(context);
        }
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                ViewHolder holder;
                if(convertView == null) {
                    convertView = inflater.inflate(R.layout.item, null);
                    holder = new ViewHolder();
                    holder.cover = (ImageView)convertView.findViewById(R.id.cover);
                    holder.name = (TextView)convertView.findViewById(R.id.name);
                    holder.playtime = (TextView)convertView.findViewById(R.id.play_time);
                    convertView.setTag(holder);
                }
                else {
                    holder = (ViewHolder) convertView.getTag();
                }        final MusicInfo pi = getItem(position);
                holder.cover.setImageResource(pi.cover);
                holder.name.setText(pi.name);
                holder.playtime.setText(pi.play_time);
                return convertView;
        }
    }
    private class ViewHolder {
        ImageView cover;
        TextView name;
        TextView playtime;
    }
}

