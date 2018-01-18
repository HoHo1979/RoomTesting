package com.iotarch.roomtesting;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.iotarch.roomtesting.dao.ExpenseDao;
import com.iotarch.roomtesting.database.ExpenseDatabase;
import com.iotarch.roomtesting.entity.Expense;
import com.iotarch.roomtesting.presenter.ExpenseViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseDisplayActivity extends AppCompatActivity {

    private ExpenseRecyclerAdapter adapter;
    private RecyclerView expenseView;

    private static final String TAG = ExpenseDisplayActivity.class.getSimpleName();

    ExpenseDao expenseDao;

    List<Expense> expenseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        expenseList = new ArrayList<>();

        expenseDao = ExpenseDatabase.getInstance(this).expenseDao();


        expenseView = findViewById(R.id.expenseRecycler);

        adapter = new ExpenseRecyclerAdapter(expenseList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());

        expenseView.setLayoutManager(layoutManager);

        ExpenseViewModel expenseViewModel = ViewModelProviders.of(this).get(ExpenseViewModel.class);
        expenseViewModel.getExpenses().observe(this,expenses->{


            expenseList.addAll(expenses);

            adapter.notifyDataSetChanged();

        });

//        expenseView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
//
//        expenseView.setItemAnimator(new DefaultItemAnimator());

        expenseView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    @Override
    protected void onDestroy() {
        //This causes the error if user press back button and come to this view again the database connection will be closed.
//        ExpenseDatabase.destroyInstance();
        super.onDestroy();
    }
}
