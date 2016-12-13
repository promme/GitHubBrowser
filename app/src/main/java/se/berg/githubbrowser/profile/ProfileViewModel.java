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
import se.berg.githubbrowser.helper.BindableFieldTarget;
import se.berg.githubbrowser.helper.DialogHelper;
import se.berg.githubbrowser.model.Repository;
import se.berg.githubbrowser.model.User;
import se.berg.githubbrowser.service.GithubService;

/**
 * Created by olleberg on 2016-12-06.
 */

public class ProfileViewModel {
    Context context;
    private User user;
    public ObservableField<String> usernameObservable;
    public ObservableField<String> realnameObservable;
    public ObservableField<String> bioObservable;
    public ObservableField<Drawable> imageObservable;
    private BindableFieldTarget bindableFieldTarget;
    private ProfileCallback listener;
    Subscription repositorySubscription, userSubscription;

    public ProfileViewModel(Context context, ProfileCallback listner) {
        this.context = context;
        this.listener = listner;
        usernameObservable = new ObservableField<>();
        bioObservable = new ObservableField<>();
        imageObservable = new ObservableField<>();
        realnameObservable = new ObservableField<>();
        bindableFieldTarget = new BindableFieldTarget(imageObservable, context.getResources());

    }

    private void fetchUser(String user) {
        GithubService service = GithubService.Factory.create();
        userSubscription = service.fetchUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(User user) {
                        updateObservables(user);
                    }
                });
        repositorySubscription = service.fetchPublicRepositories(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Repository>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(List<Repository> repositories) {
                        listener.onRepositoriesSet(repositories);
                    }
                });
    }

    public void updateObservables(User user) {
        this.user = user;
        usernameObservable.set(context.getString(R.string.profile_user_login, user.login));
        realnameObservable.set(user.name);
        bioObservable.set(user.hasBio() ? user.bio : context.getString(R.string.profile_user_bio_error));
        Glide.with(context).load(user.avatarUrl).into(bindableFieldTarget);
    }


    public void destroy() {
        context = null;
        if (repositorySubscription != null && !repositorySubscription.isUnsubscribed()) {
            repositorySubscription.unsubscribe();
        }
        if (userSubscription != null && !userSubscription.isUnsubscribed()) {
            userSubscription.unsubscribe();
        }
    }

    public void onActivityCreated() {
        fetchUser("promme");
    }

    public void openGithubProfile(View v) {
        DialogHelper.getInstance().createOpenLinkDialog(context, user.url).show();

    }

    public interface ProfileCallback {
        void onRepositoriesSet(List<Repository> repositories);
    }
}
