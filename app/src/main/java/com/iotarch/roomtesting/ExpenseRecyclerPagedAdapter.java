package com.iotarch.roomtesting;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.recyclerview.extensions.DiffCallback;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iotarch.roomtesting.entity.Expense;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by JamesHo on 2018/1/21.
 */

public class ExpenseRecyclerPagedAdapter extends PagedListAdapter<Expense, ExpenseRecyclerPagedAdapter.ExpenseViewHolder> {

    protected ExpenseRecyclerPagedAdapter() {
        super(DIFF_CALLBACK);
    }

    @Override
    public ExpenseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.expense_item_layout,parent,false);
       return new ExpenseViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ExpenseViewHolder holder, int position) {

        Expense expense=getItem(position);
        if(expense!=null){
            holder.bindTo(expense);
        }else{
            holder.clear();
        }
    }

    public static class ExpenseViewHolder extends RecyclerView.ViewHolder{

        private final TextView tx_id;
        private final TextView tx_name;
        private final TextView tx_info;
        private final TextView tx_price;
        private final TextView tx_date;


        public ExpenseViewHolder(View itemView) {
            super(itemView);

            tx_id=itemView.findViewById(R.id.ex_id);
            tx_name=itemView.findViewById(R.id.ex_name);
            tx_info=itemView.findViewById(R.id.ex_info);
            tx_price=itemView.findViewById(R.id.ex_price);
            tx_date=itemView.findViewById(R.id.ex_date);
        }


        public void bindTo(Expense expense) {

            tx_id.setText(String.valueOf(expense.getUid()));
            tx_name.setText(expense.getItemName());
            tx_date.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date(expense.getTimestamp())));
            tx_price.setText(String.valueOf(expense.getPrice()));
            tx_info.setText(expense.getInfo());

        }


        public void clear() {

            tx_id.setText("");
            tx_name.setText("");
            tx_date.setText("");
            tx_price.setText("");
            tx_info.setText("");

        }
    }

    
    

    public static final DiffCallback<Expense> DIFF_CALLBACK = new DiffCallback<Expense>() {
        @Override
        public boolean areItemsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
           return  oldItem.getUid() == newItem.getUid();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Expense oldItem, @NonNull Expense newItem) {
            return oldItem.equals(newItem);
        }
    };


}
