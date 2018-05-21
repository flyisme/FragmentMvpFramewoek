package com.huatugz.flycommen.framework.imp.activity;

import android.os.Bundle;

import com.huatugz.flycommen.framework.BaseActivity;


/**
 * Created by flyisme on 2017/8/8.
 */

public abstract class MvpActivity<P extends BaseActivityPresenter> extends BaseActivity implements BaseActivityView<P> {

    protected VIEW_STATE mState = null;
    private final String PRESENTER_SAVE_KEY="presenter_save";
    protected Bundle mPresenterBundle=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null&&savedInstanceState.getBundle(PRESENTER_SAVE_KEY)!=null)
        {
            mPresenterBundle=savedInstanceState.getBundle(PRESENTER_SAVE_KEY);
        }
    }

    @Override
    public void onStart() {
        
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRestart() {
        super.onRestart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(getPresenter()!=null)
        {
            getPresenter().dettachView();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(getPresenter()!=null)
        {
            Bundle bundle = getPresenter().statusSave();
            if (bundle!=null)
            {
                outState.putBundle(PRESENTER_SAVE_KEY,bundle);
            }
        }
    }

    @Override
    public void showRightPage(VIEW_STATE state) {
        mState = state;
    }
    @Override
    public abstract P getPresenter() ;

    public VIEW_STATE getViewState() {
        return mState;
    }

    public void setViewState(VIEW_STATE mState) {
        this.mState = mState;
    }
}
