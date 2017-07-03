package com.draw.tales.sketchbook;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.draw.tales.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by KorbBookProReturns on 6/30/17.
 */

public class SketchesRecyclerAdapter extends RecyclerView.Adapter<SketchesViewholder> {
    private List<String> mSketchList;
    private List<String> mKeyList;
    private SketchClickedListener mListener;
    private SketchLongClickedListener mLongListener;

    public SketchesRecyclerAdapter(List<String> sketchList, List<String> keyList, SketchClickedListener listener, SketchLongClickedListener longListener) {
        mSketchList = sketchList;
        mKeyList = keyList;
        mListener = listener;
        mLongListener = longListener;
    }

    @Override
    public SketchesViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SketchesViewholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_sketch,parent,false));
    }

    @Override
    public void onBindViewHolder(SketchesViewholder holder, int position) {
        Picasso.with(holder.mSketchView.getContext()).load(mSketchList.get(position)).placeholder(R.drawable.loadingsquareimage).into(holder.mSketchView);
        holder.bind(mSketchList.get(position),mKeyList.get(position),mListener,mLongListener);
    }

    @Override
    public int getItemCount() {
        return mSketchList.size();
    }

    public interface SketchClickedListener{
        void onSketchClicked(String sketchPath);
    }

    public interface SketchLongClickedListener{
        void onSketchLongClicked(String sketchpath, String key);
    }
}
