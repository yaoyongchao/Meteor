package com.yyc.mdrlib.injector.module;

import com.yyc.mdrlib.utils.InterceptorUtil;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * @author: Page
 * @time: 17-7-25
 * @description:
 */
@Module
public class OkHttpModule {

    @Provides
    @Singleton
    OkHttpClient provideProOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(15 * 1000L, TimeUnit.MILLISECONDS)//15
                .readTimeout(20 * 1000L, TimeUnit.MILLISECONDS)//20
                .writeTimeout(30 * 1000L, TimeUnit.MILLISECONDS)//15
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                /*.cache(new Cache(new File(CacheUtil.getHttpCacheDir(context), Api.OKCLIENT_DISK_CACHE_NAME),
                        Api.OKCLIENT_DISK_CACHE_SIZE))*/
                .build(); //设置缓存目录和20M缓存

    }
}
