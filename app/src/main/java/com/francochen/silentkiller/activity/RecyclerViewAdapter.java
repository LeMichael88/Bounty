package com.francochen.silentkiller.activity;


import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
        import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.francochen.silentkiller.R;

import org.w3c.dom.Text;

        import java.lang.reflect.Array;
        import java.util.ArrayList;


/**
 * Created by User on 1/1/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private ArrayList<String> mName;
    private ArrayList<String> mTime;
    private Context mContext;

    public RecyclerViewAdapter(Context context, ArrayList<String> name, ArrayList<String> time) {
        mName = name;
        mTime = time;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_captured, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.time.setText(mTime.get(position));
        holder.name.setText(mName.get(position));
    }

    @Override
    public int getItemCount() {
        return mName.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView name;
        TextView time;

        public ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageView);
            name = itemView.findViewById(R.id.playerName);
            time = itemView.findViewById(R.id.time);
        }
    }
}


