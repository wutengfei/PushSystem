package cn.cnu.pushsystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.xiaomi.xmpush.server.TargetedMessage;

public class SecondActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true); //允许使用javascript
        webView.setWebViewClient(new WebViewClient() {//使网页中的链接不以浏览器的方式打开
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求，不用借系统浏览器
            }
        });

        //从DemoMessageReceiver传来的Intent
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String topic = intent.getStringExtra("topic");

        String url = "http:" + content;
        //加载服务器上的页面
        if (content==null) {
            webView.loadUrl("http://www.baidu.com");
        } else {
            webView.loadUrl(url);
        }



    }

}
