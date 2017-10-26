package com.yyc.mdrlib.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Page
 * @time: 17-7-21
 * @description:
 */

public class Meta {
    @SerializedName("success")
    private boolean success;
    @SerializedName("msg")
    private String msg;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
