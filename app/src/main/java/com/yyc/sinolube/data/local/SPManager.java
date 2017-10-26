package com.yyc.sinolube.data.local;

import android.content.Context;

import com.yyc.mdrlib.utils.SPUtils;

/**
 * @author: Page
 * @time: 17-8-2
 * @description:
 */

public class SPManager {
    private static final String IS_FIRST_LAUNCH = "lube";

    public static boolean isFirstLaunch(Context context) {
        return (boolean) SPUtils.getSp(context,IS_FIRST_LAUNCH,true);
    }

    public static void markFirstLaunch(Context context) {
        SPUtils.setSP(context,IS_FIRST_LAUNCH,false);
    }
}
