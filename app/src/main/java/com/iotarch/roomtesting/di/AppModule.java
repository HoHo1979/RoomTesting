package com.iotarch.roomtesting.di;

import android.app.Application;
import android.content.Context;

import com.iotarch.roomtesting.repository.MainActivitySubComponent;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JamesHo on 2018/1/22.
 */

@Module(subcomponents = {MainActivitySubComponent.class})
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application){
        return application;
    }

}
