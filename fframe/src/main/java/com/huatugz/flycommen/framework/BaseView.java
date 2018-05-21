package com.huatugz.flycommen.framework;

/**
 * Created by flyisme on 2017/8/8.
 */

public interface BaseView {
    enum VIEW_STATE {
        //未加载
        UNLOAD(0)
        //正在加载
        , LOADING(1)
        //数据为空
        , LOAD_EMPTY(2)
        //加载失败
        , LOAD_ERROR(3)
        //加载成功
        , LOAD_SUCCESS(4);
        private int value;
        VIEW_STATE(int var){
            value=var;
        }
        public int getValue(){
            return value;
        }
    }
}
