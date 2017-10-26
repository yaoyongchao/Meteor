package com.yyc.sinolube.ui.main.splash;

import com.yyc.mdrlib.base.BaseContract;

/**
 * @author: Page
 * @time: 17-7-28
 * @description:
 */

public interface SplashContract {
    interface View extends BaseContract.BaseView {

        void doNextActivity(boolean isFristLaunch);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void timer();
    }

}
