package com.example.living.userInterface.mainPage.authenticationAccountPage.loginPage;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.living.R;
import com.example.living.userInterface.mainPage.AuthenticationWelcomePage.AuthenticationWelcomePageActivity;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageAgencyActivity;
import com.example.living.userInterface.mainPage.BottomNavigationViewMainPageTeamActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginPageTeamFragment extends Fragment {
    private EditText etPassword;
    private CheckBox cbPassword;
    private Button bLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_page_team_fragment, null);

        setupUILoginPageTeamFragment(view);
        setupNavigationLoginPageTeamFragment(view);
        return view;
    }

    private void setupUILoginPageTeamFragment(View view) {
        final Toolbar tLogin = view.findViewById(R.id.t_Login);
        tLogin.setNavigationIcon(R.drawable.ic_keyboard_arrow_left_dark_green_36dp);
        tLogin.setTitle("Login");
        tLogin.setTitleTextAppearance(getActivity(), R.style.setTitleTextAppearance);
        tLogin.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AuthenticationWelcomePageActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Welcome Page", Toast.LENGTH_SHORT).show();
            }
        });

        etPassword = view.findViewById(R.id.et_Password);
        cbPassword = view.findViewById(R.id.cb_Password);

        cbPassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (!isChecked) {
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }


    private void setupNavigationLoginPageTeamFragment(View view) {
        bLogin = view.findViewById(R.id.b_Login);

        bLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BottomNavigationViewMainPageTeamActivity.class);
                startActivity(intent);
                Toast.makeText(getActivity(), "Main Page", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
