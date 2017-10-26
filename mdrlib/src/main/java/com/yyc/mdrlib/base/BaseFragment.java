package com.yyc.mdrlib.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.components.support.RxFragment;
import com.yyc.mdrlib.BaseApplication;
import com.yyc.mdrlib.injector.component.ApplicationComponent;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author: Page
 * @time: 17-8-16
 * @description:
 */

public abstract class BaseFragment<P extends BaseRxPresenter> extends RxFragment implements BaseContract.BaseView {

    public Context mContext;
    private View rootView;
    //是否可见状态
    private boolean isVisible;
    //标志位，标志Fragment已经初始化完成。
    private boolean isPrepared;
    //是否第一次加载完
    private boolean isFirstLoad = true;
    protected Unbinder unbinder;

    @Inject
    public P mPresenter;

    @Override
    public void onAttach(Context context) {
        this.mContext = context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        isFirstLoad = true;
        //绑定View
        if (getLayoutId() == 0)
            return super.onCreateView(inflater, container, savedInstanceState);
        if(rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false);

        unbinder = ButterKnife.bind(this,rootView);
        isPrepared = true;
        componentInject(BaseApplication.getInstance().getAppComponent());//依赖注入
        if(mPresenter != null)
            mPresenter.attachView(this);

        initView();
        //初始化获取数据, 在此方法中获取数据不是懒加载模式
        initData();
        //在此方法中获取数据为懒加载模式,如不需要懒加载,请在initEventAndData获取数据,GankFragment有使用实例
        lazyLoad();
        return rootView;
    }


    protected abstract void componentInject(ApplicationComponent applicationComponent);

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
     *//*
    protected abstract void bindEvent();*/

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 懒加载数据
     */
    protected abstract void lazyLoadData();

    protected View getRootView() {
        return rootView;
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected void onInvisible(){}

    protected void lazyLoad(){
        if(!isPrepared || !isVisible || !isFirstLoad) return;
        isFirstLoad = false;
        lazyLoadData();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(getUserVisibleHint()){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            isVisible = true;
            onVisible();
        }else {
            isVisible = false;
            onInvisible();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder  = null;

        if(mPresenter != null)
            mPresenter.detachView();
        this.mPresenter = null;
    }
}
