package se.berg.githubbrowser.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by olleberg on 2016-12-06.
 */

public class User {
    public String login;
    public String url;
    public String name;
    public String bio;
    @SerializedName("public_repos")
    public int publicRepos;
    @SerializedName("avatar_url")
    public String avatarUrl;

    public boolean hasBio() {
        return bio != null && !bio.isEmpty();
    }
}