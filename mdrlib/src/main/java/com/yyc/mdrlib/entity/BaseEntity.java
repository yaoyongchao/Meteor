package com.yyc.mdrlib.entity;

import com.google.gson.annotations.SerializedName;

/**
 * @author: Page
 * @time: 17-7-21
 * @description:
 */

public class BaseEntity<T> {

    @SerializedName("meta")
    private Meta meta;
    @SerializedName("data")
    private T data;

    public boolean isSuccess() {
        return meta.isSuccess();
    }

    public String getMsg() {
        return meta.getMsg();
    }

    public Meta getCode() {
        return meta;
    }

    public void setCode(Meta meta) {
        this.meta = meta;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}


