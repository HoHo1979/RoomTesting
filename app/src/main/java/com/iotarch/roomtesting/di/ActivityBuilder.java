package com.iotarch.roomtesting.di;

import android.app.Activity;

import com.iotarch.roomtesting.MainActivity;
import com.iotarch.roomtesting.MainActivityModule;
import com.iotarch.roomtesting.repository.MainActivitySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by JamesHo on 2018/1/22.
 */
@Module
public abstract class ActivityBuilder {

    @Binds
    @IntoMap
    @ActivityKey(MainActivity.class)
    abstract AndroidInjector.Factory<? extends Activity> bindMainActivity(MainActivitySubComponent.Builder builder);

}
