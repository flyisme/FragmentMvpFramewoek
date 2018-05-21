package com.huatugz.flycommen.framework.imp.activity;

import android.os.Bundle;

import com.huatugz.flycommen.framework.BasePresenter;

import java.lang.ref.WeakReference;

/**
 * Created by flyisme on 2017/8/8.
 */

public class BaseActivityPresenter<V extends BaseActivityView> implements BasePresenter<V> {
    //数据恢复
    public void statusResume(Bundle bundle,V.VIEW_STATE state){

    }
    //状态保存
    public Bundle statusSave(){
        return null;
    }
    private WeakReference<V> mView;

    @Override
    public void attachView(V view) {
        mView = new WeakReference<V>(view);
    }

    @Override
    public void dettachView() {
        if (mView != null){
            mView.clear();
            mView = null;
        }
    }
    protected V getView()
    {
        if (mView!=null&&mView.get()!=null)
        {
            return mView.get();
        }
        return null;
    }
}
