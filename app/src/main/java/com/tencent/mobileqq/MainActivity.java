package com.tencent.mobileqq;

import android.content.Intent;
import android.graphics.Bitmap;
import android.preference.EditTextPreference;
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
        web=(WebView)findViewById(R.id.web1);
        bt1=(Button) findViewById(R.id.button1);
        bt2=(Button) findViewById(R.id.button2);
        bt3=(Button) findViewById(R.id.button3);
        edit3=(EditText) findViewById(R.id.editTextTextPersonName2);
        bt1.setOnClickListener(new OnclickListener());
        bt1.setText("密码");
        bt2.setOnClickListener(new OnclickListener());
        bt2.setText("扫码");
        bt3.setOnClickListener(new OnclickListener());
        bt3.setText("登录");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setSupportMultipleWindows(true);
        web.setWebViewClient(new WebViewClientBase());
        gc();//处理事件
        //web.loadUrl("https://www.baidu.com") ;
    }
    class WebViewClientBase extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            edit3.setText(url);
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            // TODO Auto-generated method stub
            super.onPageFinished(view, url);
        }


        @Override
        public void doUpdateVisitedHistory(WebView view, String url,
                                           boolean isReload) {
            // TODO Auto-generated method stub
            super.doUpdateVisitedHistory(view, url, isReload);
        }
    }
    class OnclickListener implements View.OnClickListener {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.button1:
                    //12
                    //Toast.makeText(getApplicationContext(),bt1.getText(),Toast.LENGTH_LONG).show();
                    String url ="";
                    web.loadUrl(pwUrl) ;
                    break;
                case R.id.button2:
                    //32
                    //Toast.makeText(getApplicationContext(),bt2.getText(),Toast.LENGTH_LONG).show();
                    web.loadUrl(qrUrl) ;
                    break;
                case R.id.button3:
                    //45
                    //Toast.makeText(getApplicationContext(),bt3.getText(),Toast.LENGTH_LONG).show();
                    web.loadUrl(wxUrl) ;
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setInten(intent);
        gc();//处理被启动操作
    }

    private void gc(){
        if (inten1 != null) {
            edit3.setText(edit3.getText() + this.inten1.getPackage());
            Bundle bl= inten1.getBundleExtra("appid");
           // edit3.setText(edit3.getText() + );
        }
    }
    private void setInten(Intent inten){
        this.inten1=inten;
    }
}