package com.iotarch.roomtesting.repository;

import com.iotarch.roomtesting.MainActivity;
import com.iotarch.roomtesting.MainActivityModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

/**
 * Created by JamesHo on 2018/1/25.
 */

@Subcomponent(modules = {MainActivityModule.class})
public interface MainActivitySubComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    public abstract class Builder extends AndroidInjector.Builder<MainActivity>{};


}
