package com.iotarch.roomtesting.repository;

import android.arch.paging.DataSource;
import android.arch.paging.PagedList;
import android.graphics.pdf.PdfDocument;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.entity.Expense;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;

/**
 * Created by JamesHo on 2018/1/19.
*/
@Singleton
public class ExpenseRepository {

    ExpenseDao expenseDao;

    @Inject
    public ExpenseRepository(ExpenseDao expenseDao){
        this.expenseDao = expenseDao;
    }

    public List<Expense> getExpense(){

        return expenseDao.findAllExpense();
    }


}
