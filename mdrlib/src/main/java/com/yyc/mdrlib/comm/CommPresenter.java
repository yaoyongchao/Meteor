package com.yyc.mdrlib.comm;

import com.yyc.mdrlib.base.BaseContract;
import com.yyc.mdrlib.base.BaseRxPresenter;

import javax.inject.Inject;

/**
 * @author: Page
 * @time: 17-7-31
 * @description: 默认的Presenter
 */

public class CommPresenter extends BaseRxPresenter<BaseContract.BaseView> implements BaseContract.BasePresenter<BaseContract.BaseView> {
    @Inject
    public CommPresenter() {
    }

}
