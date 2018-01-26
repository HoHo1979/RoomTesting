package com.iotarch.roomtesting;

import com.iotarch.roomtesting.database.ApiService;

import javax.inject.Inject;

/**
 * Created by JamesHo on 2018/1/24.
 */

public class MainPresenterImpl implements MainPresenter {

    MainView mainView;
    ApiService apiService;

    @Inject
    public MainPresenterImpl(MainView mainView, ApiService apiService) {

        this.mainView=mainView;
        this.apiService=apiService;
    }

    @Override
    public void loadMain() {

        apiService.loadData();
        mainView.onMainLoaded();


    }
}
