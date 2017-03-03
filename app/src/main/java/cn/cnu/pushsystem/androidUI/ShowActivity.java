package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import cn.cnu.pushsystem.R;


public class ShowActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView left_top;
    ImageView right_top;
    ImageView left_bottom;
    ImageView right_bottom;
    private TextView textView;
    String content;
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

        left_top.setOnClickListener(this);
        right_top.setOnClickListener(this);
        left_bottom.setOnClickListener(this);
        right_bottom.setOnClickListener(this);

       //Intent由DemoMessageReceiver中的onNotificationMessageClicked()传递而来
        Intent intent = getIntent();
        content = intent.getStringExtra("content");
        String message = intent.getStringExtra("message");
        textView.setText(content);

        content=1+"apple";
        // 取字符串的前i个字符
        String  str=content.substring(0,1);
        //去掉字符串的前i个字符：
        String str2=content.substring(1);
        System.out.println(str+"---------------");
        System.out.println(str2+"---------------");
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
                Intent intent = new Intent(this, ParagraphActivity.class);
                intent.putExtra("word", content);//把单词传递给下一个活动
                startActivity(intent);
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
}
