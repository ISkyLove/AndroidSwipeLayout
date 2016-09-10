package com.daimajia.swipedemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.daimajia.swipe.SimpleSwipeListener;
import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;
import com.daimajia.swipe.implments.SwipeItemMangerImpl;
import com.daimajia.swipe.util.Attributes;
import com.daimajia.swipedemo.DataBean;
import com.daimajia.swipedemo.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.SimpleViewHolder> implements SwipeLayout.ShouldIntercepParentTouchEventListen {


    private SimpleViewHolder mOpenViewHolder;


    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        SwipeLayout swipeLayout;
        TextView textViewPos;
        TextView textViewData;
        TextView buttonDelete;

        public SimpleViewHolder(View itemView) {
            super(itemView);
            swipeLayout = (SwipeLayout) itemView.findViewById(R.id.swipe);
            textViewPos = (TextView) itemView.findViewById(R.id.position);
            textViewData = (TextView) itemView.findViewById(R.id.text_data);
            buttonDelete = (TextView) itemView.findViewById(R.id.trash);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d(getClass().getSimpleName(), "onItemSelected: " + textViewData.getText().toString());
                    Toast.makeText(view.getContext(), "onItemSelected: " + textViewData.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        public void closeSwipe() {
            swipeLayout.close();
        }

        public SwipeLayout.Status getSwipeState() {
            return swipeLayout.getOpenStatus();
        }
    }

    private Context mContext;
    private ArrayList<DataBean> mDataset;

    //protected SwipeItemRecyclerMangerImpl mItemManger = new SwipeItemRecyclerMangerImpl(this);

    public RecyclerViewAdapter(Context context, ArrayList<DataBean> objects) {
        this.mContext = context;
        this.mDataset = objects;
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new SimpleViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final SimpleViewHolder viewHolder, final int position) {
        final DataBean item = mDataset.get(position);
        viewHolder.swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        viewHolder.swipeLayout.setClickToClose(true);
        viewHolder.swipeLayout.addSwipeListener(new SimpleSwipeListener() {
            @Override
            public void onOpen(SwipeLayout layout) {
                YoYo.with(Techniques.Tada).duration(500).delay(100).playOn(layout.findViewById(R.id.trash));
                item.setOpen(true);

            }

            @Override
            public void onStartOpen(SwipeLayout layout) {
                mOpenViewHolder = viewHolder;
            }

            @Override
            public void onClose(SwipeLayout layout) {
                if (mOpenViewHolder == viewHolder) {
                    mOpenViewHolder = null;
                }
            }
        });
        viewHolder.swipeLayout.setOnDoubleClickListener(new SwipeLayout.DoubleClickListener() {
            @Override
            public void onDoubleClick(SwipeLayout layout, boolean surface) {
                Toast.makeText(mContext, "DoubleClick", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "buttonDelete", Toast.LENGTH_SHORT).show();
//                mItemManger.removeShownLayouts(viewHolder.swipeLayout);
//                mDataset.remove(position);
//                notifyItemRemoved(position);
//                notifyItemRangeChanged(position, mDataset.size());
//                mItemManger.closeAllItems();
//                Toast.makeText(view.getContext(), "Deleted " + viewHolder.textViewData.getText().toString() + "!", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.swipeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mOpenViewHolder != null){
                    return;
                }
                Toast.makeText(mContext, "swipeLayout", Toast.LENGTH_SHORT).show();
            }
        });
        viewHolder.textViewPos.setText((position + 1) + ".");
        viewHolder.textViewData.setText(item.getName());
        viewHolder.swipeLayout.setShouldIntercepParentTouchEventListen(this);
        //       mItemManger.bind(viewHolder.itemView, position);
    }

    @Override
    public boolean shouldIntercepParentTouchEvent(MotionEvent e) {
        if(mOpenViewHolder != null){
            mOpenViewHolder.closeSwipe();
           // return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


}
