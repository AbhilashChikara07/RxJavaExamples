package com.example.chikara.retrowithrxjava;

import rx.Subscription;

/**
 * Created by chikara on 2/12/18.
 */

public interface RxJavaSubscriptionInterface {

    Subscription getServerResult(String param,
                                 RxJavaServerCommunicationClass.callBackListener callBackListener);

}
