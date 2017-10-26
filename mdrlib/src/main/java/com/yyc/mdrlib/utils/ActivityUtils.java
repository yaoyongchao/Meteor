package com.yyc.mdrlib.utils;

import android.app.Activity;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.FileProvider;

import com.yyc.mdrlib.R;

import java.io.File;


public class ActivityUtils {
    /**
     * 启动结果界面
     */
    public static void toActivityForResult(Activity context,
                                               Class<?> activity, int requestCode) {
        Intent intent = new Intent(context, activity);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivityForResult(intent, requestCode);
    }

    /**
     * 开启activity
     */
    public static void toActivity(Context context, Class<?> activity) {
        Intent intent = new Intent(context, activity);
        toActivity(context,activity,intent);
    }
    /**
     * 开启activity带参数
     */
    public static void toActivityAddParam(Context context, Class<?> activity,String... str) {
        Intent intent = new Intent(context, activity);
//        intent.putExtra("size",str.length);
        for (int i=0;i<str.length;i++){
            intent.putExtra(String.valueOf(i),str[i]);
        }
        toActivity(context,activity,intent);
    }

    public static void toActivity(Context context,Class<?> activity,Intent intent) {
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        context.startActivity(intent);
        Activity activity1 = (Activity)context;
        activity1.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
    }



    /**
     * 获得应用版本号码
     *
     * @param context
     * @return
     */
    public static int softVersionNum(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info.versionCode;
    }

    /**
     * 获得应用版本名称
     *
     * @param context
     * @return
     */
    public static String softVersionName(Context context) {
        PackageInfo info = null;
        try {
            synchronized (context) {
                info = context.getPackageManager().getPackageInfo(
                        context.getPackageName(), 0);
                return info.versionName;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 安装apk
     * @param content 上下文
     * @param dir apk文件
     */
    public static void installApk(Context content,File dir) {
        /*********下载完成，点击安装***********/
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            Uri contentUrl = FileProvider.getUriForFile(content, "com.lhhs.loan" + ".provider",dir);
            intent.setDataAndType(contentUrl, "application/vnd.android.package-archive");
        }else{
            Uri uri = Uri.fromFile(dir);
            /**********加这个属性是因为使用Context的startActivity方法的话，就需要开启一个新的task**********/
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.setDataAndType(uri,"application/vnd.android.package-archive");
        }

        content.startActivity(intent);
    }


    @SuppressWarnings("deprecation")
    public static void copy(String content, Context context) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 判定输入汉字
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
            return true;
        }
        return false;
    }

    /**
     * 检测String是否全是中文
     * @param name
     * @return
     */
    public static boolean checkNameChese(String name)
    {
        boolean res=true;
        char [] cTemp = name.toCharArray();
        for(int i=0;i<name.length();i++)
        {
            if(!isChinese(cTemp[i]))
            {
                res=false;
                break;
            }
        }
        return res;
    }

}
