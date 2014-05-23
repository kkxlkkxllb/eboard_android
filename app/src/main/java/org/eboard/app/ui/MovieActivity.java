package org.eboard.app.ui;

import android.os.Bundle;
import android.widget.TextView;

import org.eboard.app.R;
import org.eboard.app.core.Movie;

import butterknife.InjectView;

import static org.eboard.app.core.Constants.Extra.MOVIE_ITEM;

/**
 * Created by libiao on 14-5-14.
 */
public class MovieActivity extends BootstrapActivity {
    private Movie movieItem;

    @InjectView(R.id.tv_title) protected TextView title;
    @InjectView(R.id.tv_content) protected TextView content;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.news);

        if (getIntent() != null && getIntent().getExtras() != null) {
            movieItem = (Movie) getIntent().getExtras().getSerializable(MOVIE_ITEM);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(movieItem.getTitle());

        title.setText(movieItem.getTitle());
        content.setText(movieItem.getYear());

    }
}
