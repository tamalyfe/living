package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.living.R;
import com.example.living.data.remote.response.recruitmentCustomer.ResponseRecruitmentTeam;
import com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.viewModel.ViewModelDetailRecruitmentTeamPage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailRecruitmentTeamPageActivity extends AppCompatActivity {
    private ViewModelDetailRecruitmentTeamPage viewModel;

    @BindView(R.id.tvIdentifierRecruitmentTeam) TextView tvIdentifierRecruitmentTeam;
    @BindView(R.id.tvNameTeam) TextView tvNameTeam;
    @BindView(R.id.tvPostTeam) TextView tvPostTeam;
    @BindView(R.id.tvDomicileTeam) TextView tvDomicileTeam;
    @BindView(R.id.tvJobDescription) TextView tvJobDescription;
    @BindView(R.id.tvExperienceRecruitmentTeam) TextView tvExperienceRecruitmentTeam;
    @BindView(R.id.tvCertificate) TextView tvCertificate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_recruitment_team_page_activity);

        viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(ViewModelDetailRecruitmentTeamPage.class);

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Detail Recruitment Team");

        setupUIDetailRecruitmentTeamPageActivity();
        setupDetailRecruitmentTeamPageActivity();
    }

    private void setupUIDetailRecruitmentTeamPageActivity() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(
                    WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN,
                    WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tDetailRecruitmentTeamPageActivity = findViewById(R.id.tDetailRecruitmentTeamPageActivity);
        tDetailRecruitmentTeamPageActivity.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tDetailRecruitmentTeamPageActivity.setTitle("Detail Recruitment Team");
        tDetailRecruitmentTeamPageActivity.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tDetailRecruitmentTeamPageActivity.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setupDetailRecruitmentTeamPageActivity() {
        Intent intent = getIntent();
        String identifierRecruitmentTeam = intent.getStringExtra("identifier_recruitment_team");
        String nameTeam = intent.getStringExtra("name_team");
        String postTeam = intent.getStringExtra("post_team");
        String domicileTeam = intent.getStringExtra("domicile_team");
        String jobDescription = intent.getStringExtra("job_description");
        String experienceRecruitmentTeam = intent.getStringExtra("experience_recruitment_team");
        String certificate = intent.getStringExtra("certificate");

        tvIdentifierRecruitmentTeam.setText(identifierRecruitmentTeam); tvIdentifierRecruitmentTeam.setVisibility(View.GONE);
        tvNameTeam.setText(nameTeam);
        tvPostTeam.setText(postTeam);
        tvDomicileTeam.setText(domicileTeam);
        tvJobDescription.setText(jobDescription);
        tvExperienceRecruitmentTeam.setText(experienceRecruitmentTeam);
        tvCertificate.setText(certificate);
    }

    @OnClick(R.id.fabUpdateRecruitmentTeamPageActivity)
    void openNewActivity() {
        String identifierRecruitmentTeam = tvIdentifierRecruitmentTeam.getText().toString();
        String nameTeam = tvNameTeam.getText().toString();
        String postTeam = tvPostTeam.getText().toString();
        String domicileTeam = tvDomicileTeam.getText().toString();
        String jobDescription = tvJobDescription.getText().toString();
        String experienceRecruitmentTeam = tvExperienceRecruitmentTeam.getText().toString();
        String certificate = tvCertificate.getText().toString();

        Intent intent = new Intent(this, UpdateRecruitmentTeamPageActivity.class);
        intent.putExtra("identifier_recruitment_team", identifierRecruitmentTeam);
        intent.putExtra("name_team", nameTeam);
        intent.putExtra("post_team", postTeam);
        intent.putExtra("domicile_team", domicileTeam);
        intent.putExtra("job_description", jobDescription);
        intent.putExtra("experience_recruitment_team", experienceRecruitmentTeam);
        intent.putExtra("certificate", certificate);
        this.startActivity(intent);
    }

    @OnClick(R.id.fabDeleteRecruitmentTeamPageActivity)
    void showDeleteConfirmationDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Attention");
        alertDialogBuilder
                .setMessage("Check")
                .setCancelable(false)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String identifierRecruitmentTeam = tvIdentifierRecruitmentTeam.getText().toString();
                        viewModel.deleteRecruitmentTeam(identifierRecruitmentTeam);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

        viewModel.getResponseLiveData().observe(this, new Observer<ResponseRecruitmentTeam>() {
            @Override
            public void onChanged(ResponseRecruitmentTeam response) {
                String value = response.getValue();
                String message = response.getMessage();

                if (value.equals("1")) {
                    Toast.makeText(DetailRecruitmentTeamPageActivity.this, message, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(DetailRecruitmentTeamPageActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        setupDetailRecruitmentTeamPageActivity();
    }
}
