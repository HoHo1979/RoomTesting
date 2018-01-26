package com.iotarch.roomtesting;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.database.ExpenseDatabase;
import com.iotarch.roomtesting.di.AppComponent;
import com.iotarch.roomtesting.entity.Expense;

import java.lang.ref.WeakReference;
import java.net.ConnectException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Inject;

import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerAppCompatActivity;


//This is the App that test the Room API that help SQLLight to access the database.
//In order to use Room API , there are at least three class that needed to be created
//1. Entity Class that represent Database Column with match fields , Expense.cass
//2. DAO class that has access method with SQL query , insert, update and Deleted Annotations.
//3. A Abstract Database Object that extends RoomDatabase with abstract method that returns ExpenseDao.

//RecyclerView with Adapter to generate UI

//Use ViewModel rather than cursorloader to updat the UI
//4. A AndroidViewModel observer the dataase to update the UI


//5.Create static MyAsyncTask to avoid memory leak


public class MainActivity extends DaggerAppCompatActivity implements MainView {

    private static final String TAG = MainActivity.class.getSimpleName() ;
    private TextView itemName;
    private TextView info;
    private TextView price;
    private EditText timeStamp;
    private Button dateButton;
    private SimpleDateFormat dateFormat;


    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter.loadMain();


        itemName=findViewById(R.id.itemName);
        info=findViewById(R.id.info);
        price=findViewById(R.id.price);
        timeStamp=findViewById(R.id.date);
        dateButton = findViewById(R.id.dateButton);


        Date date = new Date();
        dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        timeStamp.setText(dateFormat.format(date));


        DatePickerDialog datePickerDialog = new DatePickerDialog(this);

        datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                timeStamp.setText(year+"/"+(month+1)+"/"+dayOfMonth);
            }
        });

       dateButton.setOnClickListener(e->datePickerDialog.show());


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    public void addExpense(View view){


        String dateString = timeStamp.getText().toString();

        long time=dateFormat.parse(dateString,new ParsePosition(0)).getTime();

        Expense expense = new Expense(time,
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

    @Override
    public void onMainLoaded() {

        Toast.makeText(this,"The Presenter is working",Toast.LENGTH_LONG).show();

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
