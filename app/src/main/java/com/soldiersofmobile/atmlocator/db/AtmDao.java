package com.soldiersofmobile.atmlocator.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;

import java.sql.SQLException;
import java.util.List;

public class AtmDao extends BaseDaoImpl<Atm, Long> {

    protected AtmDao() throws SQLException {
        super(Atm.class);
    }

    public List<Atm> getCloseTo(double lat, double lng) throws SQLException {

        QueryBuilder<Atm, Long> builder = queryBuilder();
        builder.where().le(Atm.Columns.LATITUDE, lat + 1)
                .and().ge(Atm.Columns.LATITUDE, lat - 1)
                .and().le(Atm.Columns.LONGITUDE, lng + 1)
                .and().ge(Atm.Columns.LONGITUDE, lng - 1);

        PreparedQuery<Atm> prepare = builder.prepare();

        return query(prepare);
    }
}
