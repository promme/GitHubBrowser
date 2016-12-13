package se.berg.githubbrowser.profile.repositories;

import android.content.Context;
import android.databinding.BaseObservable;
import android.view.View;

import se.berg.githubbrowser.R;
import se.berg.githubbrowser.common.helper.DialogHelper;
import se.berg.githubbrowser.common.model.Repository;

/**
 * Created by olleberg on 2016-12-07.
 */

public class RepositoryViewModel extends BaseObservable {
    Context context;
    private Repository repository;

    public RepositoryViewModel(Context context, Repository repository) {
        this.repository = repository;
        this.context = context;

    }

    public void setRepository(Repository repository) {
        this.repository = repository;
        notifyChange();
    }

    public void openGithubPage(View view) {
        DialogHelper.getInstance().createOpenLinkDialog(context, repository.url).show();
    }

    public String getTitle() {
        return repository.name;
    }

    public String getLanguage() {
        return repository.language;
    }

    public String getDescription() {
        return repository.hasDescription() ? repository.description : context.getString(R.string.repository_description_error);
    }

    public String getLastUpdated() {
        return repository.updatedAt;
    }

}
