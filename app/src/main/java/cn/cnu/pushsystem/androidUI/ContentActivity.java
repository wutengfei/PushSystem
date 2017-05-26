package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import cn.cnu.pushsystem.R;

public class ContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        getSupportActionBar().hide();
    }

    public void unit1(View view) {
        startActivity(new Intent(this,Unit1Activity.class));
    }

}
