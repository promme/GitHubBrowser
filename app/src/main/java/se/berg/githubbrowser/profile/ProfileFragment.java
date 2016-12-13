package se.berg.githubbrowser.profile;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import se.berg.githubbrowser.R;
import se.berg.githubbrowser.databinding.FragmentProfileBinding;
import se.berg.githubbrowser.common.model.Repository;
import se.berg.githubbrowser.profile.repositories.RepositoryAdapter;


public class ProfileFragment extends Fragment implements ProfileViewModel.ProfileCallback {
    FragmentProfileBinding binding;
    ProfileViewModel viewBinding;
    RepositoryAdapter adapter;

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false);
        viewBinding = new ProfileViewModel(getContext(), this);

        binding.setViewModel(viewBinding);
        adapter = new RepositoryAdapter();
        binding.profileRepositoryList.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.profileRepositoryList.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewBinding.onActivityCreated();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        viewBinding.destroy();
    }

    @Override
    public void onRepositoriesSet(List<Repository> repositories) {
        adapter.setRepositories(repositories);
    }
}
