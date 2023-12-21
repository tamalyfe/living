package com.example.living.userInterface.mainPage.featurePage.ActivityRecordPage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.living.R;

import butterknife.ButterKnife;


public class ReadActivityRecordPage extends AppCompatActivity { // implements SearchView.OnQueryTextListener
    /*
    public static final String URL = "http://tamafamily.000webhostapp.com/db_tama_family/tb_investor/";
    private List<DataModelInvestor> resultNext = new ArrayList<>();
    private RecyclerViewAdapterReadInvestor recyclerViewAdapterReadInvestor;

    private ProgressBar pbProgressBarReadInvestor;
    private TextView tvProgressBarReadInvestor;
    private int iProgressBarReadInvestor = 0;

    SharedPreferences spProgressBarReadInvestor;
    final String KEY_SavedProgressBarReadInvestor = "Saved Progress Bar Read Investor";
    final String KEY_SavedTextViewProgressBarReadInvestor = "Saved Text View Progress Bar Read Investor";

    @BindView(R.id.rv) RecyclerView rv;
    @BindView(R.id.pb) ProgressBar pb;
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_activity_record_page_activity);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
            getWindow().setStatusBarColor(Color.GRAY);
        }

        getSupportActionBar().hide();

        final Toolbar tReadInvestor = (Toolbar)findViewById(R.id.tActivityRecord);
        tReadInvestor.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tReadInvestor.setTitle("Read Activity Record");
        tReadInvestor.setTitleTextAppearance(getApplicationContext(), R.style.setTitleTextAppearance);
        tReadInvestor.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Read Activity Record");

        /*
        FloatingActionButton fabCreateInvestor = (FloatingActionButton) findViewById(R.id.fab_CreateInvestor);
        fabCreateInvestor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateInvestor.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Create Investor", Toast.LENGTH_SHORT).show();
            }
        });

        pbProgressBarReadInvestor = (ProgressBar)findViewById(R.id.pb_ProgressBarReadInvestor);
        tvProgressBarReadInvestor = (TextView)findViewById(R.id.tv_ProgressBarReadInvestor);

        spProgressBarReadInvestor = getPreferences(MODE_PRIVATE);
        int ikspbri = spProgressBarReadInvestor.getInt(KEY_SavedProgressBarReadInvestor, iProgressBarReadInvestor);
        if(ikspbri != iProgressBarReadInvestor) {pbProgressBarReadInvestor.setProgress(ikspbri);}
        String skstvpbri = spProgressBarReadInvestor.getString(KEY_SavedTextViewProgressBarReadInvestor, null);
        if(skstvpbri != null) {tvProgressBarReadInvestor.setText(skstvpbri);}

        Button bProgressBarReadInvestorIncrease = (Button)findViewById(R.id.b_ProgressBarReadInvestorIncrease);
        bProgressBarReadInvestorIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iProgressBarReadInvestor = pbProgressBarReadInvestor.getProgress();
                if(iProgressBarReadInvestor < 100) {
                    iProgressBarReadInvestor += 1;
                    pbProgressBarReadInvestor.setProgress(iProgressBarReadInvestor);
                    pbProgressBarReadInvestor.setMax(100);
                    tvProgressBarReadInvestor.setText(pbProgressBarReadInvestor.getProgress() + " / " + pbProgressBarReadInvestor.getMax());
                }

                SharedPreferences.Editor speProgressBarReadInvestor = getPreferences(MODE_PRIVATE).edit();
                speProgressBarReadInvestor.putInt(KEY_SavedProgressBarReadInvestor, pbProgressBarReadInvestor.getProgress());
                speProgressBarReadInvestor.putString(KEY_SavedTextViewProgressBarReadInvestor, tvProgressBarReadInvestor.getText().toString());
                speProgressBarReadInvestor.commit();
            }
        });

        Button bProgressBarReadInvestorDecrease = (Button)findViewById(R.id.b_ProgressBarReadInvestorDecrease);
        bProgressBarReadInvestorDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iProgressBarReadInvestor = pbProgressBarReadInvestor.getProgress();
                if(iProgressBarReadInvestor > 0) {
                    iProgressBarReadInvestor -= 1;
                    pbProgressBarReadInvestor.setProgress(iProgressBarReadInvestor);
                    pbProgressBarReadInvestor.setMax(100);
                    tvProgressBarReadInvestor.setText(pbProgressBarReadInvestor.getProgress() + " / " + pbProgressBarReadInvestor.getMax());
                }

                SharedPreferences.Editor speProgressBarReadInvestor = getPreferences(MODE_PRIVATE).edit();
                speProgressBarReadInvestor.putInt(KEY_SavedProgressBarReadInvestor, pbProgressBarReadInvestor.getProgress());
                speProgressBarReadInvestor.putString(KEY_SavedTextViewProgressBarReadInvestor, tvProgressBarReadInvestor.getText().toString());
                speProgressBarReadInvestor.commit();
            }
        });

        recyclerViewAdapterReadInvestor = new RecyclerViewAdapterReadInvestor(this, resultNext);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, true);
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        layoutManager.scrollToPositionWithOffset(0, 0);
        rv.setLayoutManager(layoutManager);
        rv.setItemAnimator(new DefaultItemAnimator());
        rv.smoothScrollToPosition(0);
        rv.setAdapter(recyclerViewAdapterReadInvestor);

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
        searchView.setQueryHint("Search Investor");
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
        APIInvestor api = retrofit.create(APIInvestor.class);
        Call<ResponseModelInvestor> call = api.read();

        call.enqueue(new Callback<ResponseModelInvestor>() {
            @Override
            public void onResponse(Call<ResponseModelInvestor> call, Response<ResponseModelInvestor> response) {
                String gValue = response.body().getValue();
                pb.setVisibility(View.GONE);
                if (gValue.equals("1")) {
                    resultNext = response.body().getResult();
                    recyclerViewAdapterReadInvestor = new RecyclerViewAdapterReadInvestor(ReadInvestor.this, resultNext);
                    rv.setAdapter(recyclerViewAdapterReadInvestor);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelInvestor> call, Throwable throwable) { }
        });
    }

    @Override
    public boolean onQueryTextSubmit(String read) {return false;}

    @Override
    public boolean onQueryTextChange(String Read) {
        rv.setVisibility(View.GONE);
        pb.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL).addConverterFactory(GsonConverterFactory.create()).build();
        APIInvestor api = retrofit.create(APIInvestor.class);
        Call<ResponseModelInvestor> call = api.search(Read);

        call.enqueue(new Callback<ResponseModelInvestor>() {
            @Override
            public void onResponse(Call<ResponseModelInvestor> call, Response<ResponseModelInvestor> response) {
                String gValue = response.body().getValue();
                pb.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                if (gValue.equals("1")) {
                    resultNext = response.body().getResult();
                    recyclerViewAdapterReadInvestor = new RecyclerViewAdapterReadInvestor(ReadInvestor.this, resultNext);
                    rv.setAdapter(recyclerViewAdapterReadInvestor);
                }
            }

            @Override
            public void onFailure(Call<ResponseModelInvestor> call, Throwable throwable) {
                pb.setVisibility(View.GONE);
            }
        });

        return true;
         */
    }
}