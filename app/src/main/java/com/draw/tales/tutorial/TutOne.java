package com.draw.tales.tutorial;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.draw.tales.R;
import com.draw.tales.classes.Constants;

/**
 * Created by KorbBookProReturns on 6/30/17.
 */

public class TutOne extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tut_one,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleSetup(view);


    }

    private void simpleSetup(View view) {
        TextView welcome = (TextView)view.findViewById(R.id.tut_one_welcome_to);
        TextView descript = (TextView)view.findViewById(R.id.tut_one_description);
        TextView bigText = (TextView)view.findViewById(R.id.tut_one_big_text);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), Constants.FONT);
        welcome.setTypeface(tf);
        descript.setTypeface(tf);
        bigText.setTypeface(tf);
    }
}
