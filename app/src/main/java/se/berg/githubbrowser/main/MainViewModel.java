package se.berg.githubbrowser.main;

import android.databinding.ObservableField;
import android.view.View;

/**
 * Created by olleberg on 2016-12-13.
 */

public class MainViewModel {
    public ObservableField<Integer> progressVisibility;

    public MainViewModel() {
        progressVisibility = new ObservableField<>();
        progressVisibility.set(View.GONE);
    }

    public void updateVisibility(int visibility) {
        progressVisibility.set(visibility);
    }
}
