package com.iotarch.roomtesting.di;

import android.app.Activity;
import android.app.Application;

import com.iotarch.roomtesting.di.AppComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;



/**
 * Created by JamesHo on 2018/1/22.
 */

public class AndroidSampleApp extends DaggerApplication{


    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
        return DaggerAppComponent.builder().create(this);
    }
}
