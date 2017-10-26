package com.yyc.sinolube.ui.main.splash;

import com.yyc.mdrlib.base.BaseRxPresenter;
import com.yyc.sinolube.MyApplication;
import com.yyc.sinolube.data.local.SPManager;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * @author: Page
 * @time: 17-7-31
 * @description:
 */

public class SplashPresenter extends BaseRxPresenter<SplashContract.View> implements SplashContract.Presenter<SplashContract.View> {
    private final static int timerSecond = 1 * 1000;
    @Inject
    public SplashPresenter() {
    }

    /**
     * 定时
     */
    @Override
    public void timer() {
        Flowable.timer(timerSecond,TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        mView.doNextActivity(SPManager.isFirstLaunch(MyApplication.getsContext()));
                        SPManager.markFirstLaunch(MyApplication.getsContext());
                    }
                });
    }



}
