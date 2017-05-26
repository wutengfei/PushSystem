package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;

import cn.cnu.pushsystem.R;


public class VideoActivity extends AppCompatActivity {
    private VideoView videoView;
    MediaController mediaController;
    String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        getSupportActionBar().hide();
        videoView = (VideoView) findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        videoPath = intent.getStringExtra("videoPath");
        initVideoPath();
    }

    private void initVideoPath() {
        File file = new File(Environment.getExternalStorageDirectory() + "/Android/data/" +
                getApplication().getPackageName() + File.separator + "resource/video", "lake.avi");
        videoPath = file.getAbsolutePath();
        videoView.setVideoPath(videoPath); // 指定视频文件的路径
        System.out.println(videoPath);

        //VideoView与MediaController进行关联
        videoView.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoView);
        videoView.requestFocus();//让VideoView获取焦点
        videoView.start(); // 开始播放
        // 增加监听上一个和下一个的切换事件，默认这两个按钮是不显示的
        mediaController.setPrevNextListeners(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(VideoActivity.this, "下一个", Toast.LENGTH_LONG).show();
            }
        }, new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(VideoActivity.this, "上一个", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (videoView != null) {
            videoView.suspend();//将 VideoView 所占用的资源释放掉。
        }
    }
}
