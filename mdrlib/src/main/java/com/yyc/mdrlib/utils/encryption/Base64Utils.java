package com.yyc.mdrlib.utils.encryption;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author: Page
 * @time: 17-8-17
 * @description: Base64 工具类
 *
 * 针对Base64.DEFAULT参数说明 :
 * 无论是编码还是解码都会有一个参数Flags，Android提供了以下几种 :
 * DEFAULT 这个参数是默认，使用默认的方法来加密
 * NO_PADDING 这个参数是略去加密字符串最后的”=”
 * NO_WRAP 这个参数意思是略去所有的换行符（设置后CRLF就没用了）
 * CRLF 这个参数看起来比较眼熟，它就是Win风格的换行符，意思就是使用CR LF这一对作为一行的结尾而不是Unix风格的LF
 * URL_SAFE 这个参数意思是加密时不使用对URL和文件名有特殊意义的字符来作为加密字符，具体就是以-和_取代+和/
 *
 */

public class Base64Utils {
    private static final String TAG = "Base64Utils";

    /**
     * @param: [str] str the String to be decrypted
     * @return: byte[] the data which is decrypted
     * @description: BASE64 解密
     */
    public static byte[] decryptBASE64(String str)  {
        byte[] result = null;
        try {
            if (null == str)
                return result;
            /*if ("".equals(null))
                result "".getBytes();*/
            result = Base64.decode(str,Base64.DEFAULT);

        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return result;
    }

    /**
     * @param: [str] str the String to be decrypted
     * @return: byte[] the data which is decrypted
     * @description: BASE64 加密
     */
    public static String encryptBASE64(byte[] str) {
        String result = null;
        try {
            if (null == str)
                return result;
            result = Base64.encodeToString(str, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e(TAG,e.toString());
        }
        return result;
    }

    /**
     * @param: [str] str the String to be decrypted
     * @return: java.lang.String the data which is decrypted
     * @description: BASE64 加密
     */
    public static String encryptBASE64Str(String str) {
        if (str == null)
            return null;
        return encryptBASE64(str.getBytes());
    }

    /**
     * @param: [str] str the String to be decrypted
     * @return: java.lang.String the data which is decrypted
     * @description: BASE64 解密
     */
    public static String decryptBASE64Str(String str) {
        if (str == null)
            return null;
        return new String(decryptBASE64(str));
    }

    /**
     * @param: [file] str the String to be decrypted
     * @return: java.lang.String the data which is decrypted
     * @description: BASE64 将文件转成base64 字符串
     */
    public static String getFileByteString(File file) throws Exception {
        FileInputStream inputFile = new FileInputStream(file);
        // 取得文件大小
        long length = file.length();
        // 根据大小创建字节数组
        byte[] buffer = new byte[(int) length];

        inputFile.read(buffer);
        inputFile.close();
        //base64转码 new String((new Base64()).encode(buffer))
        String encodedFileString = encryptBASE64(buffer);
        return encodedFileString;
    }
    /**
     * 将base64字符解码保存文件
     * @param base64Code  解码
     * @param targetPath 要存的地址
     * @return
     */

    public static String decoderBase64File(String base64Code, String targetPath) {
        File file = new File(targetPath);
        try {
            if (file.exists()) {
                Log.e(TAG,targetPath + " 文件已经存在，不能转换为文件");
                return null;
            } else {
                boolean createNewFile = file.createNewFile();
                if (createNewFile) {
                    Log.i(TAG,"文件创建成功！");
                } else {
                    Log.i(TAG,"文件创建遇到问题。");
                }
            }
            byte[] buffer = decryptBASE64(base64Code);
            FileOutputStream out = new FileOutputStream(targetPath);
            out.write(buffer);
            out.close();
            Log.i(TAG,"文件保存成功！");
        } catch (Exception e) {
            Log.e(TAG,"文件base64编码转换失败！");
            targetPath = "";
        }

        return targetPath;
    }

    /**
     * @param: [imgUrl] 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return: 编码后的字符串
     * @description: 将图片以Base64方式编码为字符串
     */
    public static String encodeImage(String imgUrl) throws IOException {
        FileInputStream fis = new FileInputStream(imgUrl);
        byte[] rs = new byte[fis.available()];
        fis.read(rs);
        fis.close();
        return encryptBASE64(rs);
    }

    /**
     * @param: [bitmap] 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return: 编码后的字符串
     * @description: 将Bitmap转换成字符串
     */
    public static String bitmaptoString(Bitmap bitmap) {
        String string = null;
        ByteArrayOutputStream bStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bStream);
        byte[] bytes = bStream.toByteArray();
        string = encryptBASE64(bytes);
        return string;
    }

    /**
     * @param: [bitmap] 图片的绝对路径（例如：D:\\jsontest\\abc.jpg）
     * @return: 编码后的字符串
     * @description: 把byte数组转化成 bitmap对象
     */
    public static Bitmap bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

}
