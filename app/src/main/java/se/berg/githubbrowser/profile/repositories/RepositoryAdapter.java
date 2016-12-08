package se.berg.githubbrowser.profile.repositories;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import se.berg.githubbrowser.R;
import se.berg.githubbrowser.databinding.RecycleritemRepositoryBinding;
import se.berg.githubbrowser.model.Repository;

/**
 * Created by olleberg on 2016-12-07.
 */

public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryViewHolder> {
    private List<Repository> repositories;

    public RepositoryAdapter(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public RepositoryAdapter() {
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }

    @Override
    public RepositoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecycleritemRepositoryBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.recycleritem_repository,
                parent,
                false);
        return new RepositoryViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(RepositoryViewHolder holder, int position) {
        holder.bindRepository(repositories.get(holder.getAdapterPosition()));
    }


    @Override
    public int getItemCount() {
        if (repositories == null)
            return 0;
        return repositories.size();
    }


}
