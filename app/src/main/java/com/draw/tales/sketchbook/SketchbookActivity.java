package com.draw.tales.sketchbook;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.draw.tales.R;
import com.draw.tales.classes.Constants;
import com.draw.tales.classes.InfoDialogs;
import com.draw.tales.classes.SquareImageView;
import com.draw.tales.main.MainActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SketchbookActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private SketchesRecyclerAdapter mAdapter;
    private List<String> mSketchList, mSketchKeys;
    private RelativeLayout mDetailLayout;
    private SquareImageView mDetailSketch;
    private String iMyUserId;
    private TextView mLoading;
    private boolean refreshed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sketchbook);

        simpleSetup();

        mDetailLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mDetailLayout.getVisibility()==View.VISIBLE){
                    mDetailLayout.setVisibility(View.GONE);
                    mDetailLayout.setClickable(false);
                }
            }
        });
    }

    private void simpleSetup() {
        iMyUserId = getIntent().getStringExtra(Constants.USER_INTENT);
        refreshed = getIntent().getBooleanExtra(Constants.REFRESHING_SKETCHES_INTENT,false);

        mRecyclerView = (RecyclerView) findViewById(R.id.sketchbook_recycler);
        mDetailLayout = (RelativeLayout) findViewById(R.id.sketchbook_detail_layout);
        mDetailSketch = (SquareImageView) findViewById(R.id.sketchbook_detail_sketch);
        mLoading = (TextView) findViewById(R.id.sketchbook_loading);

        Typeface tf = Typeface.createFromAsset(getAssets(),Constants.FONT);
        mLoading.setTypeface(tf);

        mSketchList = new ArrayList<>();
        mSketchKeys = new ArrayList<>();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference sketchesRef = db.getReference(Constants.USERS_REF).child(iMyUserId).child(Constants.SKETCHBOOK);

        getSketchesFromDb(sketchesRef);

    }

    private void getSketchesFromDb(DatabaseReference sketchesRef) {
        sketchesRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                new AsyncTask<Void,Void,Void>(){
                    @Override
                    protected Void doInBackground(Void... voids) {
                        if(dataSnapshot!=null){
                            for (DataSnapshot ds:dataSnapshot.getChildren()) {
                                String sketch = ds.getValue(String.class);
                                mSketchList.add(sketch);
                                String key = ds.getKey();
                                mSketchKeys.add(key);
                            }
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void aVoid) {
                        mLoading.setVisibility(View.GONE);
                        setSketches();
                    }
                }.execute();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void setSketches(){
        GridLayoutManager glm = new GridLayoutManager(SketchbookActivity.this,3);
        mAdapter = new SketchesRecyclerAdapter(mSketchList, mSketchKeys, new SketchesRecyclerAdapter.SketchClickedListener() {
            @Override
            public void onSketchClicked(String sketchPath) {
                mDetailLayout.setVisibility(View.VISIBLE);
                mDetailLayout.setClickable(true);
                Picasso.with(SketchbookActivity.this).load(sketchPath).placeholder(R.drawable.loadingsquareimage).into(mDetailSketch);
            }
        }, new SketchesRecyclerAdapter.SketchLongClickedListener() {
            @Override
            public void onSketchLongClicked(String sketchpath, String key) {
                InfoDialogs.deleteSketchDialog(SketchbookActivity.this,key,iMyUserId).show();
            }
        });
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setAdapter(mAdapter);
    };

    @Override
    public void onBackPressed() {
        if(refreshed){
            Intent intent = new Intent(SketchbookActivity.this, MainActivity.class);
            intent.putExtra(Constants.USER,true);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}
