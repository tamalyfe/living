package com.example.living.userInterface.mainPage.AuthenticationWelcomePage;

import android.content.Intent;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.living.R;
import com.example.living.userInterface.mainPage.authenticationAccountPage.AuthenticationAccountPageTeamActivity;

public class WelcomePageTeamFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.welcome_page_team_fragment, container, false);

        CardView cvWelcomePageTeamFragment = view.findViewById(R.id.cvWelcomePageTeamFragment);

        cvWelcomePageTeamFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AuthenticationAccountPageTeamActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
