package com.example.chikara.retrowithrxjava.NetworkCall;

import android.content.Context;

import com.example.chikara.retrowithrxjava.R;

import java.util.concurrent.TimeUnit;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by chikara on 2/12/18.
 */

public class RetrofitClient {


    private static final int MAX_REQUESTS = 4;
    private static final int CONNECTION_TIMEOUT = 60;// time in seconds
    private static final int READ_TIMEOUT = 60;// time in seconds
    private static final int WRITE_TIMEOUT = 60;// time in seconds
    private static RetrofitClient mRetrofitClient;
    private static Retrofit mRetrofitObj;
    private static String BASE_URL = "";

    static ApiInterface getAPI(Context context) {
        if (mRetrofitClient == null) {
            mRetrofitClient = new RetrofitClient();

            BASE_URL = context.getString(R.string.server_uri);
            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(MAX_REQUESTS);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .dispatcher(dispatcher)
                    .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
                    .build();

            mRetrofitObj = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
        }
        return mRetrofitObj.create(ApiInterface.class);
    }
}

