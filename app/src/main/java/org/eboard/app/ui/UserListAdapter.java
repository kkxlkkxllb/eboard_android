package org.eboard.app.ui;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.eboard.app.BootstrapApplication;
import org.eboard.app.R;
import org.eboard.app.core.User;
import org.eboard.app.util.Ln;

import java.util.List;

/**
 * Created by libiao on 14-5-22.
 */
public class UserListAdapter extends ArrayAdapter {

    private LayoutInflater inflater;
    private int layoutResourceId;
    private Context context;
    private List users;

    public UserListAdapter(Context ctx,int layoutResourceId, List objects) {
        super( ctx, layoutResourceId, objects );
        this.layoutResourceId = layoutResourceId;
        inflater = LayoutInflater.from( ctx );
        this.context = ctx;
        this.users = objects;
    }

    public static class ViewHolder {
        public ImageView imgView;
    }

    @Override
    public View getView ( int position, View convertView, ViewGroup parent ) {

        ViewHolder holder = null;
        if (convertView == null){
            convertView = inflater.inflate(layoutResourceId,parent, false );
            holder = new ViewHolder();
            holder.imgView = (ImageView) convertView.findViewById(R.id.iv_avatar);
            User user = new Gson().fromJson(users.get(position).toString(), User.class);

            String uri = user.getImgUrl();
            Ln.d(uri);
            if (uri != null){
                Picasso.with(BootstrapApplication.getInstance())
                    .load(uri)
                    .placeholder(R.drawable.gravatar_icon)
                    .into(holder.imgView);
            }

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
