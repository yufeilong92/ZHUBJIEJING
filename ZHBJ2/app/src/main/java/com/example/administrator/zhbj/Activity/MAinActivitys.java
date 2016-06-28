package com.example.administrator.zhbj.Activity;

import android.app.FragmentTransaction;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;

import com.example.administrator.zhbj.Constant.GloableConstant;
import com.example.administrator.zhbj.Fragment.ContentFragment;
import com.example.administrator.zhbj.Fragment.LeftMenuFragment;
import com.example.administrator.zhbj.R;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * Created by Administrator on 2016/6/23.
 */
public class MAinActivitys extends SlidingFragmentActivity {

    private BroadcastReceiver broadcastReceiver;

    public MAinActivitys() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //[☆]显示中间的视图，将视图放到Slidingmenu中间
        setContentView(R.layout.activity_center);

//        try {
//            if (getSupportActionBar() != null) {
//                 getActionBar().hide();
//                getSupportActionBar().hide();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
        try {
        if (getActionBar() != null) {
            getActionBar().hide();
        }}catch(Exception e){
            e.printStackTrace();
        }
        initview();
        BraoadcastReceiver();

    }

    private void BraoadcastReceiver() {
        /**
         * 接收菜单修改的广播
         */
        //[☆]广播接收器
        broadcastReceiver = new BroadcastReceiver() {

            @Override
            public void onReceive(Context context, Intent intent) {
                Log.i("onReceive:",""+intent.getAction());
//            逻辑处理
                if(GloableConstant.ACION_SLIDING_DISABLE.equals(intent.getAction())){
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//不支持滑动
                }else if(GloableConstant.ACION_SLIDING_ENABLE.equals(intent.getAction())){
                    getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//支持滑动
                }else if(GloableConstant.ACION_SLIDING_TOGGLE.equals(intent.getAction())){
                    //[☆]调用其打开与关闭
                    getSlidingMenu().toggle();
//                    getSlidingMenu().toggle(true);打开
//                    getSlidingMenu().toggle(false);关闭
                }
            }
        };
        //[☆]注册
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(GloableConstant.ACION_SLIDING_DISABLE);
        intentFilter.addAction(GloableConstant.ACION_SLIDING_ENABLE);
        intentFilter.addAction(GloableConstant.ACION_SLIDING_TOGGLE);
        //[☆]注册广播接收器使用registerReceiver
        registerReceiver(broadcastReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        unregisterReceiver(broadcastReceiver);//删除广播
    }
    //[☆]方法初始化页面的所有空间
    /**
     *侧滑效果
     */

    private void initview() {
        //[☆]设置左侧视图在左侧菜单里
      setBehindContentView(R.layout.activity_left_menu );
     //[☆]获取侧滑控件
        SlidingMenu slidingMenu=super.getSlidingMenu();
        //[☆]设置右滑的菜单
        slidingMenu.setSecondaryMenu(R.layout.activity_left_menu);
        //[☆]核心参数1Mode
        slidingMenu.setMode(SlidingMenu.LEFT);//左侧彩单
//        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//左右都有彩单
         //[☆]核心参数2 TouchMode
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        /**SlidingMenu.TOUCHMODE_FULLSCREEN //[☆]设置触摸范围全屏
        /**SlidingMenu.TOUCHMODE_NONE //[☆]设置触摸范围
        /**SlidingMenu.TOUCHMODE_MARGIN //[☆]设置触摸范为边际
         *
         */
        //[☆]获取屏幕显示宽高
        Display display = getWindowManager().getDefaultDisplay();
       //[☆]设置允许显示的宽度
        int  v = (int) (display.getWidth() * 2f / 3);
       //[☆]差值=屏幕宽度-左侧菜单的宽度
        slidingMenu.setBehindOffset(v);
       initFragment();
    }

    private void initFragment() {
        //[☆]创建二级页面
        LeftMenuFragment leftMenuFragment = new LeftMenuFragment();
        ContentFragment contentFragment = new ContentFragment();
        //[☆]打开事务  suppor支持getDefaultFragmentManager getFragmentManger
       FragmentTransaction ft=super.getFragmentManager().beginTransaction();
        //[☆]添加两个片段
        /**
          *ft.add( containerviewid布局id,fragment片段，编号String)
         */
        ft.add(R.id.fl_left_menu,leftMenuFragment,LeftMenuFragment.class.getSimpleName());
        ft.add(R.id.fl_center,contentFragment,ContentFragment.class.getSimpleName());
        ft.commit();
    }

}
