package com.example.vksbermvvm.viewModelTests;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.example.vksbermvvm.R;
import com.example.vksbermvvm.testUtils.SynchronousExecutor;
import com.example.vksbermvvm.domain.model.LoadGroupsExceprtion;
import com.example.vksbermvvm.domain.model.ProfileInfoInteractor;
import com.example.vksbermvvm.domain.model.model.Group;
import com.example.vksbermvvm.presentation.utils.IResourceWrapper;
import com.example.vksbermvvm.presentation.viewModels.GroupsListViewModel;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Юнит тесты вью модели {@link GroupsListViewModel}
 *
 * @author Цунский Роман on 2019-12-15
 */
public class GroupListViewModelTest {
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();
    private GroupsListViewModel mViewModel;
    private ProfileInfoInteractor mProfileInfoInteractor;
    private IResourceWrapper mResourceWrapper;
    private List<Group> mGroupList;


    @Before
    public void setUp() {
        mGroupList = new ArrayList<>(Arrays.asList(mock(Group.class), mock(Group.class)));
        mProfileInfoInteractor = mock(ProfileInfoInteractor.class);
        mResourceWrapper = mock(IResourceWrapper.class);
        mViewModel = new GroupsListViewModel(
                new SynchronousExecutor(),
                mProfileInfoInteractor,
                mResourceWrapper
        );
    }

    @Test
    public void testLoadGroupList_happyCase() throws LoadGroupsExceprtion {
        // arrange
        when(mProfileInfoInteractor.loadGroups(Mockito.anyString())).thenReturn(mGroupList);
        List<Group> expectedFriendList = new ArrayList<>(mGroupList);
        // act
        mViewModel.loadGroupList(Mockito.anyString());
        // assert
        assertThat(mViewModel.getGroupList().getValue(), is(expectedFriendList));
        assertThat(mViewModel.isLoading().getValue(), is(false));
    }

    @Test
    public void testLoadAlbumPhoto_interactorThrowsException() throws LoadGroupsExceprtion {
        // arrange
        when(mProfileInfoInteractor.loadGroups(Mockito.anyString())).thenThrow(new LoadGroupsExceprtion("message", new Throwable()));
        String errorLoadingGroups = "errorLoadingGroups";
        when(mResourceWrapper.getString(R.string.error_loading_groups)).thenReturn(errorLoadingGroups);

        // act
        mViewModel.loadGroupList(Mockito.anyString());

        // assert
        assertThat(mViewModel.getErrors().getValue(), is(errorLoadingGroups));
    }
}
