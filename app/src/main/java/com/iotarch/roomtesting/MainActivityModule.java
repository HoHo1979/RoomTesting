package com.iotarch.roomtesting;

import android.app.Activity;

import com.iotarch.roomtesting.MainActivity;
import com.iotarch.roomtesting.MainPresenter;
import com.iotarch.roomtesting.MainPresenterImpl;
import com.iotarch.roomtesting.MainView;
import com.iotarch.roomtesting.database.ApiService;
import com.iotarch.roomtesting.repository.MainActivitySubComponent;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.android.ActivityKey;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

/**
 * Created by JamesHo on 2018/1/25.
 */
@Module
public class MainActivityModule {

    @Provides
    MainView provideMainView(MainActivity mainActivity){
        return mainActivity;
    }

    @Provides
    MainPresenter provideMainPresenter(MainView mainView, ApiService apiService){
        return new MainPresenterImpl(mainView, apiService);
    }


}
