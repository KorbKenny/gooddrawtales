package com.draw.tales.sketchbook;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.draw.tales.R;
import com.draw.tales.classes.SquareImageView;

/**
 * Created by KorbBookProReturns on 6/30/17.
 */

public class SketchesViewholder extends RecyclerView.ViewHolder {
    SquareImageView mSketchView;

    public SketchesViewholder(View itemView) {
        super(itemView);

        mSketchView = (SquareImageView) itemView.findViewById(R.id.vh_sketch_image);
    }

    public void bind(final String sketchPath, final String key, final SketchesRecyclerAdapter.SketchClickedListener listener, final SketchesRecyclerAdapter.SketchLongClickedListener longListener){
        mSketchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onSketchClicked(sketchPath);
            }
        });

        mSketchView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                longListener.onSketchLongClicked(sketchPath,key);
                return true;
            }
        });
    }
}
