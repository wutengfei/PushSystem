package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.cnu.pushsystem.MainActivity;
import cn.cnu.pushsystem.R;
import cn.cnu.pushsystem.utils.TextStreamUtils;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * 发送请求登录
     *
     * @param
     */
    public void login(View v) {
        sendRequestWithHttpURLConnection();
    }

    private void sendRequestWithHttpURLConnection() {
        EditText et_name = (EditText) findViewById(R.id.et_username);
        EditText et_pass = (EditText) findViewById(R.id.et_password);

        final String name = et_name.getText().toString();
        final String pass = et_pass.getText().toString();

        //判断是否有网络连接
        NetworkInfo netIntfo = null;
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(this.CONNECTIVITY_SERVICE);
        netIntfo = connectivityManager.getActiveNetworkInfo();
        if (netIntfo == null) {
            Toast.makeText(this, "请检查网络", Toast.LENGTH_SHORT).show();
        } else {
            // 开启线程来发起网络请求
            Thread t = new Thread() {

                public void run() {
                    //提交的数据需要经过url编码，英文和数字编码后不变

                    String path = "http://172.19.203.88:8080/PushSys/servlet/LoginServlet";

                    try {
                        URL url = new URL(path);
                        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                        conn.setRequestMethod("POST");
                        conn.setConnectTimeout(5000);
                        conn.setReadTimeout(5000);

                        //拼接出要提交的数据的字符串
                        String data = "name=" + URLEncoder.encode(name) + "&pass=" + pass;
                        //添加post请求的两行属性，设置请求体的是文本类型
                        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                        conn.setRequestProperty("Content-Length", data.length() + "");

                        //设置打开输出流
                        conn.setDoOutput(true);
                        //拿到输出流
                        OutputStream os = conn.getOutputStream();
                        //使用输出流往服务器提交数据
                        os.write(data.getBytes());
                        if (conn.getResponseCode() == 200) {
                            InputStream is = conn.getInputStream();
                            String text = TextStreamUtils.getTextFromStream(is);
                            Message msg = handler.obtainMessage();
                            msg.obj = text;
                            handler.sendMessage(msg);

                        }
                    } catch (Exception e) {
                        Message msg = handler.obtainMessage();
                        msg.obj = "severError";
                        handler.sendMessage(msg);
                        e.printStackTrace();

                    }
                }
            };
            t.start();
        }
    }

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            if (msg.obj.toString().equals("login success")) {
                Toast.makeText(LoginActivity.this, "登陆成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else if (msg.obj.toString().equals("login fail")) {
                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
            } else if (msg.obj.toString().equals("severError")) {
                Toast.makeText(LoginActivity.this, "服务器错误", Toast.LENGTH_SHORT).show();
            }
        }
    };

}
