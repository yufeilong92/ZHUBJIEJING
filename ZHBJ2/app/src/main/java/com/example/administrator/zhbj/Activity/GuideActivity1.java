package com.example.administrator.zhbj.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.Utils.CacheUtils;

/**
 * Created by Administrator on 2016/6/22.
 * 自己实现小圆点底层
 * <p/>
 * 引导用户熟悉功能
 */
public class GuideActivity1 extends Activity {

    private ViewPager viewPager;
    private Button btn_start;
    private MyAdapter myAdapter;
    private LinearLayout dots;
    private int mDotdistance;
    private ImageView iv_red_dot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide1);
        //[☆]初始化控件
        initview();
        initlisetener();

    }

    private void initview() {


        btn_start = (Button) findViewById(R.id.btn_start);
        viewPager = (ViewPager) findViewById(R.id.vp_viewpager);
        //[☆]创建适配器
        myAdapter = new MyAdapter();
        //[☆]设置适配器给高级控件
        viewPager.setAdapter(myAdapter);
        //[☆]在布局中动态添加灰点
        dots = (LinearLayout) findViewById(R.id.lv_gray_dots);
        iv_red_dot = (ImageView) findViewById(R.id.iv_red_dot);
        //[☆]遍历其图片
        for (int i = 0; i < myAdapter.getCount(); i++) {
            ImageView grayDot = new ImageView(this);
            //[☆]给控件添红点的布局颜色
            grayDot.setBackgroundResource(R.drawable.shape_gray_dot);
            //[☆]线性布局
            dots.addView(grayDot);
            //[☆]**.layoutParams用来java控制布局控件的间距
            //[☆]选择哪个类型看一下控件的那种布局里面
            if (i > 0) {//从第一图片
                //[☆]设置控件的宽高
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,//宽
                        LinearLayout.LayoutParams.WRAP_CONTENT//高
                );
                //[☆]设置其小圆点的距离
                params.leftMargin = 10;
                //[☆]添加控件
                grayDot.setLayoutParams(params);


            }

        }/**

         //[☆]获取任意两点的距离
         //[☆]getChildAt获取布局上面的子控件
         //*/
        //[☆]获取第一点的距离
        /**
         * view的工作原理
         * 需要监听onLayout执行完毕，如果该方法不执行，其getleft都是零
         */
        dots.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {


            /**
             *方法 布局网
             */
            @Override
            public void onGlobalLayout() {
                /**
                 * 获取任意两点
                 * getChildat 获取布局上面的子控件
                 *
                 */

                int left = dots.getChildAt(0).getLeft();
                int left1 = dots.getChildAt(1).getLeft();
                //[☆]两点的的距离
                mDotdistance = left1 - left;
                Log.i("onGlobalLayout: ", "mDotdistance" + mDotdistance);
                //[☆]删除监听
                dots.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            }
        });
    }

    private void initlisetener() {
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            /**
             *
             * @param position  当前页面的下标++position
             * @param positionOffset  页面切换的百分比
             * @param positionOffsetPixels  滚动的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 0) {
                    btn_start.setVisibility(View.GONE);
                }
                //[☆]小红点放到相对布局
              RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) iv_red_dot.getLayoutParams();
               //[☆]处理左右滑左滑为减，右滑为加
                int pixeds = (int) (position * mDotdistance + positionOffset * mDotdistance);
//                int pixeds = (int) (mDotdistance * positionOffset);
                Log.i("position: "+position,"positionOffset:"+positionOffset);
               //[☆]设置滑动的间距
                params.leftMargin=pixeds;
//                加载效果
                iv_red_dot.setLayoutParams(params);

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
                startActivity(new Intent(GuideActivity1.this, MAinActivitys.class));
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
