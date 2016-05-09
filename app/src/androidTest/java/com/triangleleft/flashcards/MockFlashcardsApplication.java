package com.triangleleft.flashcards;


import com.triangleleft.flashcards.common.FlashcardsApplication;
import com.triangleleft.flashcards.common.di.ApplicationComponent;
import com.triangleleft.flashcards.common.di.ApplicationModule;
import com.triangleleft.flashcards.common.di.DaggerApplicationComponent;
import com.triangleleft.flashcards.mvp.common.di.module.NetModule;
import com.triangleleft.flashcards.mvp.common.di.module.ServiceModule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import android.support.annotation.NonNull;
import android.util.Log;

import okhttp3.HttpUrl;

public class MockFlashcardsApplication extends FlashcardsApplication {

    private static final Logger logger = LoggerFactory.getLogger(MockFlashcardsApplication.class);
    public static final HttpUrl MOCK_SERVER_URL = HttpUrl.parse("http://localhost:8080/");

    @NonNull
    @Override
    protected ApplicationComponent buildComponent() {
        Log.d("TEMP", "Ughh");
        logger.debug("buildComponent() called");
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .serviceModule(new ServiceModule())
                .netModule(new NetModule() {
                    @Override
                    public HttpUrl endpoint() {
                        return MOCK_SERVER_URL;
                    }
                })
                .build();
    }

}