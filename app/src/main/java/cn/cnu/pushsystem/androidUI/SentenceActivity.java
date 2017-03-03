package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import cn.cnu.pushsystem.R;


public class SentenceActivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sentence);
        getSupportActionBar().hide();
         textView= (TextView) findViewById(R.id.textView);
        Intent intent=getIntent();
        String word=intent.getStringExtra("word");
        textView.setText(word);
    }
}
