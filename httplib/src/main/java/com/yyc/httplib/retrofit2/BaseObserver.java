package com.yyc.httplib.retrofit2;

import android.content.Context;
import android.widget.Toast;

import com.yyc.httplib.entity.BaseEntity;
import com.yyc.httplib.utils.LogHttpLibUtil;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author: Page
 * @time: 17-8-7
 * @description: Response preprocessing.
 */
public abstract class BaseObserver<T> implements Observer<BaseEntity<T>> {

    private static final String TAG = "BaseObserver";
    public static final int SUCCESS_CODE = 1;
    private Context mContext;

    protected BaseObserver(Context context) {
        this.mContext = context.getApplicationContext();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onNext(@NonNull BaseEntity<T> tBaseEntity) {
        LogHttpLibUtil.d(TAG,"onNext");
        if (SUCCESS_CODE == tBaseEntity.getCode()) {
            T t = tBaseEntity.getData();
            onSuccess(t);
        } else {
            onFail(tBaseEntity.getMsg());
        }

    }

    @Override
    public void onError(@NonNull Throwable e) {
        LogHttpLibUtil.e(TAG,"onError");
        ExceptionHandle.handleException(e);
    }

    @Override
    public void onComplete() {
        LogHttpLibUtil.d(TAG,"onComplete");

    }

    public abstract void onSuccess(T t);

    public void onFail(String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
