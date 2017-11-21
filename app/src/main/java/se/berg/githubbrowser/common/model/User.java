package se.berg.githubbrowser.common.model;

import com.google.gson.annotations.SerializedName;

public class User {
    public String login;
    @SerializedName("html_url")
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
