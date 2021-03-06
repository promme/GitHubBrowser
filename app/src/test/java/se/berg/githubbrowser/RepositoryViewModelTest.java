package se.berg.githubbrowser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import se.berg.githubbrowser.common.GithubBrowserApplication;
import se.berg.githubbrowser.common.model.Repository;
import se.berg.githubbrowser.profile.repositories.RepositoryViewModel;
import se.berg.githubbrowser.util.MockUtil;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class RepositoryViewModelTest {
    GithubBrowserApplication application;
    RepositoryViewModel viewModel;
    Repository repository;

    @Before
    public void setUp() {
        application = (GithubBrowserApplication) RuntimeEnvironment.application;
        repository = MockUtil.mockRepository();
        viewModel = new RepositoryViewModel(application, repository);
    }

    @Test
    public void verifyObservableTextChanged() {
        assertEquals(viewModel.getDescription(), repository.description);
        repository.description = "";
        viewModel.setRepository(repository);
        assertEquals(viewModel.getDescription(), application.getString(R.string.repository_description_error));
    }
}
