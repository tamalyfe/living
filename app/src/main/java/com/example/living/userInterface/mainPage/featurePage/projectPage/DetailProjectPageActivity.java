package com.example.living.userInterface.mainPage.featurePage.projectPage;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.response.requestCustomer.ItemResponseRequestCustomer;
import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;
import com.example.living.data.remote.retrofit.requestCustomer.ApiServiceRequestCustomer;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;
import com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage.LoginPageAgencyActivity;
import com.example.living.userInterface.mainPage.featurePage.matchPage.ReadMatchPageActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.adapter.DetailProjectPageAdapter;
import com.example.living.userInterface.mainPage.featurePage.projectPage.viewModel.ViewModelDetailProjectPage;
import com.example.living.R;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.CreateRequestCustomerPageActivity;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.ReadRequestCustomerPageActivity;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.adapter.ReadRequestCustomerPageAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailProjectPageActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_request_customer/";
    private List<ItemResponseRequestCustomer> listReadRequestCustomerPageActivity = new ArrayList<>();
    private DetailProjectPageAdapter detailProjectPageAdapter;

    @BindView(R.id.pbReadRequestCustomer) ProgressBar pbReadRequestCustomerPageActivity;
    @BindView(R.id.rvReadRequestCustomer) RecyclerView rvReadRequestCustomerPageActivity;

    private ViewModelDetailProjectPage viewModel;

    @BindView(R.id.tvIdentifierProject) TextView tvIdentifierProject;
    @BindView(R.id.tvNameProject) TextView tvNameProject;
    @BindView(R.id.tvTypeProject) TextView tvTypeProject;
    @BindView(R.id.tvAccessProject) TextView tvAccessProject;
    @BindView(R.id.tvStatusProject) TextView tvStatusProject;
    @BindView(R.id.tvLocationProject) TextView tvLocationProject;
    @BindView(R.id.tvPriceListProjectCash) TextView tvPriceListProjectCash;
    @BindView(R.id.tvPriceListProjectCredit) TextView tvPriceListProjectCredit;
    @BindView(R.id.tvPromo) TextView tvPromo;
    @BindView(R.id.tvDescriptionProject) TextView tvDescriptionProject;
    @BindView(R.id.tvBedroom) TextView tvBedroom;
    @BindView(R.id.tvBathroom) TextView tvBathroom;
    @BindView(R.id.tvCarport) TextView tvCarport;
    @BindView(R.id.tvBuildingArea) TextView tvBuildingArea;
    @BindView(R.id.tvSurfaceArea) TextView tvSurfaceArea;
    @BindView(R.id.tvFacility) TextView tvFacility;
    @BindView(R.id.tvNameDeveloper) TextView tvNameDeveloper;
    @BindView(R.id.tvContactDeveloper) TextView tvContactDeveloper;
    @BindView(R.id.tvLoanBank) TextView tvLoanBank;
    @BindView(R.id.tvHandover) TextView tvHandover;

    private int itemCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_project_page_activity);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelDetailProjectPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Project");

        detailProjectPageAdapter = new DetailProjectPageAdapter(this, listReadRequestCustomerPageActivity);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReadRequestCustomerPageActivity.setLayoutManager(layoutManager);
        rvReadRequestCustomerPageActivity.setItemAnimator(new DefaultItemAnimator());
        rvReadRequestCustomerPageActivity.setAdapter(detailProjectPageAdapter);

        setupReadRequestCustomerPageActivity();
        setupUIDetailProjectPageActivity();
        setupNavigationDetailProjectPageActivity();
        setupDetailProjectPageActivity();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.END)) {
                drawerLayout.closeDrawer(GravityCompat.END);
            } else {
                drawerLayout.openDrawer(GravityCompat.END);
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupReadRequestCustomerPageActivity() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tvAmountRequestCustomer = headerView.findViewById(R.id.tvAmountRequestCustomer);

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
                    detailProjectPageAdapter = new DetailProjectPageAdapter(DetailProjectPageActivity.this, listReadRequestCustomerPageActivity);
                    rvReadRequestCustomerPageActivity.setAdapter(detailProjectPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestCustomer> call, Throwable throwable) {
            }
        });
    }

    private void setupUIDetailProjectPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tDetailProjectPageActivity = findViewById(R.id.tDetailProjectPageActivity);
        tDetailProjectPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tDetailProjectPageActivity.setTitle("Detail Project");
        tDetailProjectPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tDetailProjectPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String contactDeveloper = intent.getStringExtra("contact_developer");
        tvContactDeveloper.setText(contactDeveloper);
        makeClickableWhatsAppLink(contactDeveloper);
    }

    private void setupNavigationDetailProjectPageActivity() {
        FloatingActionButton fabReadMatchPageActivity = findViewById(R.id.fabReadMatchPageActivity);
        fabReadMatchPageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailProjectPageActivity.this, ReadMatchPageActivity.class);
                startActivity(intent);
                Toast.makeText(DetailProjectPageActivity.this, "Match", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void makeClickableWhatsAppLink(String phoneNumber) {
        SpannableString spannable = new SpannableString("WhatsApp : " + phoneNumber);

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openWhatsApp(phoneNumber);
            }
        };

        spannable.setSpan(clickableSpan, 10, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(0.8f), 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvContactDeveloper.setMovementMethod(LinkMovementMethod.getInstance());
        tvContactDeveloper.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    private void openWhatsApp(String phoneNumber) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + phoneNumber));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    private void setupDetailProjectPageActivity() {
        Intent intent = getIntent();
        String identifierProject = intent.getStringExtra("identifier_project");
        String nameProject = intent.getStringExtra("name_project");
        String typeProject = intent.getStringExtra("type_project");
        String accessProject = intent.getStringExtra("access_project");
        String statusProject = intent.getStringExtra("status_project");
        String locationProject = intent.getStringExtra("location_project");
        String priceListProjectCash = intent.getStringExtra("price_list_project_cash");
        String priceListProjectCredit = intent.getStringExtra("price_list_project_credit");
        String promo = intent.getStringExtra("promo");
        String descriptionProject = intent.getStringExtra("description_project");
        String bedroom = intent.getStringExtra("bedroom");
        String bathroom = intent.getStringExtra("bathroom");
        String carport = intent.getStringExtra("carport");
        String buildingArea = intent.getStringExtra("building_area");
        String surfaceArea = intent.getStringExtra("surface_area");
        String facility = intent.getStringExtra("facility");
        String nameDeveloper = intent.getStringExtra("name_developer");
        String contactDeveloper = intent.getStringExtra("contact_developer");
        String loanBank = intent.getStringExtra("loan_bank");
        String handover = intent.getStringExtra("handover");

        tvIdentifierProject.setText(identifierProject); tvIdentifierProject.setVisibility(View.GONE);
        tvNameProject.setText(nameProject);
        tvTypeProject.setText(typeProject);
        tvAccessProject.setText(accessProject);
        tvStatusProject.setText(statusProject + " | ");
        tvLocationProject.setText(locationProject);
        tvPriceListProjectCash.setText("Rp. " + priceListProjectCash);
        tvPriceListProjectCredit.setText(priceListProjectCredit + "/Month");
        tvPromo.setText("Rp. " + promo);
        tvDescriptionProject.setText(descriptionProject);
        tvBedroom.setText(bedroom);
        tvBathroom.setText(bathroom);
        tvCarport.setText(carport + " Carport");
        tvBuildingArea.setText(buildingArea);
        tvSurfaceArea.setText(surfaceArea);
        tvFacility.setText(facility + " Watt");
        tvNameDeveloper.setText("PT. " + nameDeveloper);
        tvContactDeveloper.setText(contactDeveloper);
        tvLoanBank.setText(loanBank);
        tvHandover.setText(handover + " Month");

        makeGoogleDriveLink(accessProject);
    }

    private void makeGoogleDriveLink(final String googleDriveLink) {
        SpannableString spannable = new SpannableString("Google Drive");

        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                openGoogleDrive(googleDriveLink);
            }
        };

        spannable.setSpan(clickableSpan, 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannable.setSpan(new ForegroundColorSpan(Color.BLUE), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannable.setSpan(new RelativeSizeSpan(0.8f), 0, spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvAccessProject.setMovementMethod(LinkMovementMethod.getInstance());
        tvAccessProject.setText(spannable, TextView.BufferType.SPANNABLE);
    }

    private void openGoogleDrive(String googleDriveLink) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleDriveLink));
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Toast.makeText(this, "Browser not Installed", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.fabUpdateProjectPageActivity)
    void openNewActivity() {
        String identifierProject = tvIdentifierProject.getText().toString(); tvIdentifierProject.setVisibility(View.GONE);
        String nameProject = tvNameProject.getText().toString();
        String typeProject = tvTypeProject.getText().toString();
        String accessProject = tvAccessProject.getText().toString();
        String statusProject = tvStatusProject.getText().toString();
        String locationProject = tvLocationProject.getText().toString();
        String priceListProjectCash = tvPriceListProjectCash.getText().toString();
        String priceListProjectCredit = tvPriceListProjectCredit.getText().toString();
        String promo = tvPromo.getText().toString();
        String descriptionProject = tvDescriptionProject.getText().toString();
        String bedroom = tvBedroom.getText().toString();
        String bathroom = tvBathroom.getText().toString();
        String carport = tvCarport.getText().toString();
        String buildingArea = tvBuildingArea.getText().toString();
        String surfaceArea = tvSurfaceArea.getText().toString();
        String facility = tvFacility.getText().toString();
        String nameDeveloper = tvNameDeveloper.getText().toString();
        String contactDeveloper = tvContactDeveloper.getText().toString();
        String loanBank = tvLoanBank.getText().toString();
        String handover = tvHandover.getText().toString();

        Intent intent = new Intent(this, UpdateProjectPageActivity.class);
        intent.putExtra("identifier_project", identifierProject);
        intent.putExtra("name_project", nameProject);
        intent.putExtra("type_project", typeProject);
        intent.putExtra("access_project", accessProject);
        intent.putExtra("status_project", statusProject);
        intent.putExtra("location_project", locationProject);
        intent.putExtra("price_list_project_cash", priceListProjectCash);
        intent.putExtra("price_list_project_credit", priceListProjectCredit);
        intent.putExtra("promo", promo);
        intent.putExtra("description_project", descriptionProject);
        intent.putExtra("bedroom", bedroom);
        intent.putExtra("bathroom", bathroom);
        intent.putExtra("carport", carport);
        intent.putExtra("building_area", buildingArea);
        intent.putExtra("surface_area", surfaceArea);
        intent.putExtra("facility", facility);
        intent.putExtra("name_developer", nameDeveloper);
        intent.putExtra("contact_developer", contactDeveloper);
        intent.putExtra("loan_bank", loanBank);
        intent.putExtra("handover", handover);

        this.startActivity(intent);
    }

    @OnClick(R.id.fabDeleteProjectPageActivity)
    void showDeleteConfirmationDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Attention");
        alertDialogBuilder
                .setMessage("Check")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String identifierProject = tvIdentifierProject.getText().toString();
                        viewModel.deleteProject(identifierProject);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        viewModel.getResponseLiveData().observe(this, new Observer<ResponseProject>() {
            @Override
            public void onChanged(ResponseProject response) {
                String value = response.getValue();
                String message = response.getMessage();

                if (value.equals("1")) {
                    Toast.makeText(DetailProjectPageActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailProjectPageActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupReadRequestCustomerPageActivity();
        setupDetailProjectPageActivity();
    }
}
