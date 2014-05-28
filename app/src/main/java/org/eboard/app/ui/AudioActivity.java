package org.eboard.app.ui;

import android.os.Bundle;
import org.eboard.app.rtmp.SayHi;
import android.view.View;
import android.widget.Button;

import org.eboard.app.R;
import org.eboard.app.util.Ln;

import butterknife.InjectView;

/**
 * Created by libiao on 14-5-22.
 */
public class AudioActivity extends BootstrapActivity implements View.OnClickListener{
    SayHi say;
    private String uri = "rtmp://158.182.151.42:1935/live/s_1529_634";

    @InjectView(R.id.start_record) protected Button start;
    @InjectView(R.id.stop_record) protected Button stop;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audio_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        say = new SayHi();
        say.Init();
        say.setOnEventCallback(new SayHi.OnEventCallback() {

            @Override
            public void onEvent(int event) {
                Ln.d(event);
                Ln.d("opened player");
                say.OpenPlayer(uri + " live=1");
            }
        });

        start.setOnClickListener(this);
        stop.setOnClickListener(this);
    }

    @Override
    public void onClick(final View v) {
        switch (v.getId()) {
            case R.id.start_record:
                startRecord();
                break;
            case R.id.stop_record:
                stopRecord();
                break;
        }
    }

    public void startRecord() {
        say.OpenPublisher(uri + " live=1");
        this.start.setVisibility(View.GONE);
        this.stop.setVisibility(View.VISIBLE);
    }

    public void stopRecord() {
        this.start.setVisibility(View.VISIBLE);
        this.stop.setVisibility(View.GONE);
        say.ClosePublisher();
        say.ClosePlayer();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
