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
import org.eboard.app.core.Word;
import org.eboard.app.util.Ln;

import com.github.kevinsawicki.wishlist.SingleTypeAdapter;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import static org.eboard.app.core.Constants.Extra.WORD_ITEM;

public class WordListFragment extends ItemListFragment<Word> {

    @Inject protected BootstrapServiceProvider serviceProvider;
    @Inject protected LogoutService logoutService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.inject(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setEmptyText(R.string.no_words);
    }

    @Override
    protected void configureList(Activity activity, ListView listView) {
        super.configureList(activity, listView);

        listView.setFastScrollEnabled(true);
        listView.setDividerHeight(0);

        getListAdapter()
                .addHeader(activity.getLayoutInflater()
                        .inflate(R.layout.words_list_item_labels, null));
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
    public Loader<List<Word>> onCreateLoader(int id, Bundle args) {
        final List<Word> initialItems = items;
        return new ThrowableLoader<List<Word>>(getActivity(), items) {

            @Override
            public List<Word> loadData() throws Exception {
                try {
                    if (getActivity() != null) {
                        return serviceProvider.getService(getActivity()).getWords();
                    } else {
                        return Collections.emptyList();
                    }

                } catch (OperationCanceledException e) {
                    Activity activity = getActivity();
                    if (activity != null)
                        activity.finish();
                    return initialItems;
                }
            }
        };
    }

    @Override
    protected SingleTypeAdapter<Word> createAdapter(List<Word> items) {
        return new WordListAdapter(getActivity().getLayoutInflater(), items);
    }

    public void onListItemClick(ListView l, View v, int position, long id) {
        Word word = ((Word) l.getItemAtPosition(position));

        startActivity(new Intent(getActivity(), WordActivity.class).putExtra(WORD_ITEM, word));
    }

    @Override
    protected int getErrorMessage(Exception exception) {
        Ln.d(exception);
        return R.string.error_loading_words;
    }
}
