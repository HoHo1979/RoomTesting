package com.iotarch.roomtesting.presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.database.ExpenseDatabase;
import com.iotarch.roomtesting.entity.Expense;

import java.lang.ref.WeakReference;
import java.util.Date;
import java.util.List;

/**
 * Created by JamesHo on 2018/1/17.
 */

public class ExpenseViewModel extends AndroidViewModel {

    private MutableLiveData<List<Expense>> expense;

    ExpenseDao expenseDao;

    public ExpenseViewModel(@NonNull Application application) {
        super(application);
        expenseDao = ExpenseDatabase.getInstance(application.getApplicationContext()).expenseDao();

    }

    public LiveData<List<Expense>> getExpenses(){

        if(expense==null){
            expense = new MutableLiveData<>();

            loadExpense();
        }
        return expense;
    }

    private void loadExpense() {


        MyAsyncTask myAsyncTask = new MyAsyncTask(this);
        myAsyncTask.execute();


    };


    static class MyAsyncTask extends AsyncTask<Void,Void,List<Expense>>{


        WeakReference<ExpenseViewModel> weakReference;


        public MyAsyncTask(ExpenseViewModel expenseViewModel){
            weakReference = new WeakReference<ExpenseViewModel>(expenseViewModel);
        }


        @Override
        protected List<Expense> doInBackground(Void... voids) {

            if(weakReference.get()==null)
                return null;

            return weakReference.get().expenseDao.findAllExpense();

        }

        @Override
        protected void onPostExecute(List<Expense> expenseList) {

            weakReference.get().expense.setValue(expenseList);

        }
    }


}
