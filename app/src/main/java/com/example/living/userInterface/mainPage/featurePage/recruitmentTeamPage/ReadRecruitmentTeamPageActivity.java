package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage;

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
import com.example.living.data.remote.response.recruitmentCustomer.ItemResponseRecruitmentTeam;
import com.example.living.data.remote.response.recruitmentCustomer.ResponseRecruitmentTeam;
import com.example.living.data.remote.retrofit.recruitmentCustomer.ApiServiceRecruitmentTeam;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.adapter.ReadRecruitmentTeamPageAdapter;
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

public class ReadRecruitmentTeamPageActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_recruitment_team/";
    private List<ItemResponseRecruitmentTeam> listReadRecruitmentTeamPageActivity = new ArrayList<>();
    private ReadRecruitmentTeamPageAdapter readRecruitmentTeamPageAdapter;

    @BindView(R.id.pbReadRecruitmentTeam) ProgressBar pbReadRecruitmentTeam;
    @BindView(R.id.rvReadRecruitmentTeam) RecyclerView rvReadRecruitmentTeam;
    @BindView(R.id.svReadRecruitmentTeam) SearchView svReadRecruitmentTeam;
    @BindView(R.id.tvLocation) TextView tvLocation;
    @BindView(R.id.ivFilter) ImageView ivFilter;
    @BindView(R.id.ivSorting) ImageView ivSorting;
    @BindView(R.id.tvAmountRecruitmentTeam) TextView tvAmountRecruitmentTeam;

    private int itemCount = 0;
    private boolean isSortingAscending = true;
    private String selectedPost = "";

    private LocationManager locationManager;
    private LocationListener locationListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_recruitment_team_page_activity);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Team");

        readRecruitmentTeamPageAdapter = new ReadRecruitmentTeamPageAdapter(this, listReadRecruitmentTeamPageActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReadRecruitmentTeam.setLayoutManager(layoutManager);
        rvReadRecruitmentTeam.setItemAnimator(new DefaultItemAnimator());
        rvReadRecruitmentTeam.setAdapter(readRecruitmentTeamPageAdapter);

        setupUIReadRecruitmentTeamPageActivity();
        setupReadRecruitmentTeamPageActivity();
        setupSearchViewReadRecruitmentTeam();

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
        popupMenu.getMenuInflater().inflate(R.menu.filter_recruitment_team_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.filter_babelan:
                        selectedPost = "Babelan";
                        break;
                    case R.id.filter_tambun_utara:
                        selectedPost = "Tambun Utara";
                        break;
                    case R.id.filter_setu:
                        selectedPost = "Setu";
                        break;
                    case R.id.filter_tarumajaya:
                        selectedPost = "Tarumajaya";
                        break;
                    case R.id.filter_mustika_jaya:
                        selectedPost = "Mustika Jaya";
                        break;
                    case R.id.filter_cikarang_barat:
                        selectedPost = "Cikarang Barat";
                        break;
                    case R.id.filter_cibitung:
                        selectedPost = "Cibitung";
                        break;
                    case R.id.filter_sukatani:
                        selectedPost = "Sukatani";
                        break;
                    default:
                        selectedPost = "";
                        break;
                }

                filterItemsByPost(selectedPost);
                return true;
            }
        });

        popupMenu.show();
    }

    private void filterItemsByPost(String post) {
        List<ItemResponseRecruitmentTeam> filteredList = new ArrayList<>();

        for (ItemResponseRecruitmentTeam item : listReadRecruitmentTeamPageActivity) {
            if (item.getPostTeam().equalsIgnoreCase(post)) {
                filteredList.add(item);
            }
        }

        updateRecyclerView(filteredList);
    }

    private void updateRecyclerView(List<ItemResponseRecruitmentTeam> itemList) {
        listReadRecruitmentTeamPageActivity.clear();
        listReadRecruitmentTeamPageActivity.addAll(itemList);
        readRecruitmentTeamPageAdapter.notifyDataSetChanged();
    }

    public void showSortPopupMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        popupMenu.getMenuInflater().inflate(R.menu.sort_recruitment_team_menu, popupMenu.getMenu());

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
        Collections.sort(listReadRecruitmentTeamPageActivity, new Comparator<ItemResponseRecruitmentTeam>() {
            @Override
            public int compare(ItemResponseRecruitmentTeam item1, ItemResponseRecruitmentTeam item2) {
                String name1 = item1.getNameTeam();
                String name2 = item2.getNameTeam();

                if (isSortingAscending) {
                    return name1.compareToIgnoreCase(name2);
                } else {
                    return name2.compareToIgnoreCase(name1);
                }
            }
        });

        readRecruitmentTeamPageAdapter.notifyDataSetChanged();
    }

    private void setupSearchViewReadRecruitmentTeam() {
        svReadRecruitmentTeam = findViewById(R.id.svReadRecruitmentTeam);
        svReadRecruitmentTeam.setQueryHint("Find");
        svReadRecruitmentTeam.setOnQueryTextListener(this);
    }

    private void setupUIReadRecruitmentTeamPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tReadRecruitmentTeamPageActivity = (Toolbar)findViewById(R.id.tReadRecruitmentTeamPageActivity);
        tReadRecruitmentTeamPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tReadRecruitmentTeamPageActivity.setTitle("Team");
        tReadRecruitmentTeamPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tReadRecruitmentTeamPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupReadRecruitmentTeamPageActivity() {
        FloatingActionButton fabReadRecruitmentTeamPageActivity = findViewById(R.id.fabReadRecruitmentTeamPageActivity);
        fabReadRecruitmentTeamPageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ReadRecruitmentTeamPageActivity.this, CreateRecruitmentTeamPageActivity.class);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRecruitmentTeam apiServiceRecruitmentTeam = retrofit.create(ApiServiceRecruitmentTeam.class);
        Call<ResponseRecruitmentTeam> call = apiServiceRecruitmentTeam.readRecruitmentTeam();
        call.enqueue(new Callback<ResponseRecruitmentTeam>() {
            @Override
            public void onResponse(Call<ResponseRecruitmentTeam> call, Response<ResponseRecruitmentTeam> response) {
                String value = response.body().getValue();
                pbReadRecruitmentTeam.setVisibility(View.GONE);

                if (value.equals("1")) {
                    listReadRecruitmentTeamPageActivity = response.body().getResult();
                    itemCount = listReadRecruitmentTeamPageActivity.size();
                    tvAmountRecruitmentTeam.setText(String.valueOf(itemCount + " Team"));
                    readRecruitmentTeamPageAdapter = new ReadRecruitmentTeamPageAdapter(ReadRecruitmentTeamPageActivity.this, listReadRecruitmentTeamPageActivity);
                    rvReadRecruitmentTeam.setAdapter(readRecruitmentTeamPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseRecruitmentTeam> call, Throwable throwable) {
            }
        });
    }

    @Override
    public boolean onQueryTextChange(String textChange) {
        rvReadRecruitmentTeam.setVisibility(View.GONE);
        pbReadRecruitmentTeam.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceRecruitmentTeam apiServiceRecruitmentTeam = retrofit.create(ApiServiceRecruitmentTeam.class);
        Call<ResponseRecruitmentTeam> call = apiServiceRecruitmentTeam.searchRecruitmentTeam(textChange);
        call.enqueue(new Callback<ResponseRecruitmentTeam>() {
            @Override
            public void onResponse(Call<ResponseRecruitmentTeam> call, Response<ResponseRecruitmentTeam> response) {
                String value = response.body().getValue();
                pbReadRecruitmentTeam.setVisibility(View.GONE);
                rvReadRecruitmentTeam.setVisibility(View.VISIBLE);

                if (value.equals("1")) {
                    listReadRecruitmentTeamPageActivity = response.body().getResult();
                    itemCount = listReadRecruitmentTeamPageActivity.size();
                    tvAmountRecruitmentTeam.setText(String.valueOf(itemCount + " Team"));
                    readRecruitmentTeamPageAdapter = new ReadRecruitmentTeamPageAdapter(ReadRecruitmentTeamPageActivity.this, listReadRecruitmentTeamPageActivity);
                    rvReadRecruitmentTeam.setAdapter(readRecruitmentTeamPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseRecruitmentTeam> call, Throwable throwable) {
                pbReadRecruitmentTeam.setVisibility(View.GONE);
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
        setupReadRecruitmentTeamPageActivity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}