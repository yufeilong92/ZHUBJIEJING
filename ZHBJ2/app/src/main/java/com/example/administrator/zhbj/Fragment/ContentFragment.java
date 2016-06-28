package com.example.administrator.zhbj.Fragment;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.administrator.zhbj.Constant.GloableConstant;
import com.example.administrator.zhbj.Fragment.pager.BasePager;
import com.example.administrator.zhbj.Fragment.pager.GOVAffarisPager;
import com.example.administrator.zhbj.Fragment.pager.HomePager;
import com.example.administrator.zhbj.Fragment.pager.NewsConterPager;
import com.example.administrator.zhbj.Fragment.pager.SetTingPager;
import com.example.administrator.zhbj.Fragment.pager.SmartServicePager;
import com.example.administrator.zhbj.R;
import com.example.administrator.zhbj.Utils.NoScrollVirwPager;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/6/24.
 * 二级页面
 */
public class ContentFragment extends BaseFragment {

    private RadioGroup mRadiogroup;
    private int positionPager = 0;

    private NoScrollVirwPager mViewPager;


    /**
     * 方法 初始化二级页面的控制的地方
     *
     * @RETUREN
     */

    @Override
    public View initview() {
        View view = View.inflate(mActivity, R.layout.frament_conect_menu, null);
        //[☆]viewpager 高级控件需要adpaer 没有就显示白板
        mViewPager = (NoScrollVirwPager) view.findViewById(R.id.vp_viewpager1);
        mRadiogroup = (RadioGroup) view.findViewById(R.id.rg_raidogroup);

        ContentPagerAdapter1 pagerAdapter = new ContentPagerAdapter1();
        //[☆]设置组viewpager显示
        mViewPager.setAdapter(pagerAdapter);
        setSlidngMenuEnable(0);
        initListeners();
        return view;
    }

    /**
     * 二级初始化页面的监听器
     */
    private void initListeners() {

        mRadiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            /**
             *
             * @param radioGroup
             * @param i 选中的id
             */
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_home:
                        positionPager = 0;
                        break;
                    case R.id.rb_new_conter:
                        positionPager = 1;
                        break;
                    case R.id.rb_smart_service:
                        positionPager = 2;
                        break;
                    case R.id.rb_gov_affair:
                        positionPager = 3;
                        break;
                    case R.id.rb_set:
                        positionPager = 4;
                        break;
                }


//           切换viewpager的显示页面

                mViewPager.setCurrentItem(positionPager, false);//设置显示第几个按键


                setSlidngMenuEnable(positionPager);
            }


        });
    }

    /**
     * 根据下标控件来控制侧滑
     *
     * @param positionPager
     */
    private void setSlidngMenuEnable(int positionPager) {
//        //           mViewPager.setCurrentItem(item页面下标,);
////      ViewPager.setCurrentItem(positionPager,false);
////                修改slidngMenu触摸范围0-4不能菜单123支持打开菜单
////                片段可以获取自己所在的activity
//        MAinActivitys mAinActivitys = (MAinActivitys) mActivity;
////                获取侧滑控件
//        SlidingMenu slidingMenu = mAinActivitys.getSlidingMenu();
//        if (positionPager == 0 || positionPager == 4) {
//            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);//触摸范围为0
//        } else {
//            slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);//触摸范围为边界
//        }
        /** 发出一个信号叫一级页面 切换 可用不可用的属性*/
        Intent intent = new Intent();
        if (positionPager == 0 || positionPager == 4) {
            intent.setAction(GloableConstant.ACION_SLIDING_DISABLE);
        } else {
            intent.setAction(GloableConstant.ACION_SLIDING_ENABLE);
        }
        //[☆]发送广播
        mActivity.sendBroadcast(intent);
    }


    public class ContentPagerAdapter1 extends PagerAdapter {
        private ArrayList<BasePager> mPagers;

        public ContentPagerAdapter1() {
            mPagers = new ArrayList<>();
            mPagers.add(new HomePager(mActivity));// 首页
            mPagers.add(new NewsConterPager(mActivity));// 新闻中心
            mPagers.add(new SmartServicePager(mActivity));// 智慧服务
            mPagers.add(new GOVAffarisPager(mActivity));// 政务
            mPagers.add(new SetTingPager(mActivity));// 设置
        }

        @Override
        public int getCount() {
            return mPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            BasePager pager = mPagers.get(position);
            pager.initData();// 加载数据
            View rootView = pager.mRootView;
            container.addView(rootView);// 添加到二级面页面的ViewPager来显示
            return rootView;// 返回三级页面的内容
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            container.removeView((View) object);
        }

    }
}
