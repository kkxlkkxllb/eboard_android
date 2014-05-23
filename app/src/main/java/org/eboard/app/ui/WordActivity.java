package org.eboard.app.ui;

import android.os.Bundle;
import android.widget.TextView;

import org.eboard.app.R;
import org.eboard.app.core.Word;

import butterknife.InjectView;

import static org.eboard.app.core.Constants.Extra.WORD_ITEM;

public class WordActivity extends BootstrapActivity {
    private Word wordItem;

    @InjectView(R.id.tv_title) protected TextView title;
    @InjectView(R.id.tv_content) protected TextView content;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.word);

        if (getIntent() != null && getIntent().getExtras() != null) {
            wordItem = (Word) getIntent().getExtras().getSerializable(WORD_ITEM);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        setTitle(wordItem.getTitle());

        title.setText(wordItem.getTitle());
        content.setText(wordItem.getContent());

    }
}
