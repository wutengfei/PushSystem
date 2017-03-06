package cn.cnu.pushsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import com.xiaomi.xmpush.server.TargetedMessage;

public class SecondActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        textView = (TextView) findViewById(R.id.textView);
        //从DemoMessageReceiver传来的Intent
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String topic = intent.getStringExtra("topic");
       // content = "1,apple,banana,good,nice";
//        // 取字符串的前i个字符
//        String str = content.substring(0, 1);
//        //去掉字符串的前i个字符：
//        String str2 = content.substring(1);
//        System.out.println(str + "---------------");
//        System.out.println(str2 + "---------------");
//        textView.setText(content + "---" + message);
     //   TargetedMessage targetedMessage1 = new TargetedMessage();
       String contents[]= content.split(",");
        for(int i = 0; i<contents.length; i++)
            System.out.println(contents[i]);



    }

}
