package com.iotarch.roomtesting.dao;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.iotarch.roomtesting.database.ExpenseDatabase;
import com.iotarch.roomtesting.entity.Expense;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by JamesHo on 2018/1/15.
 */
@RunWith(AndroidJUnit4.class)
public class ExpenseDaoTest {

    private ExpenseDao expenseDao;
    private ExpenseDatabase expenseDatabase;


    @Before
    public  void createDB(){
        Context context = InstrumentationRegistry.getTargetContext();
        expenseDatabase = Room.inMemoryDatabaseBuilder(context, ExpenseDatabase.class).build();
        expenseDao = expenseDatabase.expenseDao();
    }

    @After
    public void closeDb() throws IOException {
        expenseDatabase.close();
    }


    @Test
    public void insertExpense() throws  Exception{
        Expense expense = new Expense(new Date().getTime(),"Food","Eat Lunch",23.3);
        expenseDao.insertExpense(expense);
        List<Expense> expenseList = expenseDao.findAllExpense();
        assert expenseList.get(0).getPrice()==30;
    }

    @Test
    public void findAllExpense() throws Exception {

    }

    @Test
    public void findExpenseById() throws Exception {

    }

    @Test
    public void findExpenseBetweenTime() throws Exception {

    }

}