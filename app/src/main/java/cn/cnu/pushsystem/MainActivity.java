package cn.cnu.pushsystem;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.xiaomi.mipush.sdk.MiPushClient;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.cnu.pushsystem.androidUI.ShowActivity;

public class MainActivity extends AppCompatActivity {

    public static List<String> logList = new CopyOnWriteArrayList<String>();

    private TextView mLogView = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLogView = (TextView) findViewById(R.id.log);
        DemoApplication.setMainActivity(this);
        //设置别名，撤销别名（alias）
        // MiPushClient.setAlias(MainActivity.this, "demo1", null);
        //MiPushClient.unsetAlias(MainActivity.this, "demo1", null);
        //设置账号，撤销账号（account）
        // MiPushClient.setUserAccount(MainActivity.this, "user1", null);
        //MiPushClient.unsetUserAccount(MainActivity.this, "user1", null);
        //设置标签，撤销标签（topic：话题、主题）
        MiPushClient.subscribe(MainActivity.this, "xiaomipad", null);
        //MiPushClient.unsubscribe(MainActivity.this, "IT", null);
        //设置接收时间（startHour, startMin, endHour, endMin）
        MiPushClient.setAcceptTime(MainActivity.this, 0, 0, 23, 59, null);
        //暂停和恢复推送
        //MiPushClient.pausePush(MainActivity.this, null);
        //MiPushClient.resumePush(MainActivity.this, null);
    }

    public void button(View view) {
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshLogInfo();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DemoApplication.setMainActivity(null);
    }

    public void refreshLogInfo() {
        String AllLog = "";
        for (String log : logList) {
            AllLog = AllLog + log + "\n\n";
        }
        mLogView.setText(AllLog);
    }
}
