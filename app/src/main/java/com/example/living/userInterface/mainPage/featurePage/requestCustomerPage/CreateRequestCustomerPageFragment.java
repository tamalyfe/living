package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.living.R;
import com.example.living.userInterface.mainPage.homePage.adapter.HomePageProjectNewAdapter;

public class CreateRequestCustomerPageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.create_request_customer_page_fragment, null);

        return view;
    }
}
