package com.iotarch.roomtesting.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.entity.Expense;

/**
 * Created by JamesHo on 2018/1/14.
 */

@Database(entities = {Expense.class},version = 1,exportSchema = true)
public abstract class ExpenseDatabase extends RoomDatabase {

    private static ExpenseDatabase instance;

    public abstract ExpenseDao expenseDao();

    public static ExpenseDatabase getInstance(Context context){

        if(instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext(),ExpenseDatabase.class,"ExpenseDB").build();


        }

        return instance;
    };

    public static void destroyInstance() {

        if(instance!=null)
            instance.close();

        instance = null;
    }


}
