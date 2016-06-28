package com.example.administrator.zhbj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.Utils.CacheUtils;


/**
 * Created by Administrator on 2016/6/21.
 * 闪屏界面 control
 * 开发一个Activity
 */
public class SplashActivity extends Activity {

    private RelativeLayout rl_root;
    private AnimationSet animationSet;
    private RotateAnimation rotateAnimation;
    private AlphaAnimation alphaAnimation;
    private ScaleAnimation scaleAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //[☆]动画集合 同时播放动画
        initview();
        initlistener();
    }

    private void initview() {
        rl_root = (RelativeLayout) findViewById(R.id.rl_root);
        /**
         * AnimationSet:动画 集合，同时播放动画
         * new AnimationSet(shareInterpolator:加速效果)
         *旋转动画、
         * RotateAnimation
         * 注意有中点和角度（0--360）
         * 中点：x坐标和y坐标
         *  0.5F坐标轴的位置
         */


         animationSet =   new AnimationSet(true);
        //[☆]旋转RotateAnimation
        //[☆]参数设置（开始角度，终止角度 pivot中心 xtype）
        rotateAnimation = new RotateAnimation(0, 360, RotateAnimation.RELATIVE_TO_SELF, 0.5F, RotateAnimation.RELATIVE_TO_SELF,0.5F);
        //[☆]设置动画时间
        rotateAnimation.setDuration(3000);
        rotateAnimation.setFillAfter(true);
        //[☆]添加动画
        animationSet.addAnimation(rotateAnimation);

        /**
         *
         * 透明动画
         *透明度：开始和结束（0~~1）
         * AlphoaAnimation
         *new AlphaAnimation(fromAlpha,开始透明度，toAlpha 结束透明度)
         */
        alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        //[☆]设置动画结束时间
        alphaAnimation.setDuration(3000);
        //[☆]动画结束状态保存动画状态
        alphaAnimation.setFillAfter(true);
        //[☆]添加动画
        animationSet.addAnimation(alphaAnimation);
        /**
         * 尺寸伸缩效果动画
         * ScaleAnimation
         * 1.缩放的中心，缩放的x,y
         *    ** ScaleAnimation.RELATIVE_TO_SELF表述以其自生为中心
         *    ** 0, 1f 开始于结束
         * 设置动画是add几次有几次动画效果
         */
        //x坐标
//y坐标
        scaleAnimation = new ScaleAnimation(0, 1f,//x坐标
                0, 1f,//y坐标
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f, ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(3000);
        scaleAnimation.setFillAfter(true);
        animationSet.addAnimation(scaleAnimation);
//        /**画面转换位置移动变化
//         * TranslateAnimation
//         */
//        TranslateAnimation translateAnimation = new TranslateAnimation(
//                1F,1F,0.1F,0.1F
//        );
//        translateAnimation.setDuration(3000);
//        animationSet.addAnimation(translateAnimation);
        rl_root.startAnimation(animationSet);
    }
    private void initlistener(){
     animationSet.setAnimationListener(new Animation.AnimationListener() {
         @Override
         public void onAnimationStart(Animation animation) {

         }

         @Override
         public void onAnimationEnd(Animation animation) {
             Log.i("end","动画结束了");
//             Intent :意图1》打开界面或者服务2》传值Map  put get 3>广播
             String isFisrstShow = CacheUtils.getstring(SplashActivity.this,"isFisrstShow");
            Class<?> page="false".equals(isFisrstShow)?MAinActivitys.class:GuideActivity1.class;
//             if ("true".equals(isFisrstShow)){
//                 startActivity(new Intent(SplashActivity.this,MAinActivitys.class));
//             }else {
//                 startActivity(new Intent(SplashActivity.this,GuideActivity.class));
//
//             }
             System.out.println(isFisrstShow+">>>>>");
             System.out.println(page+">>>>>>");
             startActivity(new Intent(SplashActivity.this,page));
             finish();
         }

         @Override
         public void onAnimationRepeat(Animation animation) {

         }
     });
    }
}
