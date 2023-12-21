package com.example.living.userInterface.mainPage.forumPage;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.living.userInterface.mainPage.forumPage.helpSupportPage.HelpSupportPageFragment;
import com.example.living.userInterface.mainPage.forumPage.learnPage.LearnPageFragment;
import com.example.living.userInterface.mainPage.forumPage.timelinePage.TimelinePageFragment;

public class ViewPagerForumPageAdapter extends FragmentPagerAdapter {
    private int numberOfTabs;

    ViewPagerForumPageAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new TimelinePageFragment();
            case 1:
                return new LearnPageFragment();
            case 2:
                return new HelpSupportPageFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }
}
