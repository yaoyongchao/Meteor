package com.yyc.sinolube.ui.demo;

import android.widget.EditText;

import com.yyc.mdrlib.base.BaseRxPresenter;
import com.yyc.sinolube.ui.demo.MeContract.Presenter;
import com.yyc.sinolube.ui.demo.MeContract.View;

import javax.inject.Inject;

/**
 * Created by yaoyongchao on 17-11-6.
 */

public class MePresenter extends BaseRxPresenter<View> implements Presenter<View> {

    @Inject
    public MePresenter() {
    }

    @Override
    public void register(EditText edtAccount, EditText edtPassword) {
        try {
            Thread.sleep(2000);
            mView.ontest("注册陈工");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
