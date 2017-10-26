package com.yyc.mdrlib.injector.component;

import android.app.Activity;

import com.yyc.mdrlib.injector.module.ActivityModule;
import com.yyc.mdrlib.injector.scope.PerActivity;

import dagger.Component;

/**
 * Created by leo on 2017/5/16.
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

}
