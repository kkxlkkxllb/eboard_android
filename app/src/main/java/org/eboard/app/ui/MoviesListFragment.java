package org.eboard.app.ui;

import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.Loader;
import android.view.View;
import android.widget.ListView;

import org.eboard.app.BootstrapServiceProvider;
import org.eboard.app.Injector;
import org.eboard.app.R;
import org.eboard.app.authenticator.LogoutService;
import org.eboard.app.core.Movie;
import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static org.eboard.app.core.Constants.Extra.MOVIE_ITEM;

public class MoviesListFragment extends ItemListFragment<Movie> {

    @Inject protected BootstrapServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override
    protected void configureList(final Activity activity, final ListView listView) {
        super.configureList(activity, listView);

        listView.setFastScrollEnabled(true);
        listView.setDividerHeight(0);

        getListAdapter()
                .addHeader(activity.getLayoutInflater()
                        .inflate(R.layout.movies_list_item_labels, null));
    }

    @Override
    protected LogoutService getLogoutService() {
        return logoutService;
    }

    @Override
    public void onDestroyView() {
        setListAdapter(null);

        super.onDestroyView();
    }

    @Override
    public Loader<List<Movie>> onCreateLoader(final int id, final Bundle args) {
        final List<Movie> initialItems = items;
        return new ThrowableLoader<List<Movie>>(getActivity(), items) {

            @Override
            public List<Movie> loadData() throws Exception {
                try {
                    if (getActivity() != null) {
                        return serviceProvider.getService(getActivity()).getMovies();
                    } else {
                        return Collections.emptyList();
                    }
                } catch (final OperationCanceledException e) {
                    final Activity activity = getActivity();
                    if (activity != null)
                        activity.finish();
                    return initialItems;
                }
            }
        };
    }

    @Override
    protected SingleTypeAdapter<Movie> createAdapter(final List<Movie> items) {
        return new MoviesListAdapter(getActivity().getLayoutInflater(), items);
    }

    public void onListItemClick(final ListView l, final View v, final int position, final long id) {
        Movie movie = ((Movie) l.getItemAtPosition(position));

        startActivity(new Intent(getActivity(), MovieActivity.class).putExtra(MOVIE_ITEM, movie));
    }

    @Override
    protected int getErrorMessage(final Exception exception) {
        return R.string.error_loading_movies;
    }
}
