package com.example.administrator.zhbj.Fragment.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 * 三级页面设置
 */
public class SetTingPager extends BasePager{

    /**
     *构造方法
     */

    public SetTingPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentview) {
      mTvTitle.setText("设置");
        TextView view=new TextView(mActivity);
        view.setTextSize(22);
        view.setText("三级页面设置");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentview.addView(view);

    }
}
