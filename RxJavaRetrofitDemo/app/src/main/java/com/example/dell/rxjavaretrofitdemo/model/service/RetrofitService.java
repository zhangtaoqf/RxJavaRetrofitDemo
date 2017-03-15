package com.example.dell.rxjavaretrofitdemo.model.service;

import com.example.dell.rxjavaretrofitdemo.model.entity.PicEntity;
import com.example.dell.rxjavaretrofitdemo.model.entity.TextEntity;
import com.example.dell.rxjavaretrofitdemo.model.entity.VideoEntity;
import com.example.dell.rxjavaretrofitdemo.util.ApiInterface;
import com.example.dell.rxjavaretrofitdemo.util.RxUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 项目:RxJavaRetrofitDemo
 * 作者:Tony
 * 日期:2017年03月15
 * 时间:16:30
 */

public class RetrofitService {

    private Retrofit retrofit;
    private ApiInterface apiService;

    private RetrofitService()
    {
        init();
    }
    public static class SingleInstanceHolder{
        private static RetrofitService retrofitService = new RetrofitService();
    }

    public static RetrofitService getInstance()
    {
        return SingleInstanceHolder.retrofitService;
    }

    private void init() {
        initOkHttp();
        initRetrofit();
        apiService = retrofit.create(ApiInterface.class);
    }

    private void initOkHttp() {

    }

    private void initRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(ApiInterface.HOST)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public Observable<List<TextEntity.ItemsBean>> getTextData(int page)
    {
        return apiService.getTexts(page)
                .flatMap(new Function<TextEntity, Observable<List<TextEntity.ItemsBean>>>() {
                    @Override
                    public Observable<List<TextEntity.ItemsBean>> apply(TextEntity textEntity) throws Exception {
                        return Observable.just(textEntity.getItems());
                    }
                })
                .compose(RxUtils.<List<TextEntity.ItemsBean>>rxSchedulerHelper());
    }

    public Observable<List<PicEntity.ItemsBean>> getPicData(int page)
    {
        return apiService.getPics(page)
                .flatMap(new Function<PicEntity, Observable<List<PicEntity.ItemsBean>>>() {
                    @Override
                    public Observable<List<PicEntity.ItemsBean>> apply(@NonNull PicEntity picEntity) throws Exception {
                        return Observable.just(picEntity.getItems());
                    }
                })
                .compose(RxUtils.<List<PicEntity.ItemsBean>>rxSchedulerHelper());
    }

    public Observable<List<VideoEntity.ItemsBean>> getVideoData(int page)
    {
        return apiService.getVideos(page)
                .flatMap(new Function<VideoEntity, Observable<List<VideoEntity.ItemsBean>>>() {
                    @Override
                    public Observable<List<VideoEntity.ItemsBean>> apply(@NonNull VideoEntity videoEntity) throws Exception {
                        return Observable.just(videoEntity.getItems());
                    }
                })
                .compose(RxUtils.<List<VideoEntity.ItemsBean>>rxSchedulerHelper());
    }

}
