package se.berg.githubbrowser.util;

import se.berg.githubbrowser.common.model.Repository;
import se.berg.githubbrowser.common.model.User;

public class MockUtil {

    public static User mockUserWithoutBio(String username) {
        User user = new User();
        user.login = username;
        user.name = username;
        user.bio = "";
        user.avatarUrl = "www.example.se/picture.jpg";
        user.url = "www.example.se/picture.jpg";
        user.publicRepos = 10;
        return user;
    }

    public static Repository mockRepository() {
        Repository repository = new Repository();
        repository.description = "Some text";
        repository.name = "Super project";
        repository.language = "java";
        repository.updatedAt = "2016-04-06T14:22:18Z";
        repository.url = "www.github.com";
        return repository;
    }
}
