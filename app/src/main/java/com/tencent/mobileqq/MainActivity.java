package com.tencent.mobileqq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private WebView web=null;//(WebView)findViewById(R.id.web1);
    private Button bt1=null;//(Button) findViewById(R.id.button1);
    private Button bt2=null;//(Button) findViewById(R.id.button2);
    private Button bt3=null;//(Button) findViewById(R.id.button3);
    private EditText edit3=null;//(EditText) findViewById(R.id.editTextTextPersonName2);
    private String pwUrl="https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=35&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String qrUrl="https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=qr&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String wxUrl="https://open.weixin.qq.com/connect/app/qrconnect?appid=[应用ID]&bundleid=[应用包名]&scope=snsapi_login,snsapi_userinfo,snsapi_friend,snsapi_message,scope_what_you_want&state=weixin&from=message&isappinstalled=0";
    private Intent inten1=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}