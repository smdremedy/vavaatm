package com.soldiersofmobile.atmlocator;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.j256.ormlite.dao.Dao;
import com.soldiersofmobile.atmlocator.db.Atm;
import com.soldiersofmobile.atmlocator.db.Bank;
import com.soldiersofmobile.atmlocator.db.DbHelper;

import java.sql.SQLException;
import java.util.List;

public class ATMMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final int REQUEST_CODE = 123;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atmmap);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_atmlocator, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, AddAtmActivity.class);
            startActivityForResult(intent, REQUEST_CODE);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            refreshAtms();
        }
    }

    private void refreshAtms() {
        DbHelper dbHelper = new DbHelper(this);
        try {
            Dao<Atm, Long> atmDao = dbHelper.getDao(Atm.class);
            Dao<Bank, String> bankDao = dbHelper.getDao(Bank.class);
            List<Atm> atms = atmDao.queryForAll();
            for (Atm atm : atms) {

                LatLng atmLatLong = new LatLng(atm.getLatitude(), atm.getLongitude());

                //bankDao.refresh(atm.getBank());

                mMap.addMarker(new MarkerOptions()
                        .position(atmLatLong)
                        .title(atm.getBank().getName())
                        .snippet(atm.getBank().getPhone()));

                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(atmLatLong, 10));
            }


        } catch (SQLException e) {


        }
    }
}
