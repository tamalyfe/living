package com.example.living.userInterface.mainPage.authenticationAccountPage;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.living.R;
import com.example.living.userInterface.mainPage.AuthenticationWelcomePage.WelcomePageAgencyFragment;
import com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage.LoginPageAgencyActivity;
import com.example.living.userInterface.mainPage.featurePage.projectPage.ReadProjectPageActivity;
import com.example.living.userInterface.mainPage.forumPage.ForumPageActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountPageAgencyFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.account_page_agency_fragment, null);

        setupNavigateAccountPageAgencyFragment(view);
        return view;
    }

    private void setupNavigateAccountPageAgencyFragment(View view) {
        FloatingActionButton fabForumPage = view.findViewById(R.id.fabForumPage);
        fabForumPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(), ForumPageActivity.class);
                startActivity(intent);
            }
        });

        CardView cvLogOut = view.findViewById(R.id.cvLogOut);
        cvLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginPageAgencyActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
