package com.yyc.mdrlib.utils;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.FormatStrategy;
import com.orhanobut.logger.Logger;
import com.orhanobut.logger.PrettyFormatStrategy;

/**
 * @author: YaoYongChao
 * @time: 17-7-19 下午3:02
 * @description: Logger简单封装配置
 */

public class LoggerUtil {
    private static volatile LoggerUtil instance;
    private FormatStrategy formatStrategy;
    private PrettyFormatStrategy.Builder loggerBuilder;
    private boolean defaultIsShow = true;
    private static  final String TAG = "MdrLog";

    public LoggerUtil() {
    }

    public static LoggerUtil getInstance() {
        if (instance == null) {
            synchronized (LoggerUtil.class) {
                if (instance == null) {
                    instance = new LoggerUtil();
                }
            }
        }

        return instance;
    }

    /**
     * @author: YaoYongChao
     * @date: 17-7-19 下午3:04
     * @param: Tag
     * @return: 
     * @description: setting Logger
     */
    private void setLogger(String tag) {

        FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .tag(tag) // 全局tag
                .build();
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy) {
            @Override
            public boolean isLoggable(int priority, String tag) {
//                return BuildConfig.DEBUG;
                return defaultIsShow;
            }
        });


       /* loggerBuilder = PrettyFormatStrategy.newBuilder();
        formatStrategy = loggerBuilder.showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(3)         // (Optional) How many method line to show. Default 2
                .methodOffset(7)        // (Optional) Hides internal method calls up to offset. Default 5
 //               .logStrategy() // (Optional) Changes the log strategy to print out. Default LogCat
//                .tag("yyc tag")   // (Optional) Global tag for every log. Default PRETTY_LOGGER
                .build();
        loggerBuilder.tag(tag);
        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy));*/
    }

    public  void initLogger(boolean isShowLog, String tag) {
        if (isShowLog)
            setLogger(tag);
    }

    public void initLogger(boolean isShowLog) {
        if (isShowLog)
            initLogger();

    }

    public void initLogger() {
        initLogger(defaultIsShow , TAG);
    }

    public static void i(String strLog) {
        Logger.i(strLog);
    }

    public static void d(String strLog) {
        Logger.d(strLog);
    }

    public static void e(String strLog) {
        Logger.e(strLog);
    }

    public static void v(String strLog) {
        Logger.v(strLog);
    }

    public static void w(String strLog) {
        Logger.w(strLog);
    }


}
