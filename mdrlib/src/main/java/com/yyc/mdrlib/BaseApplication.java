package com.yyc.mdrlib;

import android.app.Application;
import android.content.Context;

import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.injector.component.DaggerApplicationComponent;
import com.yyc.mdrlib.injector.module.ApiServiceModule;
import com.yyc.mdrlib.injector.module.ApplicationModule;
import com.yyc.mdrlib.injector.module.OkHttpModule;
import com.yyc.mdrlib.injector.module.RetrofitModule;
import com.yyc.mdrlib.utils.LoggerUtil;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 上午11:41
 * @description:
 */

public class BaseApplication extends Application {
    private static Context sContext;
    private static BaseApplication sInstance;
    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initComponent();
        //初始化Log
        LoggerUtil.getInstance().initLogger(true);
        sContext = getApplicationContext();
    }

    public static BaseApplication getInstance() {
        return sInstance;
    }

    private void initComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .apiServiceModule(new ApiServiceModule())
                .okHttpModule(new OkHttpModule())
                .retrofitModule(new RetrofitModule())
                .build();
    }

    /**
     * @author: YaoYongChao
     * @date: 17-7-19 下午3:41
     * @description: 向外界的依赖提供ApplicationComponent
     */
    public static ApplicationComponent getAppComponent() {
        return applicationComponent;
    }


    public static Context getsContext() {
        return sContext;
    }


}
