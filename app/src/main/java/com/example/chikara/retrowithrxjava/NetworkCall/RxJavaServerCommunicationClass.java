package com.example.chikara.retrowithrxjava.NetworkCall;

import android.content.Context;
import android.util.Log;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by chikara on 2/12/18.
 */

public class RxJavaServerCommunicationClass implements RxJavaSubscriptionInterface {

    private Context context;

    RxJavaServerCommunicationClass(Context context) {
        this.context = context;
    }

    @Override
    public synchronized Subscription getServerResult(String param, final callBackListener listener) {
        return RetrofitClient.getAPI(context).getResult(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends String>>() {
                    @Override
                    public Observable<? extends String> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        Log.e("onCompleted", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onError(e.getMessage());
                    }

                    @Override
                    public void onNext(String result) {
                        listener.onSuccess(result);
                    }
                });

    }

    interface callBackListener {
        void onSuccess(String result);

        void onError(String errorMessage);
    }
}
