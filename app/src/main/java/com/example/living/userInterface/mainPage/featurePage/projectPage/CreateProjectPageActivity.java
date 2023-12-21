package com.example.living.userInterface.mainPage.featurePage.projectPage;

import android.app.ProgressDialog;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.living.userInterface.mainPage.featurePage.projectPage.viewModel.ViewModelCreateProjectPage;
import com.example.living.R;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateProjectPageActivity extends AppCompatActivity {
    private ViewModelCreateProjectPage viewModel;

    private ProgressDialog pdCreateProjectPageActivity;
    private RadioButton rbStatusProject;

    @BindView(R.id.etIdentifierProject) EditText etIdentifierProject;
    @BindView(R.id.actvNameProject) AutoCompleteTextView actvNameProject;
    @BindView(R.id.actvTypeProject) AutoCompleteTextView actvTypeProject;
    @BindView(R.id.etAccessProject) EditText etAccessProject;
    @BindView(R.id.rgStatusProject) RadioGroup rgStatusProject;
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

    @OnClick(R.id.bCreateProject)
    void createRequestCustomer() {
        pdCreateProjectPageActivity = new ProgressDialog(this);
        pdCreateProjectPageActivity.setCancelable(false);
        pdCreateProjectPageActivity.setMessage("Now Loading");
        pdCreateProjectPageActivity.show();

        String identifierProject = etIdentifierProject.getText().toString();
        String nameProject = actvNameProject.getText().toString();
        String typeProject = actvTypeProject.getText().toString();
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
        rbStatusProject = findViewById(selectedIdStatusProject);
        String statusProject = rbStatusProject.getText().toString();

        viewModel.createProject(
                identifierProject, nameProject, typeProject, accessProject, statusProject, locationProject,
                priceListProjectCash, priceListProjectCredit, promo, descriptionProject, bedroom, bathroom,
                carport, buildingArea, surfaceArea, facility, nameDeveloper, contactDeveloper, loanBank, handover
        );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_project_page_activity);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelCreateProjectPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Project");

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.loan_bank, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sLoanBank.setAdapter(adapter);

        setupUICreateProjectPageActivity();
        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getToastMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                pdCreateProjectPageActivity.dismiss();
                Toast.makeText(CreateProjectPageActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setupUICreateProjectPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tCreateProjectPageActivity = (Toolbar)findViewById(R.id.tCreateProjectPageActivity);
        tCreateProjectPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tCreateProjectPageActivity.setTitle("Create Project");
        tCreateProjectPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tCreateProjectPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
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
