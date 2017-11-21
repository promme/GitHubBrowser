package se.berg.githubbrowser.profile;

import android.content.Context;
import android.databinding.ObservableField;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.bumptech.glide.Glide;

import java.util.List;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import se.berg.githubbrowser.R;
import se.berg.githubbrowser.main.MainActivity;
import se.berg.githubbrowser.common.helper.BindableFieldTarget;
import se.berg.githubbrowser.common.helper.DialogHelper;
import se.berg.githubbrowser.common.model.Repository;
import se.berg.githubbrowser.common.model.User;
import se.berg.githubbrowser.common.service.GithubService;

public class ProfileViewModel {
    public ObservableField<String> usernameObservable;
    public ObservableField<String> realnameObservable;
    public ObservableField<String> bioObservable;
    public ObservableField<Drawable> imageObservable;
    public ObservableField<Integer> githubLinkVisibilityObservable;

    private Context context;
    private User user;
    private BindableFieldTarget bindableFieldTarget;
    private ProfileCallback listener;
    private Subscription repositorySubscription, userSubscription;
    private RepositoryObserver repositoryObserver;
    private UserObserver userObserver;

    public ProfileViewModel(Context context, ProfileCallback listener) {
        this.context = context;
        this.listener = listener;
        usernameObservable = new ObservableField<>();
        bioObservable = new ObservableField<>();
        imageObservable = new ObservableField<>();
        realnameObservable = new ObservableField<>();
        bindableFieldTarget = new BindableFieldTarget(imageObservable, context.getResources());
        githubLinkVisibilityObservable = new ObservableField<>();
        githubLinkVisibilityObservable.set(View.GONE);

    }

    private void fetchUser(String user) {
        if (context instanceof MainActivity) {
            ((MainActivity) context).updateProgressVisibility(View.VISIBLE);
        }
        GithubService service = GithubService.Factory.create();
        userObserver = new UserObserver();
        userSubscription = service.fetchUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userObserver);

        repositoryObserver = new RepositoryObserver();
        repositorySubscription = service.fetchPublicRepositories(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(repositoryObserver);
    }

    public void updateObservables(User user) {
        this.user = user;
        usernameObservable.set(context.getString(R.string.profile_user_login, user.login));
        realnameObservable.set(user.name);
        bioObservable.set(user.hasBio() ? user.bio : context.getString(R.string.profile_user_bio_error));
        githubLinkVisibilityObservable.set(View.VISIBLE);
        Glide.with(context).load(user.avatarUrl).into(bindableFieldTarget);
    }

    private void hideSpinner() {
        if (repositoryObserver.isComplete && userObserver.isComplete) {
            if (context instanceof MainActivity) {
                ((MainActivity) context).updateProgressVisibility(View.GONE);
            }
        }
    }

    void destroy() {
        context = null;
        if (repositorySubscription != null && !repositorySubscription.isUnsubscribed()) {
            repositorySubscription.unsubscribe();
        }
        if (userSubscription != null && !userSubscription.isUnsubscribed()) {
            userSubscription.unsubscribe();
        }
    }

    void onActivityCreated() {
        fetchUser("promme");
    }

    public void openGithubProfile(View v) {
        DialogHelper.getInstance().createOpenLinkDialog(context, user.url).show();

    }

    public interface ProfileCallback {
        void onRepositoriesSet(List<Repository> repositories);
    }


    private class RepositoryObserver implements Observer<List<Repository>> {
        boolean isComplete;

        @Override
        public void onCompleted() {
            isComplete = true;
            hideSpinner();
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(List<Repository> repositories) {
            if (listener != null) {
                listener.onRepositoriesSet(repositories);
            }
        }
    }

    private class UserObserver implements Observer<User> {
        boolean isComplete;

        @Override
        public void onCompleted() {
            isComplete = true;
            hideSpinner();
        }

        @Override
        public void onError(Throwable e) {
        }

        @Override
        public void onNext(User user) {
            updateObservables(user);
        }
    }
}
