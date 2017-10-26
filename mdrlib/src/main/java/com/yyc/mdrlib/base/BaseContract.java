package com.yyc.mdrlib.base;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 下午8:31
 * @description: MVP Contract 基类
 */

public interface BaseContract {
    interface BasePresenter<T> {

        //绑定view 这个方法将会在activity中调用
        void attachView(T view);
        //解绑
        void detachView();
    }

    interface BaseView {

        /*void showError();

        void complete();
*/
    }
}
