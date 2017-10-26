package com.yyc.mdrlib.injector.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author: Page
 * @time: 17-8-16
 * @description:
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerFragment {
}