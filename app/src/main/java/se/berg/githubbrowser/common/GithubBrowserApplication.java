package se.berg.githubbrowser.common;

import android.app.Application;

import timber.log.Timber;

public class GithubBrowserApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());
    }
}
