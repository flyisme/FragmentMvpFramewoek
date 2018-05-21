package com.huatugz.flycommen.net;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.lang.reflect.Field;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by flyisme on 2017/8/17.
 */
@Singleton
public class RetrofitServiceFactory {
    private Gson mGsonDateFormat;
    private OkHttpClient httpClient;

    @Inject
    public RetrofitServiceFactory(Gson gson, OkHttpClient httpClient) {
        mGsonDateFormat = gson;
        this.httpClient = httpClient;
    }

    public <S> S createService(Class<S> serviceClass) {
        String baseUrl = "";
        try {
            Field field1 = serviceClass.getField("BASE_URL");
            baseUrl = (String) field1.get(serviceClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Retrofit retrofit = null;
        if (TextUtils.isEmpty(baseUrl)) {
            retrofit = new Retrofit.Builder()
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();

        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(mGsonDateFormat))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit.create(serviceClass);
    }
}
