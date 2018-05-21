package com.huatugz.flycommen.framework.imp.fragment;

import android.os.Bundle;
import android.view.View;

import com.huatugz.flycommen.framework.BaseView;

/**
 * Created by flyisme on 2017/8/9.
 */

public interface BaseFragmentView<P extends BaseFragmentPresenter> extends BaseView{
    void onCreate(Bundle savedInstanceState);

    void onDestroyView();

    void showRightPage(BaseView.VIEW_STATE state);

    P getPresenter();
}
