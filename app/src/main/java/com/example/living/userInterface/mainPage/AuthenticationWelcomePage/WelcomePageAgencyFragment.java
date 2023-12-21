package com.example.living.userInterface.mainPage.AuthenticationWelcomePage;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.cardview.widget.CardView;
import com.example.living.R;
import com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage.LoginPageAgencyActivity;

public class WelcomePageAgencyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_page_agency_fragment, container, false);

        CardView cvWelcomePageAgencyFragment = view.findViewById(R.id.cvWelcomePageAgencyFragment);

        cvWelcomePageAgencyFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginPageAgencyActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
