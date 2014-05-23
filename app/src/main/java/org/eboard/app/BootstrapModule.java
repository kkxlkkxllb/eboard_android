package org.eboard.app;

import android.accounts.AccountManager;
import android.content.Context;

import org.eboard.app.authenticator.BootstrapAuthenticatorActivity;
import org.eboard.app.authenticator.LogoutService;
import org.eboard.app.core.TimerService;
import org.eboard.app.ui.AudioActivity;
import org.eboard.app.ui.BootstrapTimerActivity;
import org.eboard.app.ui.MainActivity;
import org.eboard.app.ui.MovieActivity;
import org.eboard.app.ui.MoviesListFragment;
import org.eboard.app.ui.NavigationDrawerFragment;
import org.eboard.app.ui.WordActivity;
import org.eboard.app.ui.WordListFragment;
import org.eboard.app.ui.ChatActivity;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module for setting up provides statements.
 * Register all of your entry points below.
 */
@Module(
        complete = false,

        injects = {
                BootstrapApplication.class,
                BootstrapAuthenticatorActivity.class,
                MainActivity.class,
                BootstrapTimerActivity.class,
                MovieActivity.class,
                MoviesListFragment.class,
                NavigationDrawerFragment.class,
                WordActivity.class,
                WordListFragment.class,
                TimerService.class,
                ChatActivity.class,
                AudioActivity.class
        }
)
public class BootstrapModule {

    @Singleton
    @Provides
    Bus provideOttoBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    LogoutService provideLogoutService(final Context context, final AccountManager accountManager) {
        return new LogoutService(context, accountManager);
    }

}
