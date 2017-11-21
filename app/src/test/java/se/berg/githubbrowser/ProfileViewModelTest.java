package se.berg.githubbrowser;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import se.berg.githubbrowser.common.GithubBrowserApplication;
import se.berg.githubbrowser.common.model.User;
import se.berg.githubbrowser.profile.ProfileViewModel;
import se.berg.githubbrowser.util.MockUtil;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 23)
public class ProfileViewModelTest {
    GithubBrowserApplication application;
    ProfileViewModel.ProfileCallback mockListener;
    ProfileViewModel mockViewModel;

    @Before
    public void setUp() {
        application = (GithubBrowserApplication) RuntimeEnvironment.application;
        mockListener = mock(ProfileViewModel.ProfileCallback.class);
        mockViewModel = new ProfileViewModel(application, mockListener);
    }

    @Test
    public void verifyObservableText() {
        String userName = "promme";
        User user = MockUtil.mockUserWithoutBio(userName);
        mockViewModel.updateObservables(user);
        assertEquals(mockViewModel.bioObservable.get(), application.getString(R.string.profile_user_bio_error));
        assertEquals(mockViewModel.realnameObservable.get(), userName);
        assertEquals(mockViewModel.usernameObservable.get(), application.getString(R.string.profile_user_login, userName));

    }
}
