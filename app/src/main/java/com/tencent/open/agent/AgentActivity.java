package com.tencent.open.agent;


import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
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
    private String pwUrl="https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=36&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    //"https://xui.ptlogin2.qq.com/cgi-bin/xlogin?appid=716027609&pt_3rd_aid=[应用ID]&daid=381&pt_skey_valid=0&style=35&s_url=http%3A%2F%2Fconnect.qq.com&refer_cgi=m_authorize&ucheck=1&fall_to_wv=1&status_os=10&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&client_id=[应用ID]&response_type=token&scope=all&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&status_machine=SHARK+KLE-A0&switch=1&loginfrom=main&h5sig=7xVysXlv4cWDVQKxoQ1PDgF_EBSWdIQrEDuDhHbmIpo&loginty=1";
            //https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=36&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String qrUrl="https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=qr&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
                    //"https://xui.ptlogin2.qq.com/cgi-bin/xlogin?appid=716027609&pt_3rd_aid=[应用ID]&daid=381&pt_skey_valid=0&style=qr&s_url=http%3A%2F%2Fconnect.qq.com&refer_cgi=m_authorize&ucheck=1&fall_to_wv=1&status_os=10&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&client_id=[应用ID]&response_type=token&scope=all&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&status_machine=SHARK+KLE-A0&switch=1&loginfrom=main&h5sig=7xVysXlv4cWDVQKxoQ1PDgF_EBSWdIQrEDuDhHbmIpo&loginty=1";
    //qrUrl="https://openmobile.qq.com/oauth2.0/m_authorize?_wv=16777218&status_os=10&client_id=[应用ID]&format=json&switch=1&status_version=29&status_machine=SHARK%20KLE-A0&sdkp=i&sdkv=3.51_full&sign=A36EED171E7779BC319C7A9425B97346&scope=all&style=qr&redirect_uri=auth%3A%2F%2Fwww.qq.com%2F&display=mobile&response_type=token&loginfrom=main&cancel_display=1";
    private String wxUrl="https://open.weixin.qq.com/connect/app/qrconnect?appid=[应用ID]&bundleid=[应用包名]&scope=snsapi_login,snsapi_userinfo,snsapi_friend,snsapi_message,scope_what_you_want&state=weixin&from=message&isappinstalled=0";
    private String appid="";//游戏id
    private String pack="";//包名
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
        bt1.setText("密码登录");
        bt2.setOnClickListener(new OnclickListener());
        bt2.setText("扫码登录");
        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        web.getSettings().setSupportMultipleWindows(true);

        WebSettings settings = web.getSettings();
        String ua = settings.getUserAgentString();// 获取默认的UA
        String  srt="Mozilla/5.0 (Linux; Android 10; MI 8 SE Build/QKQ1.190828.002; wv) AppleWebKit/537.36 (KHTML, like Gecko) Version/4.0 Chrome/77.0.3865.120 MQQBrowser/6.2 TBS/045512 Mobile Safari/537.36 MMWEBID/1299 MicroMessenger/8.0.1840(0x28000039) Process/tools WeChat/arm64 Weixin NetType/WIFI Language/zh_CN ABI/arm64";
        settings.setUserAgentString(ua + ";"+srt);
        web.setWebViewClient(new WebViewClientBase());

        //edit3.setText(web.getSettings().getUserAgentString());
        if(getIntent()!=null) {
             appid=getIntent().getStringExtra("appid");
             if(appid != null && !appid.equals("")) {
                 String url = pwUrl.replace("[应用ID]", appid);
                 web.loadUrl(url);
             }else{
                 Toast.makeText(getApplicationContext(),"appid不合法",Toast.LENGTH_LONG).show();
             }
             edit3.setText(edit3.getText() + "\nAPPID：" + appid);
             Uri referrer = null;
             if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP_MR1) {
                 referrer = getReferrer();
             }
             if (referrer != null) {
                 pack=referrer.toString().replace("android-app://","");
                 edit3.setText(edit3.getText() + "\n包名："+pack);
             }
        }
    }

    @SuppressLint("WrongConstant")
    public void qqLogin(String access_token, String pay_token, String openid, String pack){
        Intent var6 = this.getApplicationContext().getPackageManager().getLaunchIntentForPackage(pack);
        var6.setFlags(0x30000000);
        Bundle var5 =new Bundle();
        var5.putString("platform", "qq_m");
        var5.putString("current_uin", openid);
        var5.putString("launchfrom", "sq_gamecenter");
        var5.putString("ptoken", pay_token);
        var5.putString("preAct", "GameCenterActivity");
        var5.putString("openid", openid);
        var5.putString("atoken", access_token);
        var5.putString("gamedata", "");
        var5.putString("platformdata", "");
        var5.putString("preAct_time", "");
        var5.putString("fling_code_key", "");
        var5.putString("fling_action_key", "");
        var6.putExtras(var5);
        this.getApplicationContext().startActivity(var6);
    }
    class WebViewClientBase extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            // TODO Auto-generated method stub
            //edit3.setText(url);
            Uri ur= Uri.parse(url);
            Toast.makeText(getApplicationContext(),url,Toast.LENGTH_LONG).show();
            if(url.indexOf("auth://progress/")!=-1){
                view.stopLoading();
            }else if(url.indexOf("auth://www.qq.com?#")!=-1 ||
                    url.indexOf("auth://www.qq.com/?#")!=-1 ||
                    url.indexOf("auth://tauth.qq.com?#")!=-1 ||
                    url.indexOf("auth://tauth.qq.com/?#")!=-1 ||
                    url.indexOf("https://imgcache.qq.com/open/connect")!=-1){
                String access_token=ur.getQueryParameter("access_token");;
                String pay_token=ur.getQueryParameter("pay_token");;
                String openid=ur.getQueryParameter("openid");
                qqLogin(access_token,pay_token,openid,pack);
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
                        if(appid != null && !appid.equals("")) {
                            String url = pwUrl.replace("[应用ID]", appid);
                            web.loadUrl(url);
                        }else{
                            Toast.makeText(getApplicationContext(),"appid不合法",Toast.LENGTH_LONG).show();
                        }
                    break;
                case R.id.button2:
                    if(appid != null && !appid.equals("")) {
                        String url2 = qrUrl.replace("[应用ID]", appid);
                        web.loadUrl(url2);
                    }else{
                        Toast.makeText(getApplicationContext(),"appid不合法",Toast.LENGTH_LONG).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

}
