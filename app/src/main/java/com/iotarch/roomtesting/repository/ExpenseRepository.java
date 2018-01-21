package com.iotarch.roomtesting.repository;

import android.arch.paging.PagedList;
import android.support.annotation.Nullable;
import android.support.v7.util.DiffUtil;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.entity.Expense;

import javax.inject.Singleton;

/**
 * Created by JamesHo on 2018/1/19.
 */
@Singleton
public class ExpenseRepository {


    ExpenseDao expenseDao;


}
