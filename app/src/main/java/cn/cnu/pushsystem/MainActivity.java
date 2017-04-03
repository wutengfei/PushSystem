package cn.cnu.pushsystem;


import android.content.Intent;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import android.widget.Toast;
import cn.cnu.pushsystem.androidUI.ShowActivity;
import cn.cnu.pushsystem.dao.XmlDAO;
import cn.cnu.pushsystem.entity.Tb_word;
import cn.cnu.pushsystem.utils.DemoApplication;
import cn.cnu.pushsystem.utils.XmlFileAnalysis;
import cn.cnu.pushsystem.utils.ZIP;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.xiaomi.mipush.sdk.MiPushClient;

import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;


public class MainActivity extends AppCompatActivity {
    private TextView textView;
    public static List<String> logList = new CopyOnWriteArrayList<String>();
    private String fileName;
    String dir = "";//存储app应用数据的根目录，Android/data/包名
    boolean isAnalysis = false;//是否解析过xml文件

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        fileName = "resource.zip";
        textView = (TextView) findViewById(R.id.textView);
        String url = "http://172.19.203.88:8080/" + fileName;
        textView.setText(url);

        dir = Environment.getExternalStorageDirectory() + File.separator +
                "Android/data/" + getApplication().getPackageName();//存储app应用数据的根目录，Android/data/包名


        //  mLogView = (TextView) findViewById(R.id.log);
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

    Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            Toast.makeText(MainActivity.this, (String) msg.obj, Toast.LENGTH_SHORT).show();
        }
    };

    //跳转至下一页
    public void button(View view) {
        Intent intent = new Intent(this, ShowActivity.class);
        startActivity(intent);
    }

    /**
     * 发送请求登录
     *
     * @param
     */
   /* public void sendRequest(View v) {
        sendRequestWithHttpURLConnection();
    }*/

   /* private void sendRequestWithHttpURLConnection() {
        EditText et_name = (EditText) findViewById(R.id.et_name);
        EditText et_pass = (EditText) findViewById(R.id.et_pass);

        final String name = et_name.getText().toString();
        final String pass = et_pass.getText().toString();
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
                    //添加post请求的两行属性
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
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        };
        t.start();


    }*/

    //点击下载按钮
    public void download(View view) {
        String url = textView.getText().toString();

        System.out.println(dir);
        File file = new File(dir);
        if (!file.exists()) {
            file.mkdir();
        }


        HttpUtils http = new HttpUtils();
        http.download(url, //下载请求的网址
                dir + "/" + fileName, //下载的数据保存路径和文件名
                true, //是否开启断点续传
                true, //如果服务器响应头中包含了文件名，那么下载完毕后自动重命名
                new RequestCallBack<File>() {//侦听下载状态

                    //下载成功此方法调用
                    @Override
                    public void onSuccess(ResponseInfo<File> arg0) {
                        Toast.makeText(MainActivity.this, "下载成功", Toast.LENGTH_SHORT).show();
                        //下载完成后解压缩
                        File file1 = new File(dir, fileName);//下载下来的压缩包
                        String unZipDir = dir + "/resource";//解压缩后的存放目录
                        File file2 = new File(unZipDir);
                        if (!file2.exists()) {
                            file2.mkdir();
                        }


                        try {
                            // ZipUtils.upZipFile(file1, unZipDir);
                            ZIP.UnZipFolder(file1.getPath(), unZipDir);
                            Toast.makeText(MainActivity.this, "解压缩成功", Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        //解析解压缩后的xml文件
                        if (!isAnalysis) {
                            analysis();
                            isAnalysis = true;
                        } else {
                            Toast.makeText(MainActivity.this, "已经解析过", Toast.LENGTH_SHORT).show();
                        }
                    }

                    //下载失败此方法调用，比如文件已经下载、没有网络权限、文件访问不到，方法传入一个字符串参数告知失败原因
                    @Override
                    public void onFailure(HttpException arg0, String arg1) {
                        Toast.makeText(MainActivity.this, "下载失败，可能已经下载", Toast.LENGTH_SHORT).show();
                    }

                    //在下载过程中不断的调用，用于刷新进度条
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                    }
                });


    }

    //解析解压缩后的xml文件
    public void analysis() {

        String Path = dir + "/resource";//xml文件所在目录
        File file2 = new File(Path);
        File[] tempList = file2.listFiles();
        System.out.println(tempList[tempList.length - 1].getName());
        XmlFileAnalysis xmlFileAnalysis = new XmlFileAnalysis(Path, tempList[tempList.length - 1].getName());
        List<Tb_word> words = xmlFileAnalysis.readXML();
        XmlDAO xmlDAO = new XmlDAO(MainActivity.this);
        if (xmlDAO.insert(words)) {
            //  successCallback.invoke("数据解析成功!");
            System.out.println("数据解析成功");
            Toast.makeText(MainActivity.this, "数据解析成功", Toast.LENGTH_SHORT).show();
        } else {
            //  errorCallback.invoke("数据解析失败");
            System.out.println("数据解析失败");
            Toast.makeText(MainActivity.this, "数据解析失败", Toast.LENGTH_SHORT).show();
        }
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
        // mLogView.setText(AllLog);
    }

    public void changeTopic(View view) {
        MiPushClient.subscribe(MainActivity.this, "xiaomi5s", null);
    }

}
