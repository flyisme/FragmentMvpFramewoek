package com.huatugz.flycommen.framework;

/**
 * Created by flyisme on 2017/8/8.
 */

public interface BasePresenter<V extends BaseView> {
    String VIEW_STATUS="view_status";
    /**
     * 绑定视图
     *
     * @param view
     */
    void attachView(V view);

    /**
     * 解除绑定
     */
    void dettachView();
}
