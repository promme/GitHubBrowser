package se.berg.githubbrowser.profile.repositories;

import android.support.v7.widget.RecyclerView;

import se.berg.githubbrowser.databinding.RecycleritemRepositoryBinding;
import se.berg.githubbrowser.model.Repository;

public class RepositoryViewHolder extends RecyclerView.ViewHolder {
    final RecycleritemRepositoryBinding binding;

    public RepositoryViewHolder(RecycleritemRepositoryBinding binding) {
        super(binding.repositoryRoot);
        this.binding = binding;
    }

    void bindRepository(Repository repository) {
        if (binding.getViewModel() == null) {
            binding.setViewModel(new RepositoryViewModel(itemView.getContext(), repository));
        } else {
            binding.getViewModel().setRepository(repository);
        }
    }
}