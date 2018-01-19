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

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;


//This is the App that test the Room API that help SQLLight to access the database.
//In order to use Room API , there are at least three class that needed to be created
//1. Entity Class that represent Database Column with match fields , Expense.cass
//2. DAO class that has access method with SQL query , insert, update and Deleted Annotations.
//3. A Abstract Database Object that extends RoomDatabase with abstract method that returns ExpenseDao.

//RecyclerView with Adapter to generate UI

//Use ViewModel rather than cursorloader to updat the UI
//4. A AndroidViewModel observer the dataase to update the UI


//5.Create static MyAsyncTask to avoid memory leak


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    private TextView itemName;
    private TextView info;
    private TextView price;
    private EditText timeStamp;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        itemName=findViewById(R.id.itemName);
        info=findViewById(R.id.info);
        price=findViewById(R.id.price);
        timeStamp=findViewById(R.id.date);

    }


    public void addExpense(View view){


        Expense expense = new Expense(new Date().getTime(),
                itemName.getText().toString(),info.getText().toString(),Double.parseDouble(price.getText().toString()));

        MyAsyncTask asyncTask = new MyAsyncTask(this);

        asyncTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,expense);

        Intent intent = new Intent(this,ExpenseDisplayActivity.class);
        startActivity(intent);


    }

    @Override
    protected void onDestroy() {
        ExpenseDatabase.destroyInstance();
        super.onDestroy();
    }

    //Crate a Static MyAsyncTask to avoid memory leak.
    static class MyAsyncTask extends AsyncTask<Expense,Void,List<Long>>{

        ExpenseDao expenseDao;

        WeakReference<MainActivity> reference;

        public MyAsyncTask(MainActivity context){
            reference = new WeakReference<MainActivity>(context);
        }

        @Override
        protected List<Long> doInBackground(Expense... expenses) {


            if(reference.get()==null)
                return null;

            expenseDao=ExpenseDatabase.getInstance(reference.get()).expenseDao();

            return expenseDao.insertExpense(expenses);

        }
    }
}
