package org.eboard.app.ui;

import android.view.LayoutInflater;

import org.eboard.app.R;
import org.eboard.app.core.Movie;

import java.util.List;


public class MoviesListAdapter extends AlternatingColorListAdapter<Movie> {
    /**
     * @param inflater
     * @param items
     * @param selectable
     */
    public MoviesListAdapter(final LayoutInflater inflater, final List<Movie> items,
                             final boolean selectable) {
        super(R.layout.movie_list_item, inflater, items, selectable);
    }

    /**
     * @param inflater
     * @param items
     */
    public MoviesListAdapter(final LayoutInflater inflater, final List<Movie> items) {
        super(R.layout.movie_list_item, inflater, items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_type};
    }

    @Override
    protected void update(final int position, final Movie item) {
        super.update(position, item);

        setText(0, item.getTitle());
        setText(1, item.getType());
    }
}
