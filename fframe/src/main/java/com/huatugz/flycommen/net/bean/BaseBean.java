package com.huatugz.flycommen.net.bean;

import android.text.TextUtils;

/**
 * Created by heyf on 2018/3/26 0026.
 */

public class BaseBean<D> {
    private D data;
    private String status;
    private String header;

    public String getHeader() {
        if (TextUtils.isEmpty(header)) {
            header = "-1024";
        }
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public String getStatus() {
        if (TextUtils.isEmpty(status)) {
            status = "-1024";
        }
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
