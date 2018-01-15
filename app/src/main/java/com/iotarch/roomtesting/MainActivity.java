package com.iotarch.roomtesting;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


//This is the App that test the Room API that help SQLLight to access the database.
//In order to use Room API , there are at least three class that needed to be created
//1. Entity Class that represent Database Column with match fields , Expense.cass
//2. DAO class that has access method with SQL query , insert, update and Deleted Annotations.
//3. A Abstract Database Object that extends RoomDatabase with abstract method that returns ExpenseDao.

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
