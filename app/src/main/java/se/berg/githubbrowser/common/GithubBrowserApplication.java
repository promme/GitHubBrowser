package se.berg.githubbrowser.common;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by olleberg on 2016-12-06.
 */

public class GithubBrowserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
