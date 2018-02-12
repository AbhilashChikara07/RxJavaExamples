package com.example.chikara.retrowithrxjava.NetworkCall;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.chikara.retrowithrxjava.R;

import rx.Subscription;

public class MainActivity extends AppCompatActivity {

    TextView resultTV;
    ProgressBar progressBar;
    Subscription subscription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTV = (TextView) findViewById(R.id.resultTV);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        subscription = new RxJavaServerCommunicationClass(this).getServerResult("1",
                new RxJavaServerCommunicationClass.callBackListener() {
                    @Override
                    public void onSuccess(String result) {
                        resultTV.setText("RESULT IS :- " + result);
                        progressBar.setVisibility(View.GONE);
                        Log.e("result", "" + result);
                    }

                    @Override
                    public void onError(String errorMessage) {
                        Log.e("onError", "" + errorMessage);
                        resultTV.setText("ERROR IS :- " + errorMessage);
                        progressBar.setVisibility(View.GONE);
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscription != null)
            subscription.unsubscribe();
    }
}
