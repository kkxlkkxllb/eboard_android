package org.eboard.app.ui;

import android.os.Bundle;
import org.eboard.app.rtmp.manager.ClientManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.rtp.AudioCodec;
import android.net.rtp.AudioGroup;
import android.net.rtp.AudioStream;
import android.net.rtp.RtpStream;
import android.os.StrictMode;

import org.eboard.app.R;
import org.eboard.app.util.Ln;

/**
 * Created by libiao on 14-5-22.
 */
public class AudioActivity extends BootstrapActivity {
    public static final int STOPPED = 0;
    public static final int RECORDING = 1;
    ClientManager clientManager = new ClientManager();
    int status = STOPPED;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.audio_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        clientManager.setMode(ClientManager.NETANDFILE);
        clientManager.setRunning(true);
//        clientManager.setRecording(true);
        Thread cmThread = new Thread(clientManager);
        cmThread.start();
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}
