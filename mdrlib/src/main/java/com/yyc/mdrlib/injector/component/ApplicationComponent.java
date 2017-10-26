package com.yyc.mdrlib.injector.component;

import android.content.Context;

import com.yyc.mdrlib.api.ApiService;
import com.yyc.mdrlib.injector.module.ApiServiceModule;
import com.yyc.mdrlib.injector.module.ApplicationModule;
import com.yyc.mdrlib.injector.module.OkHttpModule;
import com.yyc.mdrlib.injector.module.RetrofitModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 上午10:43
 * @description:
 */
//@PerApplication
@Singleton
@Component(modules = {ApplicationModule.class, OkHttpModule.class, RetrofitModule.class,ApiServiceModule.class })
public interface ApplicationComponent {
    Context getContext();

    ApiService apiservice();

    OkHttpClient getOkhttpClient();
    Retrofit getRetrofit();

//    Activity getActivity();
}
