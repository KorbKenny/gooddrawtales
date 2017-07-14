package com.draw.tales.tutorial;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.draw.tales.R;
import com.draw.tales.TwoPathPageActivity;
import com.draw.tales.classes.Constants;

/**
 * Created by KorbBookProReturns on 6/30/17.
 */

public class TutThree extends Fragment {
    private CardView mBeginButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tut_three,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        simpleSetup(view);

        mBeginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), TwoPathPageActivity.class);
                intent.putExtra(Constants.TYPE_INTENT,Constants.GLOBAL);
                intent.putExtra(Constants.STORY_INTENT,Constants.FIRST_STORY);
                intent.putExtra(Constants.PAGE_ID_INTENT,Constants.FIRST_PAGE_ID);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void simpleSetup(View view) {
        mBeginButton = (CardView) view.findViewById(R.id.tut_three_lets_begin_card);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), Constants.FONT);
        TextView tv = (TextView)view.findViewById(R.id.tut_three_lets_begin_text);
        tv.setTypeface(tf);

    }
}
