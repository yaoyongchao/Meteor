package com.yyc.sinolube.ui.demo.one;

import android.widget.EditText;

import com.yyc.mdrlib.base.BaseContract;

/**
 * Created by yaoyongchao on 17-11-6.
 */

public interface OneContract {
    interface View extends BaseContract.BaseView {
        void loginSuccess(String str);

    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {

        void login(EditText edtAccount, EditText edtPassword);
    }
}
