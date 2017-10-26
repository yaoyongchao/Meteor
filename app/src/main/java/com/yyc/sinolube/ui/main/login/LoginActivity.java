package com.yyc.sinolube.ui.main.login;

import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.View;
import android.widget.Button;

import com.yyc.mdrlib.base.BaseActivity;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.utils.LoggerUtil;
import com.yyc.sinolube.R;
import com.yyc.sinolube.inject.component.DaggerCommActivityComponent;
import com.yyc.sinolube.ui.main.main.MainActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class LoginActivity extends BaseActivity<LoginPresenter> implements LoginContract.View {


    @BindView(R.id.iedt_account)
    TextInputEditText iedtAccount;
    @BindView(R.id.iedt_password)
    TextInputEditText iedtPassword;
    @BindView(R.id.acckb)
    AppCompatCheckBox acckb;
    @BindView(R.id.btn_login)
    Button btnLogin;

    @Override
    protected void componentInject(ApplicationComponent applicationComponent) {
        DaggerCommActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        isShowToolbar(View.GONE);
    }

    @Override
    protected void initData() {

    }

    @OnClick(R.id.btn_login)
    public void onViewClicked() {
        LoggerUtil.i("我点击了----");
        mPresenter.login(iedtAccount,iedtPassword);
    }

    @Override
    public void loginSuccess(String str) {
        doActivity(MainActivity.class);
    }
}
