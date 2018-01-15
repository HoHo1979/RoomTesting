package com.iotarch.roomtesting.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.entity.Expense;

/**
 * Created by JamesHo on 2018/1/14.
 */

@Database(entities = {Expense.class},version = 1)
public abstract class ExpenseDatabase extends RoomDatabase {

    public abstract ExpenseDao expenseDao();

}
