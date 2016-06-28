package com.example.administrator.zhbj.Fragment.pager;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/6/27.
 * 三级页面新闻中心
 */
public class NewsConterPager extends BasePager{

    /**
     *构造方法
     */

    public NewsConterPager(Activity mActivity) {
        super(mActivity);
    }

    @Override
    public void initView(FrameLayout contentview) {
      mTvTitle.setText("新闻中心");
        TextView view=new TextView(mActivity);
        view.setTextSize(22);
        mTVMenu.setVisibility(View.VISIBLE);
        view.setText("三级页面新闻中心");
        view.setTextColor(Color.RED);
        view.setGravity(Gravity.CENTER);
        contentview.addView(view);

    }
}
