package com.iotarch.roomtesting;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import com.iotarch.roomtesting.entity.Expense;
import com.iotarch.roomtesting.myinterface.MyItemClickedListener;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by JamesHo on 2018/1/16.
 */

public class ExpenseRecyclerAdapter extends RecyclerView.Adapter<ExpenseRecyclerAdapter.MyViewHolder> {

    List<Expense> expenseList;
    MyViewHolder viewHolder;
    MyItemClickedListener listener;

    public ExpenseRecyclerAdapter(List<Expense> expenseList, MyItemClickedListener listener){
        this.expenseList=expenseList;
        this.listener=listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.expense_item_layout,parent,false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.bind(expenseList.get(position),listener);
        Expense expense = expenseList.get(position);
        holder.tx_id.setText(String.valueOf(expense.getUid()));
        holder.tx_name.setText(expense.getItemName());
        holder.tx_info.setText(expense.getInfo());
        holder.tx_price.setText(String.valueOf(expense.getPrice()));
        holder.tx_date.setText(new SimpleDateFormat("yyyy/MM/dd").format(new Date(expense.getTimestamp())));

    }

    @Override
    public int getItemCount() {
        return expenseList.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tx_id;
        private final TextView tx_name;
        private final TextView tx_info;
        private final TextView tx_price;
        private final TextView tx_date;


        public MyViewHolder(View itemView) {

            super(itemView);

            tx_id=itemView.findViewById(R.id.ex_id);
            tx_name=itemView.findViewById(R.id.ex_name);
            tx_info=itemView.findViewById(R.id.ex_info);
            tx_price=itemView.findViewById(R.id.ex_price);
            tx_date=itemView.findViewById(R.id.ex_date);

        }

        public void bind(Expense expense,MyItemClickedListener listener) {

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.itemClicked(expense);
                }
            });

        }
    }


}
