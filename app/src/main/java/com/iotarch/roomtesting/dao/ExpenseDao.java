package com.iotarch.roomtesting.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.widget.ExpandableListView;

import com.iotarch.roomtesting.entity.Expense;

import java.util.List;

/**
 * Created by JamesHo on 2018/1/14.
 */


@Dao
public interface ExpenseDao {

    @Insert
    List<Long> insertExpense(Expense... expense);

    @Query(value = "SELECT * FROM "+Expense.TABLE_EXPENSE)
    List<Expense> findAllExpense();

    @Query(value = "SELECT * FROM "+Expense.TABLE_EXPENSE+" WHERE uid = :uid")
    List<Expense> findExpenseById(int uid);

    @Query(value = "SELECT * FROM "+Expense.TABLE_EXPENSE+" WHERE timestamp BETWEEN :startTime AND :finishTime")
    List<Expense> findExpenseBetweenTime(long startTime,long finishTime);

}
