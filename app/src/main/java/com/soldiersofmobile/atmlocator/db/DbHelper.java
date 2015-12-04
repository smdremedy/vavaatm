package com.soldiersofmobile.atmlocator.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class DbHelper extends OrmLiteSqliteOpenHelper {

    public static final String ATMLOCATOR_DB = "atmlocator.db";
    public static final int DATABASE_VERSION = 1;

    public DbHelper(Context context) {
        super(context, ATMLOCATOR_DB, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try {
            TableUtils.createTable(connectionSource, Atm.class);
            TableUtils.createTable(connectionSource, Bank.class);

            Dao<Bank, String> bankDao = getDao(Bank.class);

            bankDao.create(new Bank("PKO BP", "500 600 700"));
            bankDao.create(new Bank("Millenium", "500 600 701"));
            bankDao.create(new Bank("ING Bank", "500 600 702"));
            bankDao.create(new Bank("IDEA Bank", "500 600 703"));

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Atm.class, true);
            TableUtils.dropTable(connectionSource, Bank.class, true);

            onCreate(database, connectionSource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
