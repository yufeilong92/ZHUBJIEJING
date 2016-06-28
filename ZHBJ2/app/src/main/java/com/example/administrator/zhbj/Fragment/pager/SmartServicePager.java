package com.example.administrator.zhbj.Fragment.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 * 三级页面智慧服务
 */
public class SmartServicePager extends BasePager{

    /**
     *构造方法
     */

    public SmartServicePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentview) {
      mTvTitle.setText("智慧服务");
        TextView view=new TextView(mActivity);
        mTVMenu.setVisibility(View.VISIBLE);
        view.setTextSize(22);
        view.setText("三级页面智慧服务");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentview.addView(view);

    }
}
