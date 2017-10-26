package com.yyc.mdrlib.base;

import com.orhanobut.logger.Logger;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 下午9:00
 * @description: 基于Rx的Presenter封装,控制订阅的生命周期
 * unsubscribe() 这个方法很重要，
 * 因为在 subscribe() 之后， Observable 会持有 Subscriber 的引用，
 * 这个引用如果不能及时被释放，将有内存泄露的风险。
 *
 * 过时原因，是使用RxLifeCycle管理RxJava2的生命周期
 */
//@Deprecated
public class BaseRxPresenter <T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    private static final String TAG = "BaseRxPresenter";

    protected T mView;
    protected CompositeSubscription compositeSubscription;

    protected void unSubscribe() {
        Logger.i("unSubscribe");
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
    }

    protected void addSubscrebe(Subscription subscription) {
        Logger.i("addSubscrebe");
        if (compositeSubscription == null) {
            compositeSubscription = new CompositeSubscription();
        }
        compositeSubscription.add(subscription);
    }

    @Override
    public void attachView(T view) {
        this.mView = view;
        Logger.i("attachView --view："  + view);
    }

    @Override
    public void detachView() {
        Logger.i("detachView");
        this.mView = null;
        unSubscribe();
    }
}
