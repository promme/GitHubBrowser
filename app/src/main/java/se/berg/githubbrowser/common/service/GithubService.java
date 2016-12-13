package se.berg.githubbrowser.common.service;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import se.berg.githubbrowser.common.model.Repository;
import se.berg.githubbrowser.common.model.User;

/**
 * Created by olleberg on 2016-12-06.
 */

public interface GithubService {
    @GET("users/{username}/repos")
    Observable<List<Repository>> fetchPublicRepositories(@Path("username") String username);

    @GET("users/{username}")
    Observable<User> fetchUser(@Path("username") String username);


    class Factory {
        public static GithubService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            return retrofit.create(GithubService.class);
        }
    }
}
