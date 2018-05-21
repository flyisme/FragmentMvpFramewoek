package com.huatugz.flycommen.framework.imp.fragment;

import com.huatugz.flycommen.framework.BasePresenter;

import java.lang.ref.WeakReference;

/**
 * Created by flyisme on 2017/8/9.
 */

public class BaseFragmentPresenter<V extends BaseFragmentView> implements BasePresenter<V> {
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
