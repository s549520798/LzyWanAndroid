package com.lazylee.lzywanandroid.ui.activity.enter;

import android.content.Intent;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

import com.lazylee.lzywanandroid.R;
import com.lazylee.lzywanandroid.tools.log.Logger;
import com.lazylee.lzywanandroid.ui.activity.main.MainActivity;

import java.util.concurrent.TimeUnit;

public class EnterActivity extends AppCompatActivity {

    private static final String TAG = "EnterActivity";
    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.enter_activity);

        mCompositeDisposable = new CompositeDisposable();
        Observable.interval(1L, TimeUnit.SECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Long>() {
                    private Disposable disposable;

                    @Override
                    public void onSubscribe(Disposable d) {
                        disposable = d;
                        mCompositeDisposable.add(disposable);
                    }

                    @Override
                    public void onNext(Long aLong) {
                        Logger.d(Logger.LOG_MODEL_UI, Logger.LOG_DETAIL_MAIN, TAG, "计时 count " + aLong);
                        if (aLong.equals(2L)) {
                            if (!disposable.isDisposed()) disposable.dispose();
                            skipToMainActivity();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.e(Logger.LOG_MODEL_UI, Logger.LOG_DETAIL_MAIN, TAG, e);
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }

                        skipToMainActivity();
                    }

                    @Override
                    public void onComplete() {
                        //interval 会无线递增，需要先调用dispose.dispose
                        if (!disposable.isDisposed()) {
                            disposable.dispose();
                        }
                        skipToMainActivity();
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onDestroy() {
        mCompositeDisposable.clear();
        super.onDestroy();
    }

    private void skipToMainActivity() {
        Logger.d(Logger.LOG_MODEL_UI, Logger.LOG_DETAIL_MAIN, TAG, "EnterActivity 跳转到 MainActivity");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
