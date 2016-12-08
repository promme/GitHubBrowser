package se.berg.githubbrowser.profile.repositories;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import se.berg.githubbrowser.R;
import se.berg.githubbrowser.model.Repository;

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
        new MaterialDialog.Builder(context)
                .content(R.string.repository_dialog_content)
                .title(R.string.repository_dialog_title)
                .positiveText(R.string.repository_dialog_positive_text)
                .negativeText(R.string.repository_dialog_negative_text)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(repository.url));
                        context.startActivity(browserIntent);
                    }
                }).show();
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
