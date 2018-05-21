package com.huatugz.flycommen.framework.imp.activity;

import android.os.Bundle;

import com.huatugz.flycommen.framework.BaseView;

/**
 * Created by flyisme on 2017/8/8.
 */

public interface BaseActivityView<P extends BaseActivityPresenter> extends BaseView {
    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onPause();

    void onResume();

    void onRestart();

    void onStop();

    void onDestroy();

    void onContentChanged();

    void onSaveInstanceState(Bundle outState);

    void onAttachedToWindow();

    /**
     *
     * 加载成功的布局.
     */
    void showRightPage(VIEW_STATE state);
    P getPresenter();
}
