package com.draw.tales.tutorial;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.draw.tales.R;
import com.draw.tales.TwoPathPageActivity;
import com.draw.tales.classes.Constants;
import com.draw.tales.main.MainActivity;

public class TutorialActivity extends AppCompatActivity {
    private ViewPager vp;
    private CardView mStartButton;
    private TextView mStartText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        vp = (ViewPager)findViewById(R.id.tutorial_viewpager);

        TutorialPagerAdapter tpa = new TutorialPagerAdapter(getSupportFragmentManager());
        vp.setAdapter(tpa);
        vp.setCurrentItem(0);
        vp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return true;
            }
        });


        mStartButton = (CardView)findViewById(R.id.tut_one_button);
        mStartText = (TextView)findViewById(R.id.tut_one_button_next_text);
        Typeface tf = Typeface.createFromAsset(getAssets(), Constants.FONT);
        mStartText.setTypeface(tf);

        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(vp.getCurrentItem()==0){
                    vp.setCurrentItem(1);
                    mStartText.setText("Got it, let's start!");
                } else if(vp.getCurrentItem()==1){
                    Intent intent = new Intent(TutorialActivity.this, TwoPathPageActivity.class);
                    intent.putExtra(Constants.PAGE_ID_INTENT, Constants.FIRST_PAGE_ID);
                    intent.putExtra(Constants.TYPE_INTENT, Constants.GLOBAL);
                    intent.putExtra(Constants.STORY_INTENT, Constants.FIRST_STORY);
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_from_bottom, R.anim.out_toward_top);
                    finish();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        if(vp.getCurrentItem()==0){
            Intent intent = new Intent(TutorialActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        } else if(vp.getCurrentItem()==1){
            vp.setCurrentItem(0);
            mStartText.setText("Rad");
        }
    }
}
