package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.cnu.pushsystem.R;

public class Unit1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit1);
        getSupportActionBar().hide();
    }

    public void word1(View view) {
        startActivity(new Intent(this, ShowActivity.class));
    }
}
