package se.berg.githubbrowser.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import se.berg.githubbrowser.R;
import se.berg.githubbrowser.databinding.ActivityMainBinding;
import se.berg.githubbrowser.profile.ProfileFragment;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new MainViewModel();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        replaceContent(ProfileFragment.newInstance());
        binding.setViewModel(viewModel);

    }

    public void replaceContent(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

    public void updateProgressVisibility(int visibility) {
        viewModel.updateVisibility(visibility);
    }
}
