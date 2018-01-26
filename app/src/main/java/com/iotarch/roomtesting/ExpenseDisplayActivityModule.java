package com.iotarch.roomtesting;

import com.iotarch.roomtesting.database.ApiService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by JamesHo on 2018/1/24.
 */

@Module
class ExpenseDisplayActivityModule {

    @Provides
    ExpenseDisplayView provideExpenseView(ExpenseDisplayActivity activity){
        return activity;
    }

    @Provides
    ExpenseDisplayViewPresenter provideExpenseViewPresenter(ExpenseDisplayView expenseDisplayView, ApiService apiService){
        return new ExpenseDisplayViewPresenterImpl(expenseDisplayView,apiService);
    }


}
