package com.example.dell.rxjavaretrofitdemo.util;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 项目:RxJavaRetrofitDemo
 * 作者:Tony
 * 日期:2017年03月15
 * 时间:17:23
 */

/**
 * Rxjava线程执行切换帮助类
 */
public class RxUtils {
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return new ObservableTransformer<T, T>() {
            @Override
            public ObservableSource<T> apply(Observable<T> upstream) {
                return upstream.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(AndroidSchedulers.mainThread());
            }
        };
    }
}
