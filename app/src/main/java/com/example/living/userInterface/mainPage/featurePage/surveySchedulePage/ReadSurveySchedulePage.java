package com.example.living.userInterface.mainPage.featurePage.surveySchedulePage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.living.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import butterknife.ButterKnife;


public class ReadSurveySchedulePage extends AppCompatActivity { // implements SearchView.OnQueryTextListener
    /*
    public static final String URL = "http://tamafamily.000webhostapp.com/db_tama_family/tb_survey_schedule/";
    private List<DataModelSurveySchedule> resultNext = new ArrayList<>();
    private RecyclerViewAdapterReadSurveySchedule recyclerViewAdapterReadSurveySchedule;

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.pb) ProgressBar pb;
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_survey_schedule_page_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tReadSurveySchedule = (Toolbar)findViewById(R.id.t_ReadSurveySchedule);
        tReadSurveySchedule.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tReadSurveySchedule.setTitle("Read Survey Schedule");
        tReadSurveySchedule.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tReadSurveySchedule.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        FloatingActionButton fabCreateSurveySchedule = (FloatingActionButton) findViewById(R.id.fabCreateSurveySchedule);
        fabCreateSurveySchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateSurveySchedulePage.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Create Survey Schedule", Toast.LENGTH_SHORT).show();
            }
        });

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Read Survey Schedule");

        /*
        recyclerViewAdapterReadSurveySchedule = new RecyclerViewAdapterReadSurveySchedule(this, resultNext);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        layoutManager.scrollToPositionWithOffset(0, 0);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.smoothScrollToPosition(0);
        rv.setAdapter(recyclerViewAdapterReadSurveySchedule);

        loadData();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem mi) {
        switch (mi.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.item_one:
                break;
            case R.id.item_two:
                break;
        }

        return super.onOptionsItemSelected(mi);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_one, menu);
        getMenuInflater().inflate(R.menu.menu_two, menu);

        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem mi = menu.findItem(R.id.item_search);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(mi);
        searchView.setQueryHint("Search Survey Schedule");
        searchView.setIconified(true);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APISurveySchedule api = retrofit.create(APISurveySchedule.class);
        Call<ResponseModelSurveySchedule> call = api.read();

        call.enqueue(new Callback<ResponseModelSurveySchedule>() {
            @Override
            public void onResponse(Call<ResponseModelSurveySchedule> call, Response<ResponseModelSurveySchedule> response) {
                String gValue = response.body().getValue();
                pb.setVisibility(View.GONE);
                if (gValue.equals("1")) {
                    resultNext = response.body().getResult();
                    recyclerViewAdapterReadSurveySchedule = new RecyclerViewAdapterReadSurveySchedule(ReadSurveySchedule.this, resultNext);
                    rv.setAdapter(recyclerViewAdapterReadSurveySchedule);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelSurveySchedule> call, Throwable throwable) { }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String read) {return false;}

    @Override
    public boolean onQueryTextChange(String Read) {
        rv.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APISurveySchedule api = retrofit.create(APISurveySchedule.class);
        Call<ResponseModelSurveySchedule> call = api.search(Read);

        call.enqueue(new Callback<ResponseModelSurveySchedule>() {
            @Override
            public void onResponse(Call<ResponseModelSurveySchedule> call, Response<ResponseModelSurveySchedule> response) {
                String gValue = response.body().getValue();
                pb.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                if (gValue.equals("1")) {
                    resultNext = response.body().getResult();
                    recyclerViewAdapterReadSurveySchedule = new RecyclerViewAdapterReadSurveySchedule(ReadSurveySchedule.this, resultNext);
                    rv.setAdapter(recyclerViewAdapterReadSurveySchedule);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelSurveySchedule> call, Throwable throwable) {
                pb.setVisibility(View.GONE);
            }
        });

        return true;
         */
    }
}