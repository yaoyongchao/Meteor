package com.yyc.mdrlib.injector.module;

import com.yyc.mdrlib.api.ApiService;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * @author: Page
 * @time: 17-7-21
 * @description: 主要提供ApiService。
 */
@Module
public class ApiServiceModule {

    @Provides
//    @PerApplication
    @Singleton
    ApiService provideApiService(@Named("test") Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }


}
