package com.example.living.userInterface.mainPage.forumPage.timelinePage;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.living.R;

public class TimelinePageFragment extends Fragment {
    CardView cvReadTimeline;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.timeline_page_fragment, null);

        /*
        cvReadTimeline = v.findViewById(R.id.cv_ReadTimeline);
        cvReadTimeline.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), ReadTimeline.class);
                startActivity(intent);
                Toast.makeText(getContext(), "Read Timeline", Toast.LENGTH_SHORT).show();
            }
        });
         */

        return v;
        // return inflater.inflate(R.layout.timeline_fragment, null);
    }
}

