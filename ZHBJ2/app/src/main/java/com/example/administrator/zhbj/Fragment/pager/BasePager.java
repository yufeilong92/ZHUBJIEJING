package com.example.administrator.zhbj.Fragment.pager;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.zhbj.Constant.GloableConstant;
import com.example.administrator.zhbj.R;

/**
 * Created by Administrator on 2016/6/27.
 * 为三级页面的基类，创建该的想法是以最少的代码实现同样的功能
 */
public abstract class BasePager {
     /** 视图*/
    public View mRootView;
    /**z中间内容*/
    public FrameLayout mContentView;
    /** 上下文 即使activity*/
    public Activity mActivity;
    /**标题*/
    public TextView mTvTitle;
    /**菜单按键*/
    public ImageView mTVMenu;
    /**右边按键*/
    public  ImageView mTVRight;
public BasePager(Activity activity){
    super();
    this.mActivity=activity;
//    打气筒
    /**
     * 初始化中间代码
     */
    mRootView=View.inflate(mActivity, R.layout.paget_base,null);

   mTvTitle= (TextView) mRootView.findViewById(R.id.tv_title);
    mTVMenu= (ImageView) mRootView.findViewById(R.id.iv_img_menu);
    mTVRight= (ImageView) mRootView.findViewById(R.id.iv_icon_pic_grid_type);
    mContentView= (FrameLayout) mRootView.findViewById(R.id.fl_center_view);
    /**
     * 菜单按钮的实现功能
     */
   mTVMenu.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View view) {
//           广播及时Intent
           Intent intent=new Intent();
           intent.setAction(GloableConstant.ACION_SLIDING_TOGGLE);
//           发广播
           mActivity.sendBroadcast(intent);
       }
   });
    initView(mContentView);
}
/**
 * 初始化三级页面的控件
  */
 public  abstract void  initView(FrameLayout contentview);
    /**
     * 方法:加载三级页面数据
     */
    public  void  initData(){};
}
