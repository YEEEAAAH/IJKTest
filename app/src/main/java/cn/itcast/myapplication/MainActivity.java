package cn.itcast.myapplication;
import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import java.io.IOException;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class MainActivity extends AppCompatActivity {


    private static final int REQUEST_CODE_FOR_SINGLE_FILE = 1;
    private static final String TAG = "OpenFileActivity";
    public static final String VIDEO_NAME = "VIDEO_NAME";
    public static final String VIDEO_URI = "VIDEO_URI";
    private final String[] VIDEO_PROJECTION = {
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media._ID };
    String displayName;
    Uri uri = null;
    String uri_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_file);
        //通过系统的文件浏览器选择一个文件
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        //筛选，只显示可以“打开”的结果，如文件(而不是联系人或时区列表)
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //过滤只显示图像类型文件
        intent.setType("video/*");
        Log.d(TAG, "onCreate: "+"running normally");
        startActivityForResult(intent, REQUEST_CODE_FOR_SINGLE_FILE);



    }




    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SINGLE_FILE && resultCode == Activity.RESULT_OK) {
            Log.d(TAG, "onActivityResult: " + "running normally");

            if (data != null) {
                // 获取选择文件Uri
                uri = data.getData();
                // 获取图片信息
                Cursor cursor = this.getContentResolver()
                        .query(uri, VIDEO_PROJECTION, null, null, null, null);

                if (cursor != null && cursor.moveToFirst()) {
                    displayName = cursor.getString(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[0]));
                    String size = cursor.getString(cursor.getColumnIndexOrThrow(VIDEO_PROJECTION[1]));
                    Log.i(TAG, "Uri: " + uri.toString());
                    Log.i(TAG, "Name: " + displayName);
                    Log.i(TAG, "Size: " + size);
                }
                cursor.close();
            }

        }

        findViewById(R.id.check_file).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(uri!=null){
                    Intent intent = new Intent(MainActivity.this,MediaPlayerActivity.class);

                    intent.putExtra(VIDEO_NAME,displayName);

                    intent.putExtra(VIDEO_URI,uri.toString());
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


}