package com.varshith.myblink;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.pd.lookatme.LookAtMe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Timer;
import java.util.TimerTask;

public class Player extends AppCompatActivity {
    private LookAtMe lookAtMe;
    ImageView btnpause,btnplay,btnreplay;

    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        btnpause= (ImageView) findViewById(R.id.pause);
        btnplay=(ImageView)findViewById(R.id.play);
        btnreplay=(ImageView)findViewById(R.id.restart) ;
        Bundle extras = getIntent().getExtras();
        Uri myUri = Uri.parse(extras.getString("VideoUri"));
        lookAtMe = findViewById(R.id.lookme);
        lookAtMe.init(this);
        lookAtMe.setVideoURI(myUri);
        //  lookAtMe.setVideoPath("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_640_3MG.mp4");// to use video from a url

        lookAtMe.start();
        lookAtMe.canSeekBackward();
        lookAtMe.setLookMe();

        btnpause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag==0) {
                    lookAtMe.pause();
                    lookAtMe.stopPlayback();
                    lookAtMe.destroy();
                    flag=1;

                }
                else{
                    Toast.makeText(Player.this, "Video is Already paused Manually", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;



                lookAtMe.init(Player.this);
                lookAtMe.setVideoURI(myUri);
                lookAtMe.resume();
                lookAtMe.canSeekBackward();
                lookAtMe.setLookMe();
            }
        });
        btnreplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag=0;
                lookAtMe.init(Player.this);
                lookAtMe.setVideoURI(myUri);
                lookAtMe.start();
                lookAtMe.canSeekBackward();
                lookAtMe.setLookMe();
            }
        });
    }
}