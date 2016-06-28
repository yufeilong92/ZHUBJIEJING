package com.example.administrator.zhbj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.Utils.CacheUtils;
import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by Administrator on 2016/6/22.
 * 引导用户熟悉功能
 */
public class GuideActivity extends Activity {

    private ViewPager viewPager;
    private Button btn_start;
    private MyAdapter myAdapter;
    private CirclePageIndicator circle_indicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        //[☆]初始化控件
        initview();
        initlisetener();
//        initView1();
    }

    private void initview() {

        circle_indicator = (CirclePageIndicator) findViewById(R.id.circle_indicastor);
        btn_start = (Button) findViewById(R.id.btn_start);
//        viewPager = (ViewPager) findViewById(R.id.vp_viewpager);
        //[☆]创建适配器
        myAdapter = new MyAdapter();
        //[☆]设置适配器给高级控件
        viewPager.setAdapter(myAdapter);
        //[☆]给指示器设置viewpager
        circle_indicator.setViewPager(viewPager);
        //[☆]设置滑动监听器取代原来的viewpager

    }
//    private void  initView1(){
//        viewPager= (ViewPager) findViewById(R.id.vp_viewpager);
//        btn_start= (Button) findViewById(R.id.btn_start);
//        myAdapter=new MyAdapter();
//        //[☆]设置高级控件，小圆点
//        viewPager.setAdapter(myAdapter);
//        //[☆]给指示器设置viewpager
//        circle_indicator.setViewPager(viewPager);}

    /**
     * 初始化页面控件
     */
    private void initlisetener() {
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    btn_start.setVisibility(View.GONE);
                }
            }

            /**
             *页面切完成
             * @param position 下标
             */
            @Override
            public void onPageSelected(int position) {
//      只有第三页才有按钮
                if (position != myAdapter.getCount() - 1) {
                    btn_start.setVisibility(View.GONE);
                } else {

                    btn_start.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                  if(state==0){
//                    btn_start.setVisibility(View.GONE);
//                }
            }
        });
        //[☆]按钮点击事件
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CacheUtils.saveString(getApplicationContext(), "isFisrstShow", "false");
                startActivity(new Intent(GuideActivity.this, MAinActivitys.class));
                finish();
            }
        });
    }

    /**
     * 显示图片内容
     * 适配器
     */
    private class MyAdapter extends PagerAdapter {
        private int[] imageResIds = new int[]{R.drawable.guide_1,
                R.drawable.guide_2, R.drawable.guide_3};

        @Override
        public int getCount() {
            return imageResIds.length;
        }

        /**
         * 官方建议
         *
         * @param view
         * @param object
         * @return
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        /**
         * x显示页面
         *
         * @param container 指viewpager
         * @param position  当前页面对应得下标
         * @return
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
//            return super.instantiateItem(container, position);
            ImageView imageView = new ImageView(getBaseContext());
            imageView.setBackgroundResource(imageResIds[position]);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 方法：移除显示
         *
         * @param container
         * @param position
         * @param object    页面控件ImageView
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
            container.removeView((View) object);
        }
    }

}
