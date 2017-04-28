package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cn.cnu.pushsystem.R;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        getSupportActionBar().hide();
    }
    //跳转至下一页
    public void next(View view) {
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
    }
}
