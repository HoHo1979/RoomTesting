package com.iotarch.roomtesting;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.database.ExpenseDatabase;
import com.iotarch.roomtesting.entity.Expense;

import java.util.Date;
import java.util.List;


//This is the App that test the Room API that help SQLLight to access the database.
//In order to use Room API , there are at least three class that needed to be created
//1. Entity Class that represent Database Column with match fields , Expense.cass
//2. DAO class that has access method with SQL query , insert, update and Deleted Annotations.
//3. A Abstract Database Object that extends RoomDatabase with abstract method that returns ExpenseDao.

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    private TextView itemName;
    private TextView info;
    private TextView price;
    private EditText timeStamp;

    ExpenseDao expenseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseDao=ExpenseDatabase.getInstance(getApplicationContext()).expenseDao();

        itemName=findViewById(R.id.itemName);
        info=findViewById(R.id.info);
        price=findViewById(R.id.price);
        timeStamp=findViewById(R.id.date);

    }


    public void addExpense(View view){

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                String name=itemName.getText().toString();
                String i= info.getText().toString();
                double p = Double.parseDouble(price.getText().toString());
                long t = new Date().getTime();

                Expense expense = new Expense(t,name,i,23.5);
                List<Long> x=expenseDao.insertExpense(expense);

                return null;

            }
        }.execute();


        Intent intent = new Intent(this,ExpenseDisplayActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        ExpenseDatabase.destroyInstance();
        super.onDestroy();
    }
}
