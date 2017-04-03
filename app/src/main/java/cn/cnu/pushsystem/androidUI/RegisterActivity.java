package cn.cnu.pushsystem.androidUI;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import cn.cnu.pushsystem.R;
import cn.cnu.pushsystem.utils.HttpUtils;

public class RegisterActivity extends AppCompatActivity {
    private Handler mHandler;
    private static final int LOGIN_SUCCESS_FLAG = 1;
    private static final int REGISTER_SUCCESS_FLAG = 2;
    private static final int SERVER_ERROR = 3;
    private static final int REGISTER_FAIL_FLAG = 4;
    private static final int USERNAME_EXIST = 5;
    private static final int UNKNOWN_FLAG = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                android.util.Log.i("wqx", "msg.what=" + msg.what);
                switch (msg.what) {

                    case REGISTER_SUCCESS_FLAG:
                        Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this,
                                LoginActivity.class));
                        break;
                    case REGISTER_FAIL_FLAG:
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        break;
                    case SERVER_ERROR:
                        Toast.makeText(RegisterActivity.this, "服务器异常", Toast.LENGTH_SHORT).show();
                        break;
                    case USERNAME_EXIST:
                        Toast.makeText(RegisterActivity.this, "用户名已存在", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public void register(View v) {
        EditText et_name = (EditText) findViewById(R.id.et_username);
        EditText et_pass = (EditText) findViewById(R.id.et_password);

        final String userName = et_name.getText().toString();
        final String passWord = et_pass.getText().toString();
        if (TextUtils.isEmpty(userName) || TextUtils.isEmpty(passWord)) {
            Toast.makeText(this, "用户名和密码不能为空", Toast.LENGTH_SHORT).show();
        } else {
            new Thread() {

                public void run() {
                    Message msg = Message.obtain();
                    String result = HttpUtils.sendGetRequestRegister(
                            userName, passWord);
                    if (result != null) {
                        if (result.equals("register_success")) {
                            msg.what = REGISTER_SUCCESS_FLAG;
                            mHandler.sendMessage(msg);
                        } else if (result.equals("register_fail")) {
                            msg.what = REGISTER_FAIL_FLAG;
                            mHandler.sendMessage(msg);
                        } else if (result.equals("userName_exist")) {
                            msg.what = USERNAME_EXIST;
                            mHandler.sendMessage(msg);
                        } else if (result.equals("服务器异常")) {
                            msg.what = SERVER_ERROR;
                            mHandler.sendMessage(msg);
                        }
                    } else {
                        msg.what = UNKNOWN_FLAG;
                        mHandler.sendMessage(msg);
                    }
                }

                ;

            }.start();
        }
    }
}
