package com.example.administrator.zhbj.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 2016/6/24.
 * 处理一些子类相同的事情
 */
public abstract class BaseFragment extends Fragment {
   //[☆]/*上下文其实就是MAinactivity*/
    public Activity mActivity;
    //[☆]/*页面 视图*/
    public View mRootView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity=getActivity();//保存上下文

    }

    /**
     * 方法  ：创建Fragemnt的显示内容
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView=initview();
     //   System.out.println(initview()+"initview()");
        return mRootView;

    }

    /**
     * 初始化页面控件
     * @return
     * 必须要求实现
     */
    public abstract View initview() ;

    /**
     * 初始化获取片段显示的数
     */
    public void initData(){
  }
}
