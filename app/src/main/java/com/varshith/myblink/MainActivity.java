package com.varshith.myblink;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;

import com.pd.lookatme.LookAtMe;

public class MainActivity extends AppCompatActivity {
    private int CAMERA_PERMISSION_CODE = 23;
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    private Button reset;
    private LookAtMe lookAtMe;
    private Button pause;
    private VideoView videoView;
    private Button play;
    private EditText url;
    private Button ytplay;
    private Button resume;
    private int REQUEST_CODE=11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //videoView=findViewById(R.id.video_view);
        play=findViewById(R.id.play);
        //url=findViewById(R.id.url);
       // ytplay = findViewById(R.id.ytplay);
//        pause=findViewById(R.id.playstop);
//        resume=findViewById(R.id.resume);
//        reset=findViewById(R.id.reset);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                CAMERA_PERMISSION_CODE);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent,"Select a Video"),REQUEST_CODE);
            }
        });

//        ytplay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String yturl = url.getText().toString();
//                Intent intent=new Intent(MainActivity.this,youtubeplay.class);
//                intent.putExtra("yturl",yturl);
//                startActivity(intent);
//            }
//        });
    }


//    private void playVideo(Uri uri){
//        MediaController controller =new MediaController(MainActivity.this);
//        getSupportActionBar().hide();
//
//        lookAtMe = findViewById(R.id.lookme);
//        lookAtMe.init(this);
//        lookAtMe.setVideoURI(uri);
//        //  lookAtMe.setVideoPath("https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_640_3MG.mp4");// to use video from a url
//
//        lookAtMe.start();
//        lookAtMe.setLookMe();
//
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE){
            if(resultCode == RESULT_OK){
                if(data!=null){
                    Uri uri = data.getData();
                   // playVideo(uri);
                    Intent intent=new Intent(MainActivity.this,Player.class);
                    intent.putExtra("VideoUri", uri.toString());
                    startActivity(intent);
                }
            }
        }
    }
    public void onBackPressed(){
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);

    }
}