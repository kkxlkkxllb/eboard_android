package org.eboard.app.ui;

import android.view.LayoutInflater;

import org.eboard.app.R;
import org.eboard.app.core.Word;

import java.util.List;

public class WordListAdapter extends AlternatingColorListAdapter<Word> {
    /**
     * @param inflater
     * @param items
     * @param selectable
     */
    public WordListAdapter(final LayoutInflater inflater, final List<Word> items,
                           final boolean selectable) {
        super(R.layout.word_list_item, inflater, items, selectable);
    }

    /**
     * @param inflater
     * @param items
     */
    public WordListAdapter(final LayoutInflater inflater, final List<Word> items) {
        super(R.layout.word_list_item, inflater, items);
    }

    @Override
    protected int[] getChildViewIds() {
        return new int[]{R.id.tv_title, R.id.tv_summary};
    }

    @Override
    protected void update(final int position, final Word item) {
        super.update(position, item);

        setText(0, item.getTitle());
        setText(1, item.getContent());
    }
}
