package com.example.living.userInterface.mainPage.featurePage.projectPage;

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

import com.example.living.R;
import com.example.living.data.remote.response.project.ItemResponseProject;
import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.retrofit.project.ApiServiceProject;
import com.example.living.userInterface.mainPage.featurePage.projectPage.adapter.ReadProjectPageAdapter;
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

public class ReadProjectPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_project/";
    private List<ItemResponseProject> listReadProjectPage = new ArrayList<>();
    private ReadProjectPageAdapter readProjectPageAdapter;

    @BindView(R.id.pbReadProject) ProgressBar pbReadProjectPage;
    @BindView(R.id.rvReadProject) RecyclerView rvReadProjectPage;
    @BindView(R.id.svReadProject) SearchView svReadProject;
    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.ivFilter) ImageView ivFilter;
    @BindView(R.id.ivSorting) ImageView ivSorting;
    @BindView(R.id.tvAmountProject) TextView tvAmountProject;

    private int itemCount = 0;
    private boolean isSortingAscending = true;
    private String selectedStatusProject = "";

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_project_page_activity);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Project");

        readProjectPageAdapter = new ReadProjectPageAdapter(this, listReadProjectPage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReadProjectPage.setLayoutManager(layoutManager);
        rvReadProjectPage.setItemAnimator(new DefaultItemAnimator());
        rvReadProjectPage.setAdapter(readProjectPageAdapter);

        setupUIReadProjectPageActivity();
        setupReadProjectPageActivity();
        setupSearchViewReadProject();

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
        popupMenu.getMenuInflater().inflate(R.menu.filter_project_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.filter_status_project_ready:
                        selectedStatusProject = "Ready";
                        break;
                    case R.id.filter_status_project_indent:
                        selectedStatusProject = "Indent";
                        break;
                    default:
                        selectedStatusProject = "";
                        break;
                }

                filterItemsByStatusProject(selectedStatusProject);
                return true;
            }
        });

        popupMenu.show();
    }

    private void filterItemsByStatusProject(String statusProject) {
        List<ItemResponseProject> filteredList = new ArrayList<>();

        for (ItemResponseProject item : listReadProjectPage) {
            if (item.getStatusProject().equalsIgnoreCase(statusProject)) {
                filteredList.add(item);
            }
        }

        updateRecyclerView(filteredList);
    }

    private void updateRecyclerView(List<ItemResponseProject> itemList) {
        listReadProjectPage.clear();
        listReadProjectPage.addAll(itemList);
        readProjectPageAdapter.notifyDataSetChanged();
    }

    public void showSortPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.sort_project_menu, popupMenu.getMenu());

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
        Collections.sort(listReadProjectPage, new Comparator<ItemResponseProject>() {
            @Override
            public int compare(ItemResponseProject item1, ItemResponseProject item2) {
                String name1 = item1.getNameProject();
                String name2 = item2.getNameProject();

                if (isSortingAscending) {
                    return name1.compareToIgnoreCase(name2);
                } else {
                    return name2.compareToIgnoreCase(name1);
                }
            }
        });

        readProjectPageAdapter.notifyDataSetChanged();
    }

    private void setupSearchViewReadProject() {
        svReadProject = findViewById(R.id.svReadProject);
        svReadProject.setQueryHint("Find");
        svReadProject.setOnQueryTextListener(this);
    }

    private void setupUIReadProjectPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tReadProjectPage = (Toolbar)findViewById(R.id.tReadProjectPage);
        tReadProjectPage.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tReadProjectPage.setTitle("Project");
        tReadProjectPage.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tReadProjectPage.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupReadProjectPageActivity() {
        FloatingActionButton fabReadProjectPage = findViewById(R.id.fabReadProjectPage);
        fabReadProjectPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadProjectPageActivity.this, CreateProjectPageActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceProject apiServiceProject = retrofit.create(ApiServiceProject.class);
        Call<ResponseProject> call = apiServiceProject.readProject();
        call.enqueue(new Callback<ResponseProject>() {
            @Override
            public void onResponse(Call<ResponseProject> call, Response<ResponseProject> response) {
                String value = response.body().getValue();
                pbReadProjectPage.setVisibility(View.GONE);

                if (value.equals("1")) {
                    listReadProjectPage = response.body().getResult();
                    itemCount = listReadProjectPage.size();
                    tvAmountProject.setText(String.valueOf(itemCount + " Project"));
                    readProjectPageAdapter = new ReadProjectPageAdapter(ReadProjectPageActivity.this, listReadProjectPage);
                    rvReadProjectPage.setAdapter(readProjectPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String textChange) {
        rvReadProjectPage.setVisibility(View.GONE);
        pbReadProjectPage.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceProject apiServiceProject = retrofit.create(ApiServiceProject.class);
        Call<ResponseProject> call = apiServiceProject.searchProject(textChange);
        call.enqueue(new Callback<ResponseProject>() {
            @Override
            public void onResponse(Call<ResponseProject> call, Response<ResponseProject> response) {
                String value = response.body().getValue();
                pbReadProjectPage.setVisibility(View.GONE);
                rvReadProjectPage.setVisibility(View.VISIBLE);

                if (value.equals("1")) {
                    listReadProjectPage = response.body().getResult();
                    itemCount = listReadProjectPage.size();
                    tvAmountProject.setText(String.valueOf(itemCount + " Project"));
                    readProjectPageAdapter = new ReadProjectPageAdapter(ReadProjectPageActivity.this, listReadProjectPage);
                    rvReadProjectPage.setAdapter(readProjectPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
                pbReadProjectPage.setVisibility(View.GONE);
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
        setupReadProjectPageActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}
