package com.huatugz.flycommen.net.result;

import com.huatugz.flycommen.net.bean.BaseBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseBeanObserver<D, T extends BaseBean<D>> implements Observer<T> {
    private Disposable mDisposable;

    public enum ERROR_TYPE {
        DATA_EMPTY,//数据为空
        DATA_ERROR,//数据解析错误
        NET_ERROR,//网络连接错误
    }

    @Override
    public final void onSubscribe(Disposable d) {
        this.mDisposable = d;
    }

    @Override
    public final void onNext(T t) {
        if (t == null) {
            onError(ERROR_TYPE.DATA_ERROR, "error: service result data is NULL!");
            return;
        }
        if (Integer.parseInt(t.getHeader()) == 0 || Integer.parseInt(t.getStatus()) == 0) {
            if (t.getData() == null) {
                onError(ERROR_TYPE.DATA_EMPTY, "error: Data is empty!");
            } else {
                onSucess(t.getData());
            }
        } else {
            onError(ERROR_TYPE.DATA_ERROR, "error: Data is error!");
        }
    }

    @Override
    public final void onError(Throwable e) {
        onError(ERROR_TYPE.DATA_ERROR, "error: other error!");
        e.printStackTrace();
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    @Override
    public final void onComplete() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    //请求成功
    protected abstract void onSucess(D data);

    //发生错误！
    protected abstract void onError(ERROR_TYPE type, String err_msg);
}
