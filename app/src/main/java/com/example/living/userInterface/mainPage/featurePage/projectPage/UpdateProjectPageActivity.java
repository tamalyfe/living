package com.example.living.userInterface.mainPage.featurePage.projectPage;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.living.userInterface.mainPage.featurePage.projectPage.viewModel.ViewModelUpdateProjectPage;
import com.example.living.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateProjectPageActivity extends AppCompatActivity {
    private ViewModelUpdateProjectPage viewModel;

    private ProgressDialog pdUpdateProjectPageActivity;
    private RadioButton rbStatusProject;

    @BindView(R.id.etIdentifierProject) EditText etIdentifierProject;
    @BindView(R.id.actvNameProject) AutoCompleteTextView actvNameProject;
    @BindView(R.id.actvTypeProject) AutoCompleteTextView actvTypeProject;
    @BindView(R.id.etAccessProject) EditText etAccessProject;
    @BindView(R.id.rgStatusProject) RadioGroup rgStatusProject;
    @BindView(R.id.rbStatusProjectReady) RadioButton rbStatusProjectReady;
    @BindView(R.id.rbStatusProjectIndent) RadioButton rbStatusProjectIndent;
    @BindView(R.id.actvLocationProject) AutoCompleteTextView actvLocationProject;
    @BindView(R.id.etPriceListProjectCash) EditText etPriceListProjectCash;
    @BindView(R.id.etPriceListProjectCredit) EditText etPriceListProjectCredit;
    @BindView(R.id.etPromo) EditText etPromo;
    @BindView(R.id.etDescriptionProject) EditText etDescriptionProject;
    @BindView(R.id.etBedroom) EditText etBedroom;
    @BindView(R.id.etBathroom) EditText etBathroom;
    @BindView(R.id.etCarport) EditText etCarport;
    @BindView(R.id.etBuildingArea) EditText etBuildingArea;
    @BindView(R.id.etSurfaceArea) EditText etSurfaceArea;
    @BindView(R.id.etFacility) EditText etFacility;
    @BindView(R.id.etNameDeveloper) EditText etNameDeveloper;
    @BindView(R.id.etContactDeveloper) EditText etContactDeveloper;
    @BindView(R.id.sLoanBank) Spinner sLoanBank;
    @BindView(R.id.etHandover) EditText etHandover;

    TextView tvDescriptionProject;

    private List<String> nameProjectList = Arrays.asList(
            "Michelia Randu", "Permata Residence Satriamekar", "Trias Estesia", "Daniswara", "Bumi Pandawa Sejahtera",
            "Permata Adiwarna", "Mansion Hill Mekarwangi", "Metland ", "Cikarang Griya Pratama", "Malaika Sandrina Tania");
    private ArrayAdapter<String> nameProjectAdapter;

    private List<String> typeProjectList = Arrays.asList(
            "Rumah 1", "Rumah 2", "Rumah 3", "Rumah 4", "Rumah 5",
            "Rumah 6", "Rumah 7", "Rumah 8", "Rumah 9", "Rumah 10",
            "Rumah 11", "Rumah 12", "Rumah 13", "Rumah 14", "Rumah 15",
            "Rumah 16", "Rumah 17", "Rumah 18", "Rumah 19", "Rumah 20");
    private ArrayAdapter<String> typeProjectAdapter;

    private List<String> locationList = Arrays.asList("Babelan", "Tambun Utara", "Setu", "Tarumajaya", "Mustika Jaya", "Cikarang Barat", "Cibitung", "Sukatani");
    private ArrayAdapter<String> locationAdapter;

    @OnClick(R.id.bUpdateProject)
    void updateProject() {
        pdUpdateProjectPageActivity = new ProgressDialog(this);
        pdUpdateProjectPageActivity.setCancelable(false);
        pdUpdateProjectPageActivity.setMessage("Now Loading");
        pdUpdateProjectPageActivity.show();

        String identifierProject = etIdentifierProject.getText().toString();
        String nameProject = actvNameProject.getText().toString();
        String typeProject =actvTypeProject.getText().toString();
        String accessProject = etAccessProject.getText().toString();
        String locationProject = actvLocationProject.getText().toString();
        String priceListProjectCash = etPriceListProjectCash.getText().toString();
        String priceListProjectCredit = etPriceListProjectCredit.getText().toString();
        String promo = etPromo.getText().toString();
        String descriptionProject = etDescriptionProject.getText().toString();
        String bedroom = etBedroom.getText().toString();
        String bathroom = etBathroom.getText().toString();
        String carport = etCarport.getText().toString();
        String buildingArea = etBuildingArea.getText().toString();
        String surfaceArea = etSurfaceArea.getText().toString();
        String facility = etFacility.getText().toString();
        String nameDeveloper = etNameDeveloper.getText().toString();
        String contactDeveloper = etContactDeveloper.getText().toString();
        String loanBank = sLoanBank.getSelectedItem().toString();
        String handover = etHandover.getText().toString();

        int selectedIdStatusProject = rgStatusProject.getCheckedRadioButtonId();
        rbStatusProject = (RadioButton) findViewById(selectedIdStatusProject);
        String statusProject = rbStatusProject.getText().toString();

        viewModel.updateProject(
                identifierProject, nameProject, typeProject, accessProject, statusProject, locationProject,
                priceListProjectCash, priceListProjectCredit, promo, descriptionProject, bedroom, bathroom,
                carport, buildingArea, surfaceArea, facility, nameDeveloper, contactDeveloper, loanBank, handover
        );

        viewModel.getUpdateResult().observe(this, result -> {
            pdUpdateProjectPageActivity.dismiss();
            Toast.makeText(UpdateProjectPageActivity.this, result, Toast.LENGTH_SHORT).show();
            if (result.equals("Update Project, Success")) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_project_page_activity);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelUpdateProjectPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Project");

        Intent intent = getIntent();
        String identifierProject = intent.getStringExtra("identifier_project");
        String nameProject = intent.getStringExtra("name_project");
        String typeProject = intent.getStringExtra("type_project");
        String accessProject = intent.getStringExtra("access_project");
        String locationProject = intent.getStringExtra("location_project");
        String priceListProjectCash = intent.getStringExtra("price_list_project_cash");
        String priceListProjectCredit = intent.getStringExtra("price_list_project_credit");
        String statusProject = intent.getStringExtra("status_project");
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

        etIdentifierProject.setText(identifierProject);
        actvNameProject.setText(nameProject);
        actvTypeProject.setText(typeProject);
        etAccessProject.setText(accessProject);
        actvLocationProject.setText(locationProject);
        etPriceListProjectCash.setText(priceListProjectCash);
        etPriceListProjectCredit.setText(priceListProjectCredit);
        etPromo.setText(promo);
        etDescriptionProject.setText(descriptionProject);
        etBedroom.setText(bedroom);
        etBathroom.setText(bathroom);
        etCarport.setText(carport);
        etBuildingArea.setText(buildingArea);
        etSurfaceArea.setText(surfaceArea);
        etFacility.setText(facility);
        etNameDeveloper.setText(nameDeveloper);
        etContactDeveloper.setText(contactDeveloper);
        etHandover.setText(handover);

        if (statusProject.equals("Status Project Ready")) {
            rbStatusProjectReady.setChecked(true);
        } else {
            rbStatusProjectIndent.setChecked(true);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.loan_bank, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLoanBank.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(loanBank);
        sLoanBank.setSelection(spinnerPosition);


        setupUIUpdateProjectPageActivity();
    }

    private void setupUIUpdateProjectPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tUpdateProjectPageActivity = (Toolbar)findViewById(R.id.tUpdateProjectPageActivity);
        tUpdateProjectPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tUpdateProjectPageActivity.setTitle("Update Project");
        tUpdateProjectPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tUpdateProjectPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Drawable drawableReport = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
        int widthReport = drawableReport.getIntrinsicWidth();
        int heightReport = drawableReport.getIntrinsicHeight();
        drawableReport.setBounds(0, 0, widthReport, heightReport);
        etDescriptionProject.setCompoundDrawables(null, null, drawableReport, null);

        tvDescriptionProject = findViewById(R.id.tvDescriptionProject);
        tvDescriptionProject.setText("0 / 160");

        etDescriptionProject.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etDescriptionProject.getText().toString();
                int icounter = scounter.length();
                tvDescriptionProject.setText(icounter + " / 160");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 160) {
                    etDescriptionProject.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etDescriptionProject.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etDescriptionProject.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        nameProjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, nameProjectList);
        actvNameProject.setAdapter(nameProjectAdapter);

        typeProjectAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, typeProjectList);
        actvTypeProject.setAdapter(typeProjectAdapter);

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locationList);
        actvLocationProject.setAdapter(locationAdapter);
    }
}
