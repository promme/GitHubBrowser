package se.berg.githubbrowser.main;

import android.databinding.ObservableField;
import android.view.View;

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
