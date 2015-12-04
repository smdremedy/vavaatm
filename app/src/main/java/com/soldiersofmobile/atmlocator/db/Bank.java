package com.soldiersofmobile.atmlocator.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "bank")
public class Bank {

    public static class Columns {
        public static final String NAME = "name";
        public static final String PHONE = "phone";
    }

    @DatabaseField(columnName = Columns.NAME, id = true)
    private String name;
    @DatabaseField(columnName = Columns.PHONE, canBeNull = false)
    private String phone;

    public Bank() {
    }

    public Bank(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
