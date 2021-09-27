package cn.itcast.myapplication;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {

    private Button btnPlayer;
    //private Button btnLive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initListener();

    }

    private void initListener() {
        btnPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
/*                Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
                startActivity(intent);*/
                Intent intent = new Intent(MainActivity.this,OpenFileActivity.class);
                startActivity(intent);

            }
        });
/*        btnLive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LiveActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initView() {
        btnPlayer = findViewById(R.id.btn_player_video);
        //btnLive = findViewById(R.id.btn_live);
    }

}