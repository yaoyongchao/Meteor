package com.yyc.sinolube.inject.component;

import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.injector.scope.ActivityScope;
import com.yyc.sinolube.ui.main.login.LoginActivity;
import com.yyc.sinolube.ui.main.splash.SplashActivity;
import com.yyc.sinolube.ui.main.splash.WelcomeActivity;

import dagger.Component;

/**
 * @author: Page
 * @time: 17-7-31
 * @description: 公共的Activity Component;
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface CommActivityComponent {
    void inject(SplashActivity splashActivity);
    void inject(WelcomeActivity welcomeActivity);
    void inject(LoginActivity loginActivity);
}
