package com.example.living.userInterface.mainPage.homePage;

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

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.living.R;
import com.example.living.data.remote.response.project.ItemResponseProject;
import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.retrofit.project.ApiServiceProject;
import com.example.living.userInterface.mainPage.featurePage.ActivityRecordPage.ReadActivityRecordPage;
import com.example.living.userInterface.mainPage.featurePage.FeaturePageActivity;
import com.example.living.userInterface.mainPage.featurePage.matchPage.ReadMatchPageActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.CreateProjectPageActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.ReadProjectPageActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.adapter.ReadProjectPageAdapter;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.ReadRecruitmentTeamPageActivity;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.ReadRequestCustomerPageActivity;
import com.example.living.userInterface.mainPage.featurePage.surveySchedulePage.ReadSurveySchedulePage;
import com.example.living.userInterface.mainPage.forumPage.ForumPageActivity;
import com.example.living.userInterface.mainPage.homePage.adapter.HomePageProjectNewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomePageFragment extends Fragment implements SearchView.OnQueryTextListener {
    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_project/";
    private List<ItemResponseProject> listReadProjectPage = new ArrayList<>();
    private HomePageProjectNewAdapter homePageProjectNewAdapter;

    private ProgressBar pbReadProjectPage;
    private RecyclerView rvReadProjectPage;
    private TextView tvLocation;
    private TextView tvAmountProject;

    private int itemCount = 0;

    private LocationManager locationManager;
    private LocationListener locationListener;

    CardView cvProject;
    CardView cvTeam;
    CardView cvSurveySchedule;
    CardView cvRequestCustomer;
    CardView cvMatch;
    CardView cvActivityReport;
    CardView cvAll;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_page_fragment, null);

        rvReadProjectPage = view.findViewById(R.id.rvReadProjectNew);
        tvLocation = view.findViewById(R.id.tvLocation);

        homePageProjectNewAdapter = new HomePageProjectNewAdapter(requireContext(), listReadProjectPage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(requireContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);
        rvReadProjectPage.setLayoutManager(layoutManager);
        rvReadProjectPage.setItemAnimator(new DefaultItemAnimator());
        rvReadProjectPage.setAdapter(homePageProjectNewAdapter);

        setupNavigationHomePageFragment(view);
        setupReadProjectPageActivity(view);

        if (checkLocationPermissions()) {
            setupLocation();
        }
        return view;
    }

    private boolean checkLocationPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (requireContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ||
                    requireContext().checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
                return false;
            }
        }
        return true;
    }

    private void setupNavigationHomePageFragment(View view) {
        FloatingActionButton fabForumPage = view.findViewById(R.id.fabForumPage);
        fabForumPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ForumPageActivity.class);
                startActivity(intent);
            }
        });

        TextView tvProjectNew = view.findViewById(R.id.tvProjectNew);
        tvProjectNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent projectIntent = new Intent(requireContext(), ReadProjectPageActivity.class);
                startActivity(projectIntent);
            }
        });

        SearchView svReadProject = view.findViewById(R.id.svReadProject);
        svReadProject.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent projectIntent = new Intent(requireContext(), ReadProjectPageActivity.class);
                startActivity(projectIntent);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        cvProject = view.findViewById(R.id.cvProject);
        cvProject.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadProjectPageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Project", Toast.LENGTH_SHORT).show();
            }
        });

        cvTeam = view.findViewById(R.id.cvTeam);
        cvTeam.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadRecruitmentTeamPageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Team", Toast.LENGTH_SHORT).show();
            }
        });

        cvSurveySchedule = view.findViewById(R.id.cvSurveySchedule);
        cvSurveySchedule.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadSurveySchedulePage.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Survey Schedule", Toast.LENGTH_SHORT).show();
            }
        });

        cvRequestCustomer = view.findViewById(R.id.cvRequestCustomer);
        cvRequestCustomer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadRequestCustomerPageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Request Customer", Toast.LENGTH_SHORT).show();
            }
        });

        cvMatch = view.findViewById(R.id.cvMatch);
        cvMatch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadMatchPageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Match", Toast.LENGTH_SHORT).show();
            }
        });

        cvActivityReport = view.findViewById(R.id.cvActivityReport);
        cvActivityReport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ReadActivityRecordPage.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Activity Report", Toast.LENGTH_SHORT).show();
            }
        });

        cvAll = view.findViewById(R.id.cvAll);
        cvAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), FeaturePageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "All", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupLocation() {
        locationManager = (LocationManager) requireContext().getSystemService(Context.LOCATION_SERVICE);

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

        if (ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
    }

    private String getCityName(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(requireContext(), Locale.getDefault());
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

    private void setupReadProjectPageActivity(View view) {
        pbReadProjectPage = view.findViewById(R.id.pbReadProjectNew);
        rvReadProjectPage = view.findViewById(R.id.rvReadProjectNew);

        tvAmountProject = view.findViewById(R.id.tvAmountProject);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiServiceProject apiServiceProject = retrofit.create(ApiServiceProject.class);
        Call<ResponseProject> call = apiServiceProject.readProject();
        call.enqueue(new Callback<ResponseProject>() {
            @Override
            public void onResponse(Call<ResponseProject> call, Response<ResponseProject> response) {
                if (isAdded()) {
                    String value = response.body().getValue();
                    pbReadProjectPage.setVisibility(View.GONE);

                    if (value.equals("1")) {
                        listReadProjectPage = response.body().getResult();
                        itemCount = listReadProjectPage.size();
                        tvAmountProject.setText(String.valueOf(itemCount + " Project"));
                        homePageProjectNewAdapter = new HomePageProjectNewAdapter(requireContext(), listReadProjectPage);
                        rvReadProjectPage.setAdapter(homePageProjectNewAdapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
            }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void onResume() {
        super.onResume();
        setupReadProjectPageActivity(getView());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (locationManager != null && locationListener != null) {
            locationManager.removeUpdates(locationListener);
        }
    }
}