package com.yyc.sinolube.ui.main.splash;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.yyc.mdrlib.base.BaseActivity;
import com.yyc.mdrlib.comm.CommPresenter;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.sinolube.R;
import com.yyc.sinolube.ui.main.login.LoginActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class WelcomeActivity extends BaseActivity<CommPresenter> {


    @BindView(R.id.viewpager)
    ViewPager viewpager;
    Button btnEnterIntoApp;
    AdapterViewpager adapterViewpager;
    private int[] bgRes = new int[]{R.drawable.bg_guide_one,R.drawable.bg_guide_two};


    @Override
    protected void componentInject(ApplicationComponent applicationComponent) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_welcome;
    }

    @Override
    protected void initView() {
        isShowToolbar(View.GONE);

        List<View> list = new ArrayList<View>();
        View view =  null;
        for (int i = 0; i < bgRes.length ; i++) {
            view = getLayoutInflater().inflate(R.layout.item_viewpager_welcome,null);
            view.setBackgroundResource(bgRes[i]);

            if(i == bgRes.length - 1) {
                btnEnterIntoApp = (Button) view.findViewById(R.id.btn_enter_into_app);
                btnEnterIntoApp.setVisibility(View.VISIBLE);
                btnEnterIntoApp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        doActivity(LoginActivity.class);
                    }
                });
            }
            list.add(view);
        }

        adapterViewpager = new AdapterViewpager(list);
        viewpager.setAdapter(adapterViewpager);


    }

    @Override
    protected void initData() {

    }


    public class AdapterViewpager extends PagerAdapter {
        private List<View> mViewList;

//        @Inject
        public AdapterViewpager(List<View> mViewList) {
            this.mViewList = mViewList;
        }

        @Override
        public int getCount() {//必须实现
            return mViewList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {//必须实现
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {//必须实现，实例化
            container.addView(mViewList.get(position));
            return mViewList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {//必须实现，销毁
            container.removeView(mViewList.get(position));
        }


    }

}
