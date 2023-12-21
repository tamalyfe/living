package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
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
import com.example.living.R;
import com.example.living.data.remote.response.project.ItemResponseProject;
import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;
import com.example.living.data.remote.retrofit.project.ApiServiceProject;
import com.example.living.userInterface.mainPage.featurePage.matchPage.ReadMatchPageActivity;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.adapter.DetailRequestCustomerPageAdapter;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.viewModel.ViewModelDetailRequestCustomerPage;
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

public class DetailRequestCustomerPageActivity extends AppCompatActivity {
    private DrawerLayout drawerLayout;

    public static final String URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_project/";
    private List<ItemResponseProject> listReadProjectPage = new ArrayList<>();
    private DetailRequestCustomerPageAdapter detailRequestCustomerPageAdapter;

    @BindView(R.id.pbReadProject) ProgressBar pbReadProjectPage;
    @BindView(R.id.rvReadProject) RecyclerView rvReadProjectPage;

    private ViewModelDetailRequestCustomerPage viewModel;

    @BindView(R.id.tvIdentifierRequestCustomer) TextView tvIdentifierRequestCustomer;
    @BindView(R.id.tvNameCustomer) TextView tvNameCustomer;
    @BindView(R.id.tvContactCustomer) TextView tvContactCustomer;
    @BindView(R.id.tvDomicileCustomer) TextView tvDomicileCustomer;
    @BindView(R.id.tvDescriptionRequestCustomer) TextView tvDescriptionRequestCustomer;
    @BindView(R.id.tvLocationProject) TextView tvLocationProject;
    @BindView(R.id.tvPriceListProjectCash) TextView tvPriceListProjectCash;
    @BindView(R.id.tvPriceListProjectCredit) TextView tvPriceListProjectCredit;
    @BindView(R.id.tvStatusProject) TextView tvStatusProject;

    private int itemCount = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_request_customer_page_activity);

        drawerLayout = findViewById(R.id.drawer_layout);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelDetailRequestCustomerPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Request Customer");

        detailRequestCustomerPageAdapter = new DetailRequestCustomerPageAdapter(this, listReadProjectPage);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        rvReadProjectPage.setLayoutManager(layoutManager);
        rvReadProjectPage.setItemAnimator(new DefaultItemAnimator());
        rvReadProjectPage.setAdapter(detailRequestCustomerPageAdapter);

        setupReadProjectPageActivity();
        setupUIDetailRequestCustomerPageActivity();
        setupNavigationDetailRequestCustomerPageActivity();
        setupDetailRequestCustomerPageActivity();
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

    private void setupReadProjectPageActivity() {
        NavigationView navigationView = findViewById(R.id.navigation_view);
        View headerView = navigationView.getHeaderView(0);
        TextView tvAmountProject = headerView.findViewById(R.id.tvAmountProject);

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
                    detailRequestCustomerPageAdapter = new DetailRequestCustomerPageAdapter(DetailRequestCustomerPageActivity.this, listReadProjectPage);
                    rvReadProjectPage.setAdapter(detailRequestCustomerPageAdapter);
                }
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
            }
        });
    }

    private void setupUIDetailRequestCustomerPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tDetailRequestCustomerPageActivity = findViewById(R.id.tDetailRequestCustomerPageActivity);
        tDetailRequestCustomerPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tDetailRequestCustomerPageActivity.setTitle("Detail Request Customer");
        tDetailRequestCustomerPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tDetailRequestCustomerPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String contactCustomer = intent.getStringExtra("contact_customer");
        tvContactCustomer.setText(contactCustomer);
        makeClickableWhatsAppLink(contactCustomer);
    }

    private void setupNavigationDetailRequestCustomerPageActivity() {
        FloatingActionButton fabReadMatchPageActivity = findViewById(R.id.fabReadMatchPageActivity);
        fabReadMatchPageActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailRequestCustomerPageActivity.this, ReadMatchPageActivity.class);
                startActivity(intent);
                Toast.makeText(DetailRequestCustomerPageActivity.this, "Match", Toast.LENGTH_SHORT).show();
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

        tvContactCustomer.setMovementMethod(LinkMovementMethod.getInstance());
        tvContactCustomer.setText(spannable, TextView.BufferType.SPANNABLE);
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

    private void setupDetailRequestCustomerPageActivity() {
        Intent intent = getIntent();
        String identifierRequestCustomer = intent.getStringExtra("identifier_request_customer");
        String nameCustomer = intent.getStringExtra("name_customer");
        String contactCustomer = intent.getStringExtra("contact_customer");
        String domicileCustomer = intent.getStringExtra("domicile_customer");
        String descriptionRequestCustomer = intent.getStringExtra("description_request_customer");
        String locationProject = intent.getStringExtra("location_project");
        String priceListProjectCash = intent.getStringExtra("price_list_project_cash");
        String priceListProjectCredit = intent.getStringExtra("price_list_project_credit");
        String statusProject = intent.getStringExtra("status_project");

        tvIdentifierRequestCustomer.setText(identifierRequestCustomer); tvIdentifierRequestCustomer.setVisibility(View.GONE);
        tvNameCustomer.setText(nameCustomer);
        tvContactCustomer.setText(contactCustomer);
        tvDomicileCustomer.setText(domicileCustomer);
        tvDescriptionRequestCustomer.setText(descriptionRequestCustomer);
        tvLocationProject.setText(locationProject);
        tvPriceListProjectCash.setText(priceListProjectCash);
        tvPriceListProjectCredit.setText(priceListProjectCredit);
        tvStatusProject.setText(statusProject);
    }

    @OnClick(R.id.fabUpdateRequestCustomerPageActivity)
    void openNewActivity() {
        String identifierRequestCustomer = tvIdentifierRequestCustomer.getText().toString();
        String nameCustomer = tvNameCustomer.getText().toString();
        String contactCustomer = tvContactCustomer.getText().toString();
        String domicileCustomer = tvDomicileCustomer.getText().toString();
        String descriptionRequestCustomer = tvDescriptionRequestCustomer.getText().toString();
        String locationProject = tvLocationProject.getText().toString();
        String priceListProjectCash = tvPriceListProjectCash.getText().toString();
        String priceListProjectCredit = tvPriceListProjectCredit.getText().toString();
        String statusProject = tvStatusProject.getText().toString();

        Intent intent = new Intent(this, UpdateRequestCustomerPageActivity.class);
        intent.putExtra("identifier_request_customer", identifierRequestCustomer);
        intent.putExtra("name_customer", nameCustomer);
        intent.putExtra("contact_customer", contactCustomer);
        intent.putExtra("domicile_customer", domicileCustomer);
        intent.putExtra("description_request_customer", descriptionRequestCustomer);
        intent.putExtra("location_project", locationProject);
        intent.putExtra("price_list_project_cash", priceListProjectCash);
        intent.putExtra("price_list_project_credit", priceListProjectCredit);
        intent.putExtra("status_project", statusProject);
        this.startActivity(intent);
    }

    @OnClick(R.id.fabDeleteRequestCustomerPageActivity)
    void showDeleteConfirmationDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Attention");
        alertDialogBuilder
                .setMessage("Check")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String identifierRequestCustomer = tvIdentifierRequestCustomer.getText().toString();
                        viewModel.deleteRequestCustomer(identifierRequestCustomer);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        viewModel.getResponseLiveData().observe(this, new Observer<ResponseRequestCustomer>() {
            @Override
            public void onChanged(ResponseRequestCustomer response) {
                String value = response.getValue();
                String message = response.getMessage();

                if (value.equals("1")) {
                    Toast.makeText(DetailRequestCustomerPageActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailRequestCustomerPageActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupReadProjectPageActivity();
        setupDetailRequestCustomerPageActivity();
    }
}
