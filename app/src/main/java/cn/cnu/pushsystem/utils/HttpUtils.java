package cn.cnu.pushsystem.utils;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {
    private static String hostname = "http://172.19.203.88:8080";//修改为自己电脑的ip地址
    private static String path = hostname + "/PushSys/servlet/LoginServlet";
    private static final int GETVALUE = 1001;

    HttpUtils() {
    }

    private static URL url = null;
    private static HttpURLConnection huc = null;

    public static String sendGetRequestLogin(String user, String pwd) {
        String result = null;

        try {
            String path = hostname + "/PushSys/servlet/LoginServlet";
            StringBuilder requestUrl = new StringBuilder(path);
            requestUrl.append("?").append("userName=").append(user).append("&")
                    .append("passWord=").append(pwd);
            String urlPath = requestUrl.toString();
            System.getProperty("file.encoding");
            Log.i("info", "urlPath=" + urlPath);
            url = new URL(urlPath);
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestProperty("charset", "UTF-8");
            huc.setRequestMethod("GET");
            huc.setConnectTimeout(15000);
            huc.setReadTimeout(5000);
            huc.setDoInput(true);
            if (huc != null) {
                if (huc.getResponseCode() == 200) {
                    /*请求成功不代表登录成功：举个例子，你问小王吃饭没，小王回答你了，就代表请求成功。小王没理你，就代表请求没成功，有可能是他没听到你说话，或者他不想理你。
                     * 小王回答你说吃过了，代表登录成功。小王回答你说没吃，代表登录失败。*/
                    Log.i("info", "请求登录!");
                    //inputStreamReader字节流读取转为字符流,可以一个个字符读也可以读到一个buffer
                    //getInputStream是真正去连接网络获取数据
                    InputStream in = huc.getInputStream();
                    //使用缓冲一行行的读入，加速InputStreamReader的速度
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder s = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        s.append(line);
                    }
                    reader.close();

                    result = s.toString();
                    Log.i("info", "result=" + result);
                } else {
                    Log.i("info", "请求登录Fail!");
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            Log.i("info", "e=" + e);
            result = "服务器异常";
            e.printStackTrace();
        } finally {
            if (huc != null)
                huc.disconnect();
        }
        return result;
    }

    public static String sendGetRequestRegister(String user, String pwd) {
        String result = null;
        try {
            String path = hostname + "/PushSys/servlet/RegisterServlet";
            StringBuilder requestUrl = new StringBuilder(path);
            requestUrl.append("?").append("userName=").append(user).append("&")
                    .append("passWord=").append(pwd);
            String urlPath = requestUrl.toString();
            Log.i("info", "urlPath=" + urlPath);
            url = new URL(urlPath);
            huc = (HttpURLConnection) url.openConnection();
            huc.setRequestMethod("GET");
            huc.setConnectTimeout(15000);
            huc.setReadTimeout(5000);
            huc.setRequestProperty("charset", "UTF-8");
            huc.setDoInput(true);
            if (huc != null) {
                if (huc.getResponseCode() == 200) {
                    Log.i("info", "请求注册!");
                    InputStream in = huc.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder s = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        s.append(line);
                    }
                    reader.close();
                    result = s.toString();
                    Log.i("info", "result=" + result);
                } else {
                    Log.i("info", "请求注册Fail!");
                }
            }
        } catch (Exception e) {
            result = "服务器异常";
            Log.i("info", "e=" + e);
            e.printStackTrace();
        } finally {
            if (huc != null)
                huc.disconnect();
        }
        return result;
    }
}
