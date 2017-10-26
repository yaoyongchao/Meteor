package com.yyc.mdrlib.injector.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 上午10:33
 * @description:
 */
@Module
public class ApplicationModule {
    private  Context context;

    public ApplicationModule(Context context) {
        context = context;
    }

    @Singleton
    @Provides
    Context provideContext() {
        return context;
    }
}
