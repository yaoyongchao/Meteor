package com.yyc.sinolube.ui.main.login;

import android.widget.EditText;

import com.yyc.mdrlib.api.ApiService;
import com.yyc.mdrlib.base.BaseRxPresenter;
import com.yyc.mdrlib.utils.LoggerUtil;

import javax.inject.Inject;

/**
 * @author: Page
 * @time: 17-7-31
 * @description:
 */

public class LoginPresenter extends BaseRxPresenter<LoginContract.View> implements LoginContract.Presenter<LoginContract.View> {
    @Inject
    ApiService api;

    @Inject
    public LoginPresenter() {
    }

    @Override
    public void login(EditText edtAccount, EditText edtPassword) {
        LoggerUtil.i("欢迎登录" + api);
        mView.loginSuccess("ok");
    }
}
