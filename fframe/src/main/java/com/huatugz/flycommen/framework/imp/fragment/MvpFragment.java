package com.huatugz.flycommen.framework.imp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.huatugz.flycommen.framework.fragmentation.swipeback.SwipeBackFragment;

import java.util.ArrayList;
import java.util.List;


/**
 * 懒加载(一个View可对应多个Presenter)
 * 子类只需要把需要使用的Presenter加到mPresenters中，父类完成presenter生命周期的管理
 * Created by flyisme on 2017/8/9.
 */

public abstract class MvpFragment<P1 extends BaseFragmentPresenter> extends SwipeBackFragment implements BaseFragmentView<P1> {


    protected VIEW_STATE mState = null;
    private List<BaseFragmentPresenter> mPresenters = new ArrayList<>();

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        initPresenters();
        for (BaseFragmentPresenter p : mPresenters) {
            if (p != null) {
                p.attachView(this);
            }
        }
        super.onLazyInitView(savedInstanceState);
        hideSoftInput();
    }


    @Override
    public void onDestroyView() {
        for (BaseFragmentPresenter p : mPresenters) {
            if (p != null) {
                p.dettachView();
            }
        }
        mPresenters.clear();
        super.onDestroyView();
    }

    /**
     * 第一次调用会在onCreateView后
     * 获取默认Presenter
     *
     * @return 默认Presenter的实例对象(子类需要确保多次调用返回一个实例)
     */
    @Override
    public abstract P1 getPresenter();


    protected void initPresenters() {
        addPresent(getPresenter());
    }

    @Override
    public void showRightPage(VIEW_STATE state) {
        mState = state;
    }

    /**
     * 增加其它Presenter;
     *
     * @param p
     */
    protected void addPresent(BaseFragmentPresenter p) {
        if (p != null) {
            mPresenters.add(p);
        }
    }
}
