package org.eboard.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ScrollView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import org.eboard.app.R;
import org.eboard.app.core.User;
import org.eboard.app.util.Ln;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import io.socket.IOAcknowledge;
import io.socket.IOCallback;
import io.socket.SocketIO;
import io.socket.SocketIOException;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;

/**
 * Created by libiao on 14-5-19.
 */
public class ChatActivity extends BootstrapActivity {
    @InjectView(R.id.scrollView) protected ScrollView scrollView;
    public SocketIO socket;
    public static final String SOCKET_URL = "http://158.182.150.91:19999";

    public static final String AUTH = "authenticate";
    public static final String USER_LIST = "userlist";
    public static final String SYNC = "synchronize";
    public static final String BCAST = "cbcast";
    public static final String SHARED_OBJECT = "shared_object";
    public static final String SYSTEM_MESSAGE = "system_message";

    private final Bundle users_args = new Bundle();
    private final UserListFragment frag = new UserListFragment();

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chat_activity);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        Ln.d("should once");
            JSONObject obj = new JSONObject();

            try {
                obj.put("userid",44);
                obj.put("userrole",1);
                obj.put("username","shshi");
                obj.put("sessionid","b4d231ee8fee7b29151d302da729f4c3");
                obj.put("devicetype","mobile_web");
                obj.put("channel","744aba6171a69d752474ea95a61ddcc7");
                obj.put("classroom_id",1518);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {

                String query = obj.toString();
                socket = new SocketIO(SOCKET_URL);
                socket.setQueryString("userinfo=" + query);

                socket.connect(new IOCallback() {
                    @Override
                    public void onMessage(JSONObject json, IOAcknowledge ack) {
                        try {
                            Ln.d("Server said:" + json.toString(2));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onMessage(String data, IOAcknowledge ack) {
                        Ln.d("Server said: " + data);
                    }

                    @Override
                    public void onError(SocketIOException socketIOException) {
                        Ln.d("an Error occured");
                        socketIOException.printStackTrace();
                    }

                    @Override
                    public void onDisconnect() {
                        Ln.d("Connection terminated.");
                    }

                    @Override
                    public void onConnect() {
                        Ln.d("Connection established");
                    }

                    @Override
                    public void on(String event, IOAcknowledge ack, Object... args) {
                        if (AUTH.equals(event)) {
                            Ln.d("auth success");
                        }
                        else if(USER_LIST.equals(event)) {
                            Ln.d(args);
                                users_args.putString("user_list", args[0].toString());
                                frag.setArguments(users_args);
                                getFragmentManager()
                                        .beginTransaction()
                                        .add(R.id.user_list,frag)
                                        .commit();
                        }
                        else if(SYNC.equals(event)) {
                            Ln.d("sync" + args);
                        }
                        else if(BCAST.equals(event)) {
                            Ln.d("cast" + args);
                        }
                        else if(SHARED_OBJECT.equals(event)) {
                            Ln.d("shared object" + args);
                        }
                        else if(SYSTEM_MESSAGE.equals(event)){
                            Ln.d("sys msg" + args);
                        }
                    }
                });
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        switch (item.getItemId()) {
            // This is the home button in the top left corner of the screen.
            case android.R.id.home:
                // Don't call finish! Because activity could have been started by an
                // outside activity and the home button would not operated as expected!
                final Intent homeIntent = new Intent(this, MainActivity.class);
                homeIntent.addFlags(FLAG_ACTIVITY_CLEAR_TOP | FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(homeIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        if (socket.isConnected()){
            socket.disconnect();
            Ln.d("socket should be disconnect");
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Ln.d("it should be destroy");
    }

}
