package com.tencent.open.agent;


import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.tencent.mobileqq.R;

import java.util.List;
import java.util.Set;
public class AgentActivity extends AppCompatActivity{
    private WebView web=null;//(WebView)findViewById(R.id.web1);
    private Button bt1=null;//(Button) findViewById(R.id.button1);
    private Button bt2=null;//(Button) findViewById(R.id.button2);
    private Button bt3=null;//(Button) findViewById(R.id.button3);
    private EditText edit3=null;//(EditText) findViewById(R.id.editTextTextPersonName2);
    private String pwUrl="https://xui.ptlogin2.qq.com/cgi-bin/xlogin?appid=716027609&pt_3rd_aid=[应用ID]&daid=381&pt_skey_valid=0&style=35&s_url=http%3A%2F%2Fconnect.qq.com&refer_cgi=m_authorize&ucheck=1&fall_to_wv=1&status_os=10&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&client_id=[应用ID]&response_type=token&scope=all&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&status_machine=SHARK+KLE-A0&switch=1&loginfrom=main&h5sig=7xVysXlv4cWDVQKxoQ1PDgF_EBSWdIQrEDuDhHbmIpo&loginty=1";
            //https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=36&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String qrUrl="https://xui.ptlogin2.qq.com/cgi-bin/xlogin?appid=716027609&pt_3rd_aid=[应用ID]&daid=381&pt_skey_valid=0&style=qr&s_url=http%3A%2F%2Fconnect.qq.com&refer_cgi=m_authorize&ucheck=1&fall_to_wv=1&status_os=10&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&client_id=[应用ID]&response_type=token&scope=all&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&status_machine=SHARK+KLE-A0&switch=1&loginfrom=main&h5sig=7xVysXlv4cWDVQKxoQ1PDgF_EBSWdIQrEDuDhHbmIpo&loginty=1";
    //https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=qr&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String wxUrl="https://open.weixin.qq.com/connect/app/qrconnect?appid=[应用ID]&bundleid=[应用包名]&scope=snsapi_login,snsapi_userinfo,snsapi_friend,snsapi_message,scope_what_you_want&state=weixin&from=message&isappinstalled=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        web=(WebView)findViewById(R.id.web1);
        bt1=(Button) findViewById(R.id.button1);
        bt2=(Button) findViewById(R.id.button2);
        bt3=(Button) findViewById(R.id.button3);
        edit3=(EditText) findViewById(R.id.editTextTextPersonName3);
        bt1.setOnClickListener(new OnclickListener());
        bt1.setText("密码1");
        bt2.setOnClickListener(new OnclickListener());
        bt2.setText("扫码2");
        bt3.setOnClickListener(new OnclickListener());
        bt3.setText("登录3");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setSupportMultipleWindows(true);
        web.setWebViewClient(new WebViewClientBase());
        String ua = web.getSettings().getUserAgentString();
        web.getSettings().setUserAgentString(ua.replace("Android","Mozilla/5.0 (Linux; Android 10; CDY-AN90 Build/HUAWEICDY-AN90; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/78.0.3904.62 XWEB/2691 MMWEBSDK/200801 Mobile Safari/537.36 MMWEBID/4006 MicroMessenger/7.0.18.1740(0x2700123B) Process/toolsmp WeChat/arm64 NetType/4G Language/zh_CN ABI/arm64"));
         if(getIntent()!=null) {
            edit3.setText(edit3.getText() + "\nonCreate1" + getIntent().getStringExtra("appid"));
             ActivityManager actvityManager = (ActivityManager)
                     this.getSystemService( ACTIVITY_SERVICE );

             List<ActivityManager.RunningAppProcessInfo> procInfos = actvityManager.getRunningAppProcesses();
             getIntent().putExtra("pgname",procInfos.get(1).processName);//获取最近的activity包
             edit3.setText(edit3.getText() +"\n"+getIntent() .getStringExtra("pgname") );
        }else{
            edit3.setText(edit3.getText() +"\nintentnull");
        }
        //String se=
        if(savedInstanceState !=null) {
            StringBuilder string = new StringBuilder("Bundle{");
            for (String key : savedInstanceState.keySet()) {
                string.append(" ").append(key).append(" => ").append(savedInstanceState.get(key)).append(";");
            }
            string.append(" }Bundle");
            edit3.setText(edit3.getText() +"\n"+string);
        }else{
            edit3.setText(edit3.getText() +"\nnull");
        }

        //gc();//处理事件
        //web.loadUrl("https://www.baidu.com") ;
    }
    class WebViewClientBase extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            edit3.setText(url);
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            if(url.indexOf("auth://progress/")!=-1){
                view.stopLoading();
            }
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
                        String appid = getIntent().getStringExtra("appid");
                        if(appid != null && !appid.equals("")) {
                            String url = pwUrl.replace("[应用ID]", appid);
                            web.loadUrl(url);
                        }else{
                            Toast.makeText(getApplicationContext(),"appid不合法",Toast.LENGTH_LONG).show();
                        }
                    break;
                case R.id.button2:
                    //32
                    String appid2 = getIntent().getStringExtra("appid");
                    if(appid2 != null && !appid2.equals("")) {
                        String url2 = qrUrl.replace("[应用ID]", appid2);
                        web.loadUrl(url2);
                    }else{
                        Toast.makeText(getApplicationContext(),"appid不合法",Toast.LENGTH_LONG).show();
                    }
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
        setIntent(intent);
        edit3.setText(edit3.getText() + intent.getPackage());
        edit3.setText(edit3.getText() +"\nonNewIntent");

    }






    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(savedInstanceState != null) {
            edit3.setText(edit3.getText() + "\nonPostCreate");
        }else{
            edit3.setText(edit3.getText() + "\nonPostCreatenull");
        }
        /*String string = "Bundle{";
        for (String key : savedInstanceState.keySet()) {
            string += " " + key + " => " + savedInstanceState.get(key) + ";";
        }
        string += " }Bundle";;
        edit3.setText(edit3.getText() +"\n"+string);*/

    }



}
