package com.yyc.sinolube.ui.main.splash;

import android.view.View;
import android.widget.ImageView;

import com.orhanobut.logger.Logger;
import com.yyc.mdrlib.base.BaseActivity;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.sinolube.R;
import com.yyc.sinolube.inject.component.DaggerCommActivityComponent;
import com.yyc.sinolube.ui.main.login.LoginActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class SplashActivity extends BaseActivity<SplashPresenter> implements SplashContract.View {


    @BindView(R.id.img)
    ImageView img;

    @Override
    protected void componentInject(ApplicationComponent applicationComponent) {
        DaggerCommActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initView() {
        isShowToolbar(View.GONE);
//        Logger.i("api:" , apiService);

        mPresenter.timer();

    }

    @Override
    protected void initData() {

    }


    @Override
    public void doNextActivity(boolean isFristLaunch) {
        Logger.i("isIFrstLaunch--" + isFristLaunch);
        doActivity(isFristLaunch ? WelcomeActivity.class : LoginActivity.class);
    }


    @OnClick(R.id.img)
    public void onViewClicked() {
    }
}
