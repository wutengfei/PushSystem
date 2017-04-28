package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.cnu.pushsystem.R;
import cn.cnu.pushsystem.dao.DBOpenHelper;
import cn.cnu.pushsystem.entity.Tb_word;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView left_top;
    ImageView right_top;
    ImageView left_bottom;
    ImageView right_bottom;
    private TextView textView;
    String content;
    private DBOpenHelper helper;
    private SQLiteDatabase db;
    private android.media.MediaPlayer mediaPlayer = new android.media.MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        getSupportActionBar().hide();
        textView = (TextView) findViewById(R.id.textView);
        left_top = (ImageView) findViewById(R.id.left_top);
        right_top = (ImageView) findViewById(R.id.right_top);
        left_bottom = (ImageView) findViewById(R.id.left_bottom);
        right_bottom = (ImageView) findViewById(R.id.right_bottom);

        left_top.setImageDrawable(getResources().getDrawable(R.drawable.text));
        right_top.setImageDrawable(getResources().getDrawable(R.drawable.audbtn));
        left_bottom.setImageDrawable(getResources().getDrawable(R.drawable.video));
        right_bottom.setImageDrawable(getResources().getDrawable(R.drawable.picbtn));

        //把单词对应的资源查询出来显示
        helper = new DBOpenHelper(this);
        db = helper.getWritableDatabase();
        String name = "lake";
        Cursor cursor = db.query("word", new String[]{"name", "picturePath", "pronunciationPath","vedioPath1"},
                "name like ? ", new String[]{name}, null, null, null, null);
        Tb_word word = new Tb_word();
        if (cursor.moveToFirst()) {
            word.setPicturePath(cursor.getString(cursor.getColumnIndex("picturePath")));
            word.setPronunctionPath(cursor.getString(cursor.getColumnIndex("pronunciationPath")));
            word.setVedioPath1(cursor.getString(cursor.getColumnIndex("vedioPath1")));
        }
        //图片路径
        String picturePath = Environment.getExternalStorageDirectory() + File.separator + "Android/data/" +
                getApplication().getPackageName() + File.separator + "resource/" + word.getPicturePath();
        System.out.println("picturePath:"+picturePath);
        Bitmap bitmap = getLoacalBitmap(picturePath); //从本地取图片
        left_top.setImageBitmap(bitmap);

        //音频路径
        String pronunciationPath = Environment.getExternalStorageDirectory() + File.separator + "Android/data/" +
                getApplication().getPackageName() + File.separator + "resource/" + word.getPronunctionPath();
        System.out.println("pronunciationPath:"+pronunciationPath);

        //视频路径
        String videoPath = Environment.getExternalStorageDirectory() + File.separator + "Android/data/" +
                getApplication().getPackageName() + File.separator + "resource/" + word.getVedioPath1();
        System.out.println("getVedioPath1:"+videoPath);

        //把压缩包中xml文件里含有的所有单词提取出来，放入数组
        Cursor cursor2 = db.query("word", new String[]{"name"},
                null, null, null, null, null);
        Tb_word[] words = new Tb_word[cursor.getCount()];
        for (int i = 0; i < cursor.getCount(); i++) {
            if (cursor.moveToFirst()) {
                words[i] = new Tb_word();
                words[i].setName(cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
        }
        cursor.close();

        //初始化MediaPlayer
        try {
            File file = new File(pronunciationPath);
            mediaPlayer.setDataSource(file.getPath()); // 指定音频文件的路径
            mediaPlayer.prepare(); // 让MediaPlayer进入到准备状态
        } catch (Exception e) {
            e.printStackTrace();
        }

        left_top.setOnClickListener(this);
        right_top.setOnClickListener(this);
        left_bottom.setOnClickListener(this);
        right_bottom.setOnClickListener(this);

        //Intent由DemoMessageReceiver中的onNotificationMessageClicked()传递而来
        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        String message = intent.getStringExtra("message");


        content = words[0].getName();
        textView.setText(content);
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            if (!url.equals("")) {
                FileInputStream fis = new FileInputStream(url);
                return BitmapFactory.decodeStream(fis);
            } else return null;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    //下一个单词
    public void next(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left_top: {
                Intent intent = new Intent(this, SentenceActivity.class);
                intent.putExtra("word", content);//把单词传递给下一个活动
                startActivity(intent);
            }
            break;
            case R.id.left_bottom: {
                Intent intent = new Intent(this, VideoActivity.class);
                intent.putExtra("word", content);//把单词传递给下一个活动
                startActivity(intent);
            }
            break;
            case R.id.right_top: {
//                Intent intent = new Intent(this, ParagraphActivity.class);
//                intent.putExtra("word", content);//把单词传递给下一个活动
//                startActivity(intent);
                if (!mediaPlayer.isPlaying()) {
                    mediaPlayer.start(); // 开始播放
                } else {
                    mediaPlayer.pause(); // 暂停播放
                }

            }
            break;
            case R.id.right_bottom: {
                Intent intent = new Intent(this, WordActivity.class);
                intent.putExtra("word", content);//把单词传递给下一个活动
                startActivity(intent);
            }
            break;
        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        mediaPlayer.release();
    }
}
