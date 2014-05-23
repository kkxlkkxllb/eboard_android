package org.eboard.app.ui;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.eboard.app.R;
import org.eboard.app.core.User;

import java.util.List;

/**
 * Created by libiao on 14-5-22.
 */
public class UserListFragment extends ListFragment {
    public static final Gson GSON = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.user_list_fragment, container,
                false);
        String list = getArguments().getString("user_list");
        final List<User> users = GSON.fromJson(list, List.class);

        UserListAdapter adapter = new UserListAdapter(getActivity(),R.layout.user_list_item, users);
        setListAdapter(adapter);
        return rootView;
    }
}
