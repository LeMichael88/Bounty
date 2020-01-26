package com.francochen.silentkiller;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class LobbyImageAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater layoutInflater;

    public Integer[] images = {
            R.drawable.player_avatar, R.drawable.player_avatar, R.drawable.player_avatar,
            R.drawable.player_avatar, R.drawable.player_avatar, R.drawable.player_avatar
    };

    public LobbyImageAdapter(Context context) {
        context = this.context;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View grid, ViewGroup viewGroup) {
        ImageView imageView;
        if (grid == null) {
            grid = layoutInflater.inflate(R.layout.lobby_player, null);
            imageView = (ImageView) grid.findViewById(R.id.player_avatar);

            grid.setTag(imageView);

        } else {
            imageView = (ImageView) grid.getTag();
        }


        imageView.setImageResource(images[i]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        return grid;
    }
}
