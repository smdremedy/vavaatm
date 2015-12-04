package com.soldiersofmobile.atmlocator.db;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName = "atm", daoClass = AtmDao.class)
public class Atm implements Serializable {

    @DatabaseField(columnName = Columns.ID, generatedId = true)
    private long id;
    @DatabaseField(columnName = Columns.LATITUDE, canBeNull = false)
    private double latitude;
    @DatabaseField(columnName = Columns.LONGITUDE, canBeNull = false)
    private double longitude;
    @DatabaseField(columnName = Columns.ADDRESS, canBeNull = false)
    private String address;
    @DatabaseField(columnName = Columns.BANK, foreign = true, foreignAutoRefresh = true)
    private Bank bank;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public class Columns {
        public static final String ID = "id";
        public static final String LATITUDE = "latitude";
        public static final String LONGITUDE = "longitude";
        public static final String ADDRESS = "address";
        public static final String BANK = "bank";
    }
}
