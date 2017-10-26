package com.yyc.mdrlib.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author: YaoYongChao
 * @time: 17-7-18 下午10:28
 * @description: 该类用于区分与Sigleton或其他@Scope的作用域
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
