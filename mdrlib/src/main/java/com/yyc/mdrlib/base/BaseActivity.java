package com.yyc.mdrlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.yyc.mdrlib.BaseApplication;
import com.yyc.mdrlib.R;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.utils.ActivityManager;
import com.yyc.mdrlib.utils.ActivityUtils;
import com.yyc.mdrlib.utils.LoggerUtil;

import org.w3c.dom.Text;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: Page
 * @time: 17-7-24
 * @description: Activity 基类
 */

public abstract class BaseActivity<P extends BaseRxPresenter> extends RxAppCompatActivity
implements BaseContract.BaseView{
    private final static int BASE_VIEW_ID = R.layout.base_layout;
    public Context context;

    protected Unbinder unbinder;
    private LinearLayout rootView;
    private Toolbar toolbar;
    private OnClickLeftListener onClickLeftListener;
    private OnClickRighttListener onClickRighttListener;
    private ImageButton imgBtnRight;
    private ImageButton imgBtnLeft;
    private TextView tvTitle;

    @Inject
    public P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        /*设置全屏*/
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
//                WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        setContentView(R.layout.base_layout);


        setContentView(initRootView());
        super.onCreate(savedInstanceState);

//        Logger.i("oncreate");
        context = this;
        unbinder = ButterKnife.bind(this);
        componentInject(BaseApplication.getInstance().getAppComponent());//依赖注入
        if(mPresenter != null)
            mPresenter.attachView(this);

        initView();
        initData();
        Logger.i("mPresenter--" + mPresenter);
        ActivityManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        rootView = (LinearLayout) findViewById(R.id.base_view);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);

        initToolBar();
        if (getLayoutId() != 0)
            rootView.addView(getLayoutInflater().inflate(getLayoutId(),null));


//        DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(BaseApplication.getInstance()))
//                .activityModule(getActivityModule())
//                .build();
    }

  /*  protected ActivityComponent getActivityComponent(){
        return  DaggerActivityComponent.builder()
                .applicationComponent(BaseApplication.getAppComponent())
                .activityModule(getActivityModule())
                .build()
                ;
    }*/

    /*protected ActivityModule getActivityModule(){
        return new ActivityModule(this);
    }*/

    private View initRootView() {
        rootView = (LinearLayout) getLayoutInflater().inflate(BASE_VIEW_ID,null);
//        rootView = (LinearLayout) findViewById(R.id.base_view);
        toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        imgBtnRight = (ImageButton) rootView.findViewById(R.id.imgbtn_title_right);
        initToolBar();
        if (getLayoutId() != 0) {
            View subView = getLayoutInflater().inflate(getLayoutId(),null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            subView.setLayoutParams(params);
            rootView.addView(subView);
        }

        return rootView;
    }

    private void initToolBar() {
//        toolbar.setTitle("中心");
//        toolbar.setSubtitle("subTitle");

        // 显示应用的Logo
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayUseLogoEnabled(false);
        //是否显示应用标题
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        imgBtnRight = (ImageButton) rootView.findViewById(R.id.imgbtn_title_right);
        imgBtnLeft = (ImageButton) rootView.findViewById(R.id.imgbtn_title_left);
        tvTitle = (TextView) rootView.findViewById(R.id.toolbar_title);

        imgBtnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickLeftListener == null) {
                    finish();
                }else {
                    onClickLeftListener.onClickLeft(v);
                }
            }
        });

        imgBtnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickRighttListener != null)
                    onClickRighttListener.onClickRight(v);
            }
        });
    }

    public void isShowToolbar(int visibility){
        toolbar.setVisibility(visibility);
    }
    public void setToolbarTitle(String str) {
        if (null != tvTitle)
            tvTitle.setText(str);
    }

    public Toolbar getToolBar() {
        return toolbar;
    }


        /**
         * @param: [resId]
         * @return: void
         * @description: 设置右边图标
         */
    public void setLeftIcon(@DrawableRes int resId) {
        if(toolbar != null)
            imgBtnLeft.setBackgroundResource(resId);
    }

        /**
         * @param: [resId]
         * @return: void
         * @description: 设置左边图标
         */
    public void setRightIcon(@DrawableRes int resId) {
        if(toolbar != null)
            imgBtnRight.setBackgroundResource(resId);
    }


        /**
         * @param: [visibility]
         * @return: void
         * @description: 是否隐藏左边icon
         */
    public void isShowLeftIcon(int visibility) {
        if(toolbar != null)
            imgBtnLeft.setVisibility(visibility);
    }

    /**
     * @param: [visibility]
     * @return: void
     * @description: 是否隐藏右边边icon
     */
    public void isShowRightIcon(int visibility) {
        imgBtnRight.setVisibility(visibility);
    }

    protected abstract void componentInject(ApplicationComponent applicationComponent);

   /* public BaseActivityComponent getActivityComponent() {
        if (baseActivityComponent == null) {
            baseActivityComponent = DaggerBaseActivityComponent.builder()
                    .applicationComponent(BaseApplication.getAppComponent())
                    .activityModule(new ActivityModule(this))
                    .build();

        }
        return baseActivityComponent;
    }*/


       /**
        * @param: []
        * @return: void
        * @description: 获取跟布局
        */
   public View getRootView() {
       return rootView;
   }


    /**
     * 初始化注解
     */
//    protected abstract void initInject();

    /**
     * 获取布局控件
     */
    protected abstract int getLayoutId();

    /**
     * 初始化子View
     */
    protected abstract void initView();

    /**
     * 绑定事件
     *//*onVisible
    protected abstract void bindEvent();*/

    /**
     * 初始化数据
     */
    protected abstract void initData();


    /*************************  Fragment相关  **********************/
    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * 添加 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void addFragment(int containerViewId, Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        // 设置tag，不然下面 findFragmentByTag(tag)找不到
        fragmentTransaction.add(containerViewId, fragment, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(containerViewId, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * 替换 Fragment
     *
     * @param containerViewId
     * @param fragment
     */
    protected void replaceFragment(int containerViewId, Fragment fragment, String tag) {
        if (getSupportFragmentManager().findFragmentByTag(tag) == null) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            // 设置tag
            fragmentTransaction.replace(containerViewId, fragment, tag);
            fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            // 这里要设置tag，上面也要设置tag
            fragmentTransaction.addToBackStack(tag);
            fragmentTransaction.commit();
        } else {
            // 存在则弹出在它上面的所有fragment，并显示对应fragment
            getSupportFragmentManager().popBackStack(tag, 0);
        }
    }

    @Override
    protected void onDestroy() {
        // 取消注解绑定
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder  = null;

        if(mPresenter != null)
            mPresenter.detachView();
        this.mPresenter = null;

        ActivityManager.getInstance().removeActivity(this);
    }

    public interface OnClickLeftListener{
        void onClickLeft(View v);
    }

    public void setOnClickLeftListener(OnClickLeftListener onClickLeftListener){
        this.onClickLeftListener = onClickLeftListener;
    }

    public void setOnClickRighttListener(OnClickRighttListener onClickRighttListener) {
        this.onClickRighttListener = onClickRighttListener;
    }

    public interface OnClickRighttListener{
        void onClickRight(View v);
    }

    /**
     * @description: 跳转activity
     */
    public void doActivity(Class<?> activity) {
        ActivityUtils.toActivity(this,activity);
    }

    /**
     * @description: 跳转带参数 activity
     */
    public void doActivity(Class<?> activity,String... str) {
        ActivityUtils.toActivityAddParam(this,activity,str);
    }

}
