package com.example.living.userInterface.mainPage.featurePage.surveySchedulePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.living.R;

import butterknife.ButterKnife;

public class CreateSurveySchedulePage extends AppCompatActivity {
    /*
    public static final String URL = "http://tamafamily.000webhostapp.com/db_tama_family/tb_survey_schedule/";
    private RadioButton rbSurveyMethodShareBring;
    private ProgressDialog pd;

    @BindView(R.id.et_Code)
    EditText etCode; @BindView(R.id.et_Link) EditText etLink;
    @BindView(R.id.et_NameProjectListingProperty) EditText etNameProjectListingProperty; @BindView(R.id.et_LocationProjectListingProperty) EditText etLocationProjectListingProperty;
    @BindView(R.id.et_NameDeveloper) EditText etNameDeveloper; @BindView(R.id.et_LocationDeveloper) EditText etLocationDeveloper;
    @BindView(R.id.et_NameAgency) EditText etNameAgency; @BindView(R.id.et_LocationAgency) EditText etLocationAgency;
    @BindView(R.id.et_NameManagerAgency) EditText etNameManagerAgency; @BindView(R.id.et_ContactEmailSocialMediaManagerAgency) EditText etContactEmailSocialMediaManagerAgency; @BindView(R.id.et_AddressManagerAgency) EditText etAddressManagerAgency;
    @BindView(R.id.et_NameDigitalMarketing) EditText etNameDigitalMarketing; @BindView(R.id.et_ContactEmailSocialMediaDigitalMarketing) EditText etContactEmailSocialMediaDigitalMarketing; @BindView(R.id.et_AddressDigitalMarketing) EditText etAddressDigitalMarketing;
    @BindView(R.id.et_NameCustomer) EditText etNameCustomer; @BindView(R.id.et_ContactEmailSocialMediaCustomer) EditText etContactEmailSocialMediaCustomer; @BindView(R.id.et_AddressCustomer) EditText etAddressCustomer;
    @BindView(R.id.rg_SurveyMethod) RadioGroup rgSurveyMethod; @BindView(R.id.et_TimeSurveySchedule) EditText etTimeSurveySchedule; @BindView(R.id.et_DaySurveySchedule) EditText etDaySurveySchedule; @BindView(R.id.et_DateSurveySchedule) EditText etDateSurveySchedule;
    @BindView(R.id.et_Report) EditText etReport; @BindView(R.id.et_Interest) EditText etInterest; @BindView(R.id.et_Request) EditText etRequest; @BindView(R.id.et_Reward) EditText etReward; @BindView(R.id.et_Bonus) EditText etBonus;
    @BindView(R.id.et_Time) EditText etTime; @BindView(R.id.et_Day) EditText etDay; @BindView(R.id.et_Date) EditText etDate;

    TextView tvCounterReport; TextView tvCounterInterest; TextView tvCounterRequest; TextView tvCounterReward; TextView tvCounterBonus;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_survey_schedule_page_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tCreateSurveySchedule = (Toolbar)findViewById(R.id.t_CreateSurveySchedule);
        tCreateSurveySchedule.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tCreateSurveySchedule.setTitle("Create Survey Schedule");
        tCreateSurveySchedule.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tCreateSurveySchedule.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Create Survey Schedule");

        /*
        // Report //
        Drawable drawableReport = getResources().getDrawable(R.drawable.ic_description_black_24dp);
        int widthReport = drawableReport.getIntrinsicWidth();
        int heightReport = drawableReport.getIntrinsicHeight();
        drawableReport.setBounds(0, 0, widthReport, heightReport);
        etReport.setCompoundDrawables(null, null, drawableReport, null);

        tvCounterReport = findViewById(R.id.tv_CounterReport);
        tvCounterReport.setText("0 / 255");

        etReport.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etReport.getText().toString();
                int icounter = scounter.length();
                tvCounterReport.setText(icounter + " / 255");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 255) {
                    etReport.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etReport.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etReport.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        // Interest //
        Drawable drawableInterest = getResources().getDrawable(R.drawable.ic_description_black_24dp);
        int widthInterest = drawableInterest.getIntrinsicWidth();
        int heightInterest = drawableInterest.getIntrinsicHeight();
        drawableInterest.setBounds(0, 0, widthInterest, heightInterest);
        etInterest.setCompoundDrawables(null, null, drawableInterest, null);

        tvCounterInterest = findViewById(R.id.tv_CounterInterest);
        tvCounterInterest.setText("0 / 255");

        etInterest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etInterest.getText().toString();
                int icounter = scounter.length();
                tvCounterInterest.setText(icounter + " / 255");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 255) {
                    etInterest.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etInterest.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etInterest.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        // Request //
        Drawable drawableRequest = getResources().getDrawable(R.drawable.ic_description_black_24dp);
        int widthRequest = drawableRequest.getIntrinsicWidth();
        int heightRequest = drawableRequest.getIntrinsicHeight();
        drawableRequest.setBounds(0, 0, widthRequest, heightRequest);
        etRequest.setCompoundDrawables(null, null, drawableRequest, null);

        tvCounterRequest = findViewById(R.id.tv_CounterRequest);
        tvCounterRequest.setText("0 / 255");

        etRequest.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etRequest.getText().toString();
                int icounter = scounter.length();
                tvCounterRequest.setText(icounter + " / 255");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 255) {
                    etRequest.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etRequest.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etRequest.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        // Reward //
        Drawable drawableReward = getResources().getDrawable(R.drawable.ic_description_black_24dp);
        int widthReward = drawableReward.getIntrinsicWidth();
        int heightReward = drawableReward.getIntrinsicHeight();
        drawableReward.setBounds(0, 0, widthReward, heightReward);
        etReward.setCompoundDrawables(null, null, drawableReward, null);

        tvCounterReward = findViewById(R.id.tv_CounterReward);
        tvCounterReward.setText("0 / 255");

        etReward.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etReward.getText().toString();
                int icounter = scounter.length();
                tvCounterReward.setText(icounter + " / 255");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 255) {
                    etReward.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etReward.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etReward.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

        // Bonus //
        Drawable drawableBonus = getResources().getDrawable(R.drawable.ic_description_black_24dp);
        int widthBonus = drawableBonus.getIntrinsicWidth();
        int heightBonus = drawableBonus.getIntrinsicHeight();
        drawableBonus.setBounds(0, 0, widthBonus, heightBonus);
        etBonus.setCompoundDrawables(null, null, drawableBonus, null);

        tvCounterBonus = findViewById(R.id.tv_CounterBonus);
        tvCounterBonus.setText("0 / 255");

        etBonus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                String scounter = etBonus.getText().toString();
                int icounter = scounter.length();
                tvCounterBonus.setText(icounter + " / 255");
            }

            @Override
            public void afterTextChanged(Editable e) {
                if(e.toString().length() >= 255) {
                    etBonus.length();
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_dark_green_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etBonus.setCompoundDrawables(null, null, drawable, null);
                }
                else {
                    Drawable drawable = getResources().getDrawable(R.drawable.ic_description_black_24dp);
                    int width = drawable.getIntrinsicWidth();
                    int height = drawable.getIntrinsicHeight();
                    drawable.setBounds(0, 0, width, height);
                    etBonus.setCompoundDrawables(null, null, drawable, null);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(mi);
    }

    @OnClick(R.id.b_CreateSurveySchedule) void create() {
        pd = new ProgressDialog(this);
        pd.setCancelable(false);
        pd.setMessage("Loading Survey Schedule");
        pd.show();

        String code = etCode.getText().toString(); String link = etLink.getText().toString();
        String nameProjectListingProperty = etNameProjectListingProperty.getText().toString(); String locationProjectListingProperty = etLocationProjectListingProperty.getText().toString();
        String nameDeveloper = etNameDeveloper.getText().toString(); String locationDeveloper = etLocationDeveloper.getText().toString();
        String nameAgency = etNameAgency.getText().toString(); String locationAgency = etLocationAgency.getText().toString();
        String nameManagerAgency = etNameManagerAgency.getText().toString(); String contactEmailSocialMediaManagerAgency = etContactEmailSocialMediaManagerAgency.getText().toString(); String addressManagerAgency = etAddressManagerAgency.getText().toString();
        String nameDigitalMarketing = etNameDigitalMarketing.getText().toString(); String contactEmailSocialMediaDigitalMarketing = etContactEmailSocialMediaDigitalMarketing.getText().toString(); String addressDigitalMarketing = etAddressDigitalMarketing.getText().toString();
        String nameCustomer = etNameCustomer.getText().toString(); String contactEmailSocialMediaCustomer = etContactEmailSocialMediaCustomer.getText().toString(); String addressCustomer = etAddressCustomer.getText().toString();
        String timeSurveySchedule = etTimeSurveySchedule.getText().toString(); String daySurveySchedule = etDaySurveySchedule.getText().toString(); String dateSurveySchedule = etDateSurveySchedule.getText().toString();
        String report = etReport.getText().toString(); String interest = etInterest.getText().toString(); String request = etRequest.getText().toString(); String reward = etReward.getText().toString(); String bonus = etBonus.getText().toString();
        String time = etTime.getText().toString(); String day = etDay.getText().toString(); String date = etDate.getText().toString();

        int iSurveyMethod = rgSurveyMethod.getCheckedRadioButtonId();
        rbSurveyMethodShareBring = (RadioButton) findViewById(iSurveyMethod);
        String surveyMethod = rbSurveyMethodShareBring.getText().toString();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APISurveySchedule api = retrofit.create(APISurveySchedule.class);
        Call<ResponseModelSurveySchedule> call = api.create(code, link,
                nameProjectListingProperty, locationProjectListingProperty,
                nameDeveloper, locationDeveloper,
                nameAgency, locationAgency,
                nameManagerAgency, contactEmailSocialMediaManagerAgency, addressManagerAgency,
                nameDigitalMarketing, contactEmailSocialMediaDigitalMarketing, addressDigitalMarketing,
                nameCustomer, contactEmailSocialMediaCustomer, addressCustomer,
                surveyMethod, timeSurveySchedule, daySurveySchedule, dateSurveySchedule,
                report, interest, request, reward, bonus,
                time, day, date);
        call.enqueue(new Callback<ResponseModelSurveySchedule>() {
            @Override
            public void onResponse(Call<ResponseModelSurveySchedule> call, Response<ResponseModelSurveySchedule> response) {
                String gValue = response.body().getValue();
                String gMessage = response.body().getMessage();
                pd.dismiss();
                if (gValue.equals("1")) { Toast.makeText(CreateSurveySchedule.this, gMessage, Toast.LENGTH_SHORT).show(); finish(); }
                else { Toast.makeText(CreateSurveySchedule.this, gMessage, Toast.LENGTH_SHORT).show(); }
            }

            @Override
            public void onFailure(Call<ResponseModelSurveySchedule> call, Throwable throwable) {
                throwable.printStackTrace();
                pd.dismiss();
                Toast.makeText(CreateSurveySchedule.this, "Network Error Survey Schedule", Toast.LENGTH_SHORT).show();
            }
        });
         */
    }
}

