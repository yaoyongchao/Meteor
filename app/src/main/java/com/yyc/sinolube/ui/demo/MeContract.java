package com.yyc.sinolube.ui.demo;

import android.widget.EditText;

import com.yyc.mdrlib.base.BaseContract;

/**
 * Created by yaoyongchao on 17-11-6.
 */

public class MeContract {
    interface View extends BaseContract.BaseView {
            void ontest(String str);
    }

    interface Presenter<T> extends BaseContract.BasePresenter<T> {
            void register(EditText edtAccount, EditText edtPassword);
    }
}
