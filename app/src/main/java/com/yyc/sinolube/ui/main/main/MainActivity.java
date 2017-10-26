package com.yyc.sinolube.ui.main.main;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yyc.mdrlib.base.BaseActivity;
import com.yyc.mdrlib.comm.CommPresenter;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.widget.NoScrollViewPager;
import com.yyc.sinolube.R;
import com.yyc.sinolube.ui.demo.MeFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<CommPresenter> {
    @BindView(R.id.viewpager)
    NoScrollViewPager viewpager;
    @BindView(R.id.tablayout)
    TabLayout tablayout;


    //Tab 文字
    private final int[] TAB_TITLES = new int[]{R.string.me,R.string.me,R.string.me,R.string.me};
    //Tab 图片
    private final int[] TAB_IMGS = new int[]{R.drawable.tab_me_selector,R.drawable.tab_me_selector,R.drawable.tab_me_selector,R.drawable.tab_me_selector};
    //Fragment 数组
    private final Fragment[] TAB_FRAGMENTS = new Fragment[] {new MeFragment(),new MeFragment(),new MeFragment(),new MeFragment()};
    //Tab 数目
    private final int COUNT = TAB_TITLES.length;

    private MyViewPagerAdapter adapter;

    @Override
    protected void componentInject(ApplicationComponent applicationComponent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
//        isShowToolbar(View.GONE);
        isShowLeftIcon(View.GONE);
        setTabs(tablayout,this.getLayoutInflater(),TAB_TITLES,TAB_IMGS);
        adapter = new MyViewPagerAdapter(getSupportFragmentManager());
        viewpager.setAdapter(adapter);
        viewpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewpager));
    }

    @Override
    protected void initData() {

    }


    /**
     * @description: 设置添加Tab
     */
    private void setTabs(TabLayout tabLayout, LayoutInflater inflater, int[] tabTitlees, int[] tabImgs){
        for (int i = 0; i < tabImgs.length; i++) {
            TabLayout.Tab tab = tabLayout.newTab();
            View view = inflater.inflate(R.layout.tab_custom,null);
            tab.setCustomView(view);

            TextView tvTitle = (TextView)view.findViewById(R.id.tv_tab);
            tvTitle.setText(tabTitlees[i]);
            ImageView imgTab = (ImageView) view.findViewById(R.id.img_tab);
            imgTab.setImageResource(tabImgs[i]);
            tabLayout.addTab(tab);

        }
    }

    /**
     * @description: ViewPager 适配器
     */
    private class MyViewPagerAdapter extends FragmentPagerAdapter {
        public MyViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return TAB_FRAGMENTS[position];
        }

        @Override
        public int getCount() {
            return COUNT;
        }
    }
}
