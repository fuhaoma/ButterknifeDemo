package com.mfh.butterknifedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * 关于ButterKnife的使用。
 * 1.在ButterKnife里的build.gradle中的repositories {}里面添加mavenCentral
 * 在dependencies {}里面添加classpath 'com.jakewharton:butterknife-gradle-plugin:8.5.1'
 * 即：buildscript {
 * repositories { mavenCentral() }
 * dependencies { classpath 'com.jakewharton:butterknife-gradle-plugin:8.5.1'} }
 * 2.在app里的build.gradle中添加 apply plugin: 'com.jakewharton.butterknife'
 * dependencies {//添加butterKnife
 * compile 'com.jakewharton:butterknife:8.5.1'
 * annotationProcessor 'com.jakewharton:butterknife-compiler:8.5.1'}
 * 3.配置完成，在设置中的plugins中搜索ButterKnife选择下载最多的。
 * 4.在xml中写出三个控件。
 * 5.在布局文件上（R.layout.activity_main上）右键选择generate
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userName)
    EditText userName;
    @BindView(R.id.userPwd)
    EditText userPwd;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
//    @BindView(R.id.fragment)
//    FrameLayout fragment;
    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
    }

    @OnClick({R.id.userName, R.id.userPwd, R.id.submit, R.id.cancel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.userName:
                Toast.makeText(this, "点击了第一个editText", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userPwd:
                Toast.makeText(this, "点击了第二个editText", Toast.LENGTH_SHORT).show();
                break;
            case R.id.submit:
                if (!TextUtils.isEmpty(userName.getText().toString().trim())&&!TextUtils.isEmpty(userPwd.getText().toString().trim())){
                    Toast.makeText(this,"用户名："+
                                        userName.getText().toString().trim()+
                                        "密码："+
                                        userPwd.getText().toString().trim(), Toast.LENGTH_SHORT).show();

                    DemoFragment demoFragment = new DemoFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("user",userName.getText().toString().trim());
                    bundle.putString("pwd",userPwd.getText().toString().trim());
                    demoFragment.setArguments(bundle);
                    getSupportFragmentManager().beginTransaction().add(R.id.fragment,demoFragment).commit();

                }
                break;
            case R.id.cancel:
                userName.setText("");
                userPwd.setText("");
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
