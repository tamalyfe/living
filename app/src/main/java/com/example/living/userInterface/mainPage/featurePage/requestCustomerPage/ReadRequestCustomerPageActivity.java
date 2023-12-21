package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.living.data.remote.response.requestCustomer.ItemResponseRequestCustomer;
import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;
import com.example.living.data.remote.retrofit.requestCustomer.ApiServiceRequestCustomer;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.adapter.ReadRequestCustomerPageAdapter;
import com.example.living.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReadRequestCustomerPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_request_customer/";
    private List<ItemResponseRequestCustomer> listReadRequestCustomerPageActivity = new ArrayList<>();
    private ReadRequestCustomerPageAdapter readRequestCustomerPageAdapter;

    @BindView(R.id.pbReadRequestCustomer) ProgressBar pbReadRequestCustomerPageActivity;
    @BindView(R.id.rvReadRequestCustomer) RecyclerView rvReadRequestCustomerPageActivity;

    @BindView(R.id.svReadRequestCustomer) SearchView svReadRequestCustomer;
    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.ivFilter) ImageView ivFilter;
    @BindView(R.id.ivSorting) ImageView ivSorting;
    @BindView(R.id.tvAmountRequestCustomer) TextView tvAmountRequestCustomer;

    private int itemCount = 0;
    private boolean isSortingAscending = true;
    private String selectedDomicile = "";

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_request_customer_page_activity);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Request Customer");

        readRequestCustomerPageAdapter = new ReadRequestCustomerPageAdapter(this, listReadRequestCustomerPageActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReadRequestCustomerPageActivity.setLayoutManager(layoutManager);
        rvReadRequestCustomerPageActivity.setItemAnimator(new DefaultItemAnimator());
        rvReadRequestCustomerPageActivity.setAdapter(readRequestCustomerPageAdapter);

        setupUIReadRequestCustomerPageActivity();
        setupReadRequestCustomerPageActivity();
        setupSearchViewReadRequestCustomer();

        ivFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterPopupMenu(view);
            }
        });

        if (checkLocationPermissions()) {
            setupLocation();
        }
    }

    private boolean checkLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                return false;
            }
        }
        return true;
    }

    private void setupLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                String cityName = getCityName(latitude, longitude);
                tvLocation.setText(cityName);
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
            }
        };

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private String getCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
            if (addresses != null && addresses.size() > 0) {
                String cityName = addresses.get(0).getLocality();
                return cityName != null ? cityName : "Unknown";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "Unknown";
    }

    private void showFilterPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.filter_request_customer_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.filter_jakarta:
                        selectedDomicile = "Jakarta";
                        break;
                    case R.id.filter_bogor:
                        selectedDomicile = "Bogor";
                        break;
                    case R.id.filter_depok:
                        selectedDomicile = "Depok";
                        break;
                    case R.id.filter_tangerang:
                        selectedDomicile = "Tangerang";
                        break;
                    case R.id.filter_bekasi:
                        selectedDomicile = "Bekasi";
                        break;
                    default:
                        selectedDomicile = "";
                        break;
                }

                filterItemsByDomicile(selectedDomicile);
                return true;
            }
        });

        popupMenu.show();
    }

    private void filterItemsByDomicile(String domicile) {
        List<ItemResponseRequestCustomer> filteredList = new ArrayList<>();

        for (ItemResponseRequestCustomer item : listReadRequestCustomerPageActivity) {
            if (item.getDomicileCustomer().equalsIgnoreCase(domicile)) {
                filteredList.add(item);
            }
        }

        updateRecyclerView(filteredList);
    }

    private void updateRecyclerView(List<ItemResponseRequestCustomer> itemList) {
        listReadRequestCustomerPageActivity.clear();
        listReadRequestCustomerPageActivity.addAll(itemList);
        readRequestCustomerPageAdapter.notifyDataSetChanged();
    }

    public void showSortPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.sort_request_customer_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_sort_ascending:
                        isSortingAscending = true;
                        sortAlphabetically();
                        return true;
                    case R.id.action_sort_descending:
                        isSortingAscending = false;
                        sortAlphabetically();
                        return true;
                    default:
                        return false;
                }
            }
        });

        popupMenu.show();
    }

    private void sortAlphabetically() {
        Collections.sort(listReadRequestCustomerPageActivity, new Comparator<ItemResponseRequestCustomer>() {
            @Override
            public int compare(ItemResponseRequestCustomer item1, ItemResponseRequestCustomer item2) {
                String name1 = item1.getNameCustomer();
                String name2 = item2.getNameCustomer();

                if (isSortingAscending) {
                    return name1.compareToIgnoreCase(name2);
                } else {
                    return name2.compareToIgnoreCase(name1);
                }
            }
        });

        readRequestCustomerPageAdapter.notifyDataSetChanged();
    }

    private void setupSearchViewReadRequestCustomer() {
        svReadRequestCustomer = findViewById(R.id.svReadRequestCustomer);
        svReadRequestCustomer.setQueryHint("Find");
        svReadRequestCustomer.setOnQueryTextListener(this);
    }

    private void setupUIReadRequestCustomerPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tReadRequestCustomerPageActivity = (Toolbar)findViewById(R.id.tReadRequestCustomerPageActivity);
        tReadRequestCustomerPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tReadRequestCustomerPageActivity.setTitle("Request Customer");
        tReadRequestCustomerPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tReadRequestCustomerPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupReadRequestCustomerPageActivity() {
        FloatingActionButton fabReadRequestCustomerPageActivity = findViewById(R.id.fabReadRequestCustomerPageActivity);
        fabReadRequestCustomerPageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadRequestCustomerPageActivity.this, CreateRequestCustomerPageActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRequestCustomer apiServiceRequestCustomer = retrofit.create(ApiServiceRequestCustomer.class);
        Call<ResponseRequestCustomer> call = apiServiceRequestCustomer.readRequestCustomer();
        call.enqueue(new Callback<ResponseRequestCustomer>() {
            @Override
            public void onResponse(Call<ResponseRequestCustomer> call, Response<ResponseRequestCustomer> response) {
                String value = response.body().getValue();
                pbReadRequestCustomerPageActivity.setVisibility(View.GONE);

                if (value.equals("1")) {
                    listReadRequestCustomerPageActivity = response.body().getResult();
                    itemCount = listReadRequestCustomerPageActivity.size();
                    tvAmountRequestCustomer.setText(String.valueOf(itemCount + " Request Customer"));
                    readRequestCustomerPageAdapter = new ReadRequestCustomerPageAdapter(ReadRequestCustomerPageActivity.this, listReadRequestCustomerPageActivity);
                    rvReadRequestCustomerPageActivity.setAdapter(readRequestCustomerPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestCustomer> call, Throwable throwable) {
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String textChange) {
        rvReadRequestCustomerPageActivity.setVisibility(View.GONE);
        pbReadRequestCustomerPageActivity.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRequestCustomer apiServiceRequestCustomer = retrofit.create(ApiServiceRequestCustomer.class);
        Call<ResponseRequestCustomer> call = apiServiceRequestCustomer.searchRequestCustomer(textChange);
        call.enqueue(new Callback<ResponseRequestCustomer>() {
            @Override
            public void onResponse(Call<ResponseRequestCustomer> call, Response<ResponseRequestCustomer> response) {
                String value = response.body().getValue();
                pbReadRequestCustomerPageActivity.setVisibility(View.GONE);
                rvReadRequestCustomerPageActivity.setVisibility(View.VISIBLE);

                if (value.equals("1")) {
                    listReadRequestCustomerPageActivity = response.body().getResult();
                    itemCount = listReadRequestCustomerPageActivity.size();
                    tvAmountRequestCustomer.setText(String.valueOf(itemCount + " Request Customer"));
                    readRequestCustomerPageAdapter = new ReadRequestCustomerPageAdapter(ReadRequestCustomerPageActivity.this, listReadRequestCustomerPageActivity);
                    rvReadRequestCustomerPageActivity.setAdapter(readRequestCustomerPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestCustomer> call, Throwable throwable) {
                pbReadRequestCustomerPageActivity.setVisibility(View.GONE);
            }
        });
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupReadRequestCustomerPageActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}