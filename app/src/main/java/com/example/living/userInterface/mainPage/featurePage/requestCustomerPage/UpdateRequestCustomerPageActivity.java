package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.living.R;
import com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.viewModel.ViewModelUpdateRequestCustomerPage;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateRequestCustomerPageActivity extends AppCompatActivity {
    private ViewModelUpdateRequestCustomerPage viewModel;

    private ProgressDialog pdUpdateRequestCustomerPageActivity;
    private RadioButton rbStatusProject;

    @BindView(R.id.etIdentifierRequestCustomer) EditText etIdentifierRequestCustomer;
    @BindView(R.id.etNameCustomer) EditText etNameCustomer;
    @BindView(R.id.etContactCustomer) EditText etContactCustomer;
    @BindView(R.id.actvDomicileCustomer) AutoCompleteTextView actvDomicileCustomer;
    @BindView(R.id.etDescriptionRequestCustomer) EditText etDescriptionRequestCustomer;
    @BindView(R.id.actvLocationProject) AutoCompleteTextView actvLocationProject;
    @BindView(R.id.etPriceListProjectCash) EditText etPriceListProjectCash;
    @BindView(R.id.etPriceListProjectCredit) EditText etPriceListProjectCredit;
    @BindView(R.id.rgStatusProject) RadioGroup rgStatusProject;
    @BindView(R.id.rbStatusProjectReady) RadioButton rbStatusProjectReady;
    @BindView(R.id.rbStatusProjectIndent) RadioButton rbStatusProjectIndent;

    TextView tvDescriptionRequestCustomer;

    private List<String> cityList = Arrays.asList("Jakarta", "Bogor", "Depok", "Tangerang", "Bekasi");
    private ArrayAdapter<String> cityAdapter;

    private List<String> locationList = Arrays.asList("Babelan", "Tambun Utara", "Setu", "Tarumajaya", "Mustika Jaya", "Cikarang Barat", "Cibitung", "Sukatani");
    private ArrayAdapter<String> locationAdapter;

    @OnClick(R.id.bUpdateRequestCustomer)
    void updateRequestCustomer() {
        pdUpdateRequestCustomerPageActivity = new ProgressDialog(this);
        pdUpdateRequestCustomerPageActivity.setCancelable(false);
        pdUpdateRequestCustomerPageActivity.setMessage("Now Loading");
        pdUpdateRequestCustomerPageActivity.show();

        String identifierRequestCustomer = etIdentifierRequestCustomer.getText().toString();
        String nameCustomer = etNameCustomer.getText().toString();
        String contactCustomer = etContactCustomer.getText().toString();
        String domicileCustomer = actvDomicileCustomer.getText().toString();
        String descriptionRequestCustomer = etDescriptionRequestCustomer.getText().toString();
        String locationProject = actvLocationProject.getText().toString();
        String priceListProjectCash = etPriceListProjectCash.getText().toString();
        String priceListProjectCredit = etPriceListProjectCredit.getText().toString();

        int selectedIdStatusProject = rgStatusProject.getCheckedRadioButtonId();
        rbStatusProject = (RadioButton) findViewById(selectedIdStatusProject);
        String statusProject = rbStatusProject.getText().toString();

        viewModel.updateRequestCustomer(
                identifierRequestCustomer, nameCustomer, contactCustomer,
                domicileCustomer, descriptionRequestCustomer, locationProject,
                priceListProjectCash, priceListProjectCredit, statusProject
        );

        viewModel.getUpdateResult().observe(this, result -> {
            pdUpdateRequestCustomerPageActivity.dismiss();
            Toast.makeText(UpdateRequestCustomerPageActivity.this, result, Toast.LENGTH_SHORT).show();
            if (result.equals("Update Request Customer, Success")) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_request_customer_page_activity);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelUpdateRequestCustomerPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Request Customer");

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

        etIdentifierRequestCustomer.setText(identifierRequestCustomer);
        etNameCustomer.setText(nameCustomer);
        etContactCustomer.setText(contactCustomer);
        actvDomicileCustomer.setText(domicileCustomer);
        etDescriptionRequestCustomer.setText(descriptionRequestCustomer);
        actvLocationProject.setText(locationProject);
        etPriceListProjectCash.setText(priceListProjectCash);
        etPriceListProjectCredit.setText(priceListProjectCredit);

        if (statusProject.equals("Status Project Ready")) {
            rbStatusProjectReady.setChecked(true);
        } else {
            rbStatusProjectIndent.setChecked(true);
        }

        setupUIUpdateRequestCustomerPageActivity();
    }

    private void setupUIUpdateRequestCustomerPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tUpdateRequestCustomerPageActivity = (Toolbar)findViewById(R.id.tUpdateRequestCustomerPageActivity);
        tUpdateRequestCustomerPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tUpdateRequestCustomerPageActivity.setTitle("Update Request Customer");
        tUpdateRequestCustomerPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tUpdateRequestCustomerPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Drawable drawableReport = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
        int widthReport = drawableReport.getIntrinsicWidth();
        int heightReport = drawableReport.getIntrinsicHeight();
        drawableReport.setBounds(0, 0, widthReport, heightReport);
        etDescriptionRequestCustomer.setCompoundDrawables(null, null, drawableReport, null);

        tvDescriptionRequestCustomer = findViewById(R.id.tvDescriptionRequestCustomer);
        tvDescriptionRequestCustomer.setText("0 / 160");

        etDescriptionRequestCustomer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etDescriptionRequestCustomer.getText().toString();
                int icounter = scounter.length();
                tvDescriptionRequestCustomer.setText(icounter + " / 160");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 160) {
                    etDescriptionRequestCustomer.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etDescriptionRequestCustomer.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etDescriptionRequestCustomer.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cityList);
        actvDomicileCustomer.setAdapter(cityAdapter);

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locationList);
        actvLocationProject.setAdapter(locationAdapter);
    }
}
