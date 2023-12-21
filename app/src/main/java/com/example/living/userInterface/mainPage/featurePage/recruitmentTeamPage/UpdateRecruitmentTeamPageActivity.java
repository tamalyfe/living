package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage;

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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.living.R;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.viewModel.ViewModelUpdateRecruitmentTeamPage;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UpdateRecruitmentTeamPageActivity extends AppCompatActivity {
    private ViewModelUpdateRecruitmentTeamPage viewModel;

    private ProgressDialog pdUpdateRecruitmentTeamPageActivity;

    @BindView(R.id.etIdentifierRecruitmentTeam) EditText etIdentifierRecruitmentTeam;
    @BindView(R.id.etNameTeam) EditText etNameTeam;
    @BindView(R.id.actvPostTeam) AutoCompleteTextView actvPostTeam;
    @BindView(R.id.actvDomicileTeam) AutoCompleteTextView actvDomicileTeam;
    @BindView(R.id.sJobDescription) Spinner sJobDescription;
    @BindView(R.id.etExperienceRecruitmentTeam) EditText etExperienceRecruitmentTeam;
    @BindView(R.id.etCertificate) EditText etCertificate;

    TextView tvExperienceRecruitmentTeam;

    private List<String> locationList = Arrays.asList("Babelan", "Tambun Utara", "Setu", "Tarumajaya", "Mustika Jaya", "Cikarang Barat", "Cibitung", "Sukatani");
    private ArrayAdapter<String> locationAdapter;

    private List<String> cityList = Arrays.asList("Jakarta", "Bogor", "Depok", "Tangerang", "Bekasi");
    private ArrayAdapter<String> cityAdapter;

    @OnClick(R.id.bUpdateRecruitmentTeam)
    void updateRecruitmentTeam() {
        pdUpdateRecruitmentTeamPageActivity = new ProgressDialog(this);
        pdUpdateRecruitmentTeamPageActivity.setCancelable(false);
        pdUpdateRecruitmentTeamPageActivity.setMessage("Now Loading");
        pdUpdateRecruitmentTeamPageActivity.show();

        String identifierRecruitmentTeam = etIdentifierRecruitmentTeam.getText().toString();
        String nameTeam = etNameTeam.getText().toString();
        String postTeam = actvPostTeam.getText().toString();
        String domicileTeam = actvDomicileTeam.getText().toString();
        String jobDescription = sJobDescription.getSelectedItem().toString();
        String experienceRecruitmentTeam = etExperienceRecruitmentTeam.getText().toString();
        String certificate = etCertificate.getText().toString();

        viewModel.updateRecruitmentTeam(
                identifierRecruitmentTeam, nameTeam, postTeam, domicileTeam,
                jobDescription, experienceRecruitmentTeam, certificate
        );

        viewModel.getUpdateResult().observe(this, result -> {
            pdUpdateRecruitmentTeamPageActivity.dismiss();
            Toast.makeText(UpdateRecruitmentTeamPageActivity.this, result, Toast.LENGTH_SHORT).show();
            if (result.equals("Update Recruitment Team, Success")) {
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_recruitment_team_page_activity);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelUpdateRecruitmentTeamPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Update Request Customer");

        Intent intent = getIntent();
        String identifierRecruitmentTeam = intent.getStringExtra("identifier_recruitment_team");
        String nameTeam = intent.getStringExtra("name_team");
        String postTeam = intent.getStringExtra("post_team");
        String domicileTeam = intent.getStringExtra("domicile_team");
        String jobDescription = intent.getStringExtra("job_description");
        String experienceRecruitmentTeam = intent.getStringExtra("experience_recruitment_team");
        String certificate = intent.getStringExtra("certificate");

        etIdentifierRecruitmentTeam.setText(identifierRecruitmentTeam);
        etNameTeam.setText(nameTeam);
        actvPostTeam.setText(postTeam);
        actvDomicileTeam.setText(domicileTeam);
        etExperienceRecruitmentTeam.setText(experienceRecruitmentTeam);
        etCertificate.setText(certificate);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.job_description, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sJobDescription.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition(jobDescription);
        sJobDescription.setSelection(spinnerPosition);

        setupUIUpdateRecruitmentTeamPageActivity();
    }

    private void setupUIUpdateRecruitmentTeamPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tUpdateRecruitmentTeamPageActivity = (Toolbar)findViewById(R.id.tUpdateRecruitmentTeamPageActivity);
        tUpdateRecruitmentTeamPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tUpdateRecruitmentTeamPageActivity.setTitle("Update Recruitment Team");
        tUpdateRecruitmentTeamPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tUpdateRecruitmentTeamPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Drawable drawableReport = getResources().getDrawable(R.drawable.ic_baseline_graphic_eq_24);
        int widthReport = drawableReport.getIntrinsicWidth();
        int heightReport = drawableReport.getIntrinsicHeight();
        drawableReport.setBounds(0, 0, widthReport, heightReport);
        etExperienceRecruitmentTeam.setCompoundDrawables(null, null, drawableReport, null);

        tvExperienceRecruitmentTeam = findViewById(R.id.tvExperienceRecruitmentTeam);
        tvExperienceRecruitmentTeam.setText("0 / 160");

        etExperienceRecruitmentTeam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etExperienceRecruitmentTeam.getText().toString();
                int icounter = scounter.length();
                tvExperienceRecruitmentTeam.setText(icounter + " / 160");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 160) {
                    etExperienceRecruitmentTeam.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_graphic_eq_24);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etExperienceRecruitmentTeam.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_baseline_graphic_eq_black_24);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etExperienceRecruitmentTeam.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        locationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locationList);
        actvPostTeam.setAdapter(locationAdapter);

        cityAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cityList);
        actvDomicileTeam.setAdapter(cityAdapter);
    }
}
