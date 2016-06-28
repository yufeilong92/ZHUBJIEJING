package com.example.zhbj03text;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.tv_textview);
    }
    public void request(View v){
//        请求方法 url，回掉对象
        HttpUtils httpUtils = new HttpUtils();

        RequestCallBack<String> requestCallBack = new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo responseInfo) {
             textView.setText("请求成功"+responseInfo.result);
            }
            @Override
            public void onFailure(HttpException e, String s) {
                textView.setText("请求失败"+e.getMessage());
            }
        };
        String url="http://192.168.56.1:8080/";
        httpUtils.send(HttpRequest.HttpMethod.GET,url, requestCallBack);
    }
}
