package com.example.chikara.retrowithrxjava.NetworkCall;


import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by chikara on 2/12/18.
 */

public interface ApiInterface {

    @GET(KeyIds.GET_SPECIES)
    rx.Observable<String>  getResult(@Query("page") String page);

}
