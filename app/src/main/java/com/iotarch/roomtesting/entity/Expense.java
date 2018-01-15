package com.iotarch.roomtesting.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by JamesHo on 2018/1/14.
 * Defined the Java Bean Object of Expense with relate field to be store in Database
 */
@Entity(tableName = Expense.TABLE_EXPENSE)
public class Expense {

    public static final String TABLE_EXPENSE = "expense" ;


    @PrimaryKey(autoGenerate = true)
    private int uid;

    @ColumnInfo(name="timestamp")
    private long timestamp;

    @ColumnInfo(name="item_name")
    private String itemName;

    @ColumnInfo(name="info")
    private String info;

    @ColumnInfo(name="price")
    private double price;

    public Expense(long timestamp, String itemName, String info, double price) {
        this.timestamp = timestamp;
        this.itemName = itemName;
        this.info = info;
        this.price = price;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
