package se.berg.githubbrowser.common.model;

import com.google.gson.annotations.SerializedName;

public class Repository {
    @SerializedName("html_url")
    public String url;
    @SerializedName("updated_at")
    public String updatedAt;
    public String description;
    public String name;
    public String language;

    public boolean hasDescription() {
        return description != null && !description.isEmpty();
    }
}
