package com.varshith.myblink;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;

import com.pd.lookatme.LookAtMe;

public class youtubeplay extends AppCompatActivity {

    private LookAtMe lookAtMe;
    MediaController mediaControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        lookAtMe = findViewById(R.id.lookme);
        Bundle extras = getIntent().getExtras();
       // Uri myUri = Uri.parse(extras.getString("VideoUri"));
        String yturl = extras.getString("yturl");
        if (mediaControls == null) {
            // create an object of media controller class
            mediaControls = new MediaController(youtubeplay.this);
            mediaControls.setAnchorView(lookAtMe);
        }

        lookAtMe.init(this);
        //lookAtMe.setVideoURI(myUri);
        lookAtMe = findViewById(R.id.lookme);
        lookAtMe.setVideoPath("https://www.appsloveworld.com/wp-content/uploads/2018/10/640.mp4");// to use video from a url

        lookAtMe.start();
        lookAtMe.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Toast.makeText(getApplicationContext(), "Thank You...!!!", Toast.LENGTH_LONG).show(); // display a toast when an video is completed
            }
        });
        lookAtMe.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Toast.makeText(getApplicationContext(), "Oops An Error Occur While Playing Video...!!!", Toast.LENGTH_LONG).show(); // display a toast when an error is occured while playing an video
                return false;
            }
        });
        lookAtMe.setLookMe();

    }
}