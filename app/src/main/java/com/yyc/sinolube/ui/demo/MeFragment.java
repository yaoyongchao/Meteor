package com.yyc.sinolube.ui.demo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.yyc.mdrlib.base.BaseFragment;
import com.yyc.mdrlib.injector.component.ApplicationComponent;
import com.yyc.mdrlib.utils.ToastUtils;
import com.yyc.sinolube.R;
import com.yyc.sinolube.inject.component.DaggerCommActivityComponent;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: Page
 * @time: 17-8-15
 * @description:
 */

public class MeFragment extends BaseFragment<MePresenter> implements MeContract.View {

    @BindView(R.id.ed1)
    EditText ed1;
    @BindView(R.id.ed2)
    EditText ed2;
    @BindView(R.id.btnc)
    Button btnc;


    @Override
    protected void componentInject(ApplicationComponent applicationComponent) {
        DaggerCommActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_me;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected void lazyLoadData() {

    }

    @Override
    public void ontest(String str) {
        ToastUtils.showShort(str);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btnc)
    public void onViewClicked() {
        mPresenter.register(ed1,ed2);
    }

}
