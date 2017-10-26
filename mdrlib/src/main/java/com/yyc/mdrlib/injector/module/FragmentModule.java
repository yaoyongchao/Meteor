package com.yyc.mdrlib.injector.module;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.yyc.mdrlib.injector.scope.PerFragment;

import dagger.Module;
import dagger.Provides;

/**
 * @author: Page
 * @time: 17-8-16
 * @description:
 */

@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment){
        this.mFragment = fragment;
    }

    @Provides
    @PerFragment
    public Activity provideActivity(){
        return mFragment.getActivity();
    }
}
