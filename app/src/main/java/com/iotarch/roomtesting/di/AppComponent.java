package com.iotarch.roomtesting.di;

import android.app.Application;

import com.iotarch.roomtesting.MainActivityModule;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by JamesHo on 2018/1/22.
 */
@Component(modules = {AndroidSupportInjectionModule.class,AppModule.class,
        ActivityBuilder.class,MainActivityModule.class})
public interface AppComponent extends AndroidInjector<AndroidSampleApp>{


    @Component.Builder
    abstract class Builder extends AndroidInjector.Builder<AndroidSampleApp> {}


}
