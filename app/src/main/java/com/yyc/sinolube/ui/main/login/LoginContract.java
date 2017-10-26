package com.yyc.sinolube.ui.main.login;

import android.widget.EditText;

import com.yyc.mdrlib.base.BaseContract;

/**
 * @author: Page
 * @time: 17-7-28
 * @description:
 */

public interface LoginContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(String str);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void login(EditText edtAccount, EditText edtPassword);
    }

}
