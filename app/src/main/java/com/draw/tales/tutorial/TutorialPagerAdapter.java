package com.draw.tales.tutorial;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by KorbBookProReturns on 6/30/17.
 */

public class TutorialPagerAdapter extends FragmentPagerAdapter {
    public TutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch(position){
            case 0:
                return new TutOne();
            case 1:
                return new TutTwo();
            case 2:
                return new TutThree();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
