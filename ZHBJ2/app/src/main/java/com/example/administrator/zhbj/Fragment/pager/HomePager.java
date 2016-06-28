package com.example.administrator.zhbj.Fragment.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 * 三级人页面的首页
 *
 */
public class HomePager extends BasePager{

    public HomePager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentview) {
      mTvTitle.setText("首页");
        TextView view=new TextView(mActivity);
        view.setTextSize(22);
        view.setText("三级首页");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentview.addView(view);

    }
}
