package com.example.mylandscapeapplication;

import android.content.res.Configuration;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    private NavigationState currentNavigationState = NavigationState.LIST;

    private final MutableLiveData<Boolean> isLandscapeMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<Boolean> getIsLandscapeMutableLiveData() {
        return isLandscapeMutableLiveData;
    }

    private final MutableLiveData<NavigationState> navigationStateMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<NavigationState> getNavigationStateMutableLiveData() {
        return navigationStateMutableLiveData;
    }

    private final MediatorLiveData<MainViewState> mainViewStateMediatorLiveData = new MediatorLiveData<>();
    public LiveData<MainViewState> getMainViewStateLiveData() {
        return mainViewStateMediatorLiveData;
    }

    public MainViewModel() {
        navigationStateMutableLiveData.setValue(NavigationState.HOME);
        configureMediator();

    }

    private void configureMediator(){
        mainViewStateMediatorLiveData.addSource(isLandscapeMutableLiveData, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                combine(aBoolean, navigationStateMutableLiveData.getValue());
            }
        });

        mainViewStateMediatorLiveData.addSource(navigationStateMutableLiveData, new Observer<NavigationState>() {
            @Override
            public void onChanged(NavigationState navigationState) {
                combine(isLandscapeMutableLiveData.getValue(), navigationState);
            }
        });
    }

    private NavigationState checkAndRedirectDestination(boolean isLandScape, NavigationState askedDestination) {
        switch (askedDestination){
            case HOME:
                return NavigationState.HOME.redirectNavigation(isLandScape, askedDestination);
            case LIST:
                return NavigationState.LIST.redirectNavigation(isLandScape, askedDestination);
            default:
                return askedDestination;
        }
    }

    private void combine(Boolean isLandscape, NavigationState askedDestination){

        if ((isLandscape == null) || (askedDestination == null))
            return;

        NavigationState redirectNavigation = checkAndRedirectDestination(isLandscape, askedDestination);

        MenuItemViewState home = new MenuItemViewState(NavigationState.HOME.isEnable(isLandscape, redirectNavigation),
                NavigationState.HOME.isVisible(isLandscape, redirectNavigation));
        MenuItemViewState detail = new MenuItemViewState(NavigationState.DETAIL.isEnable(isLandscape, redirectNavigation),
                NavigationState.DETAIL.isVisible(isLandscape, redirectNavigation));
        MenuItemViewState edit = new MenuItemViewState(NavigationState.EDIT.isEnable(isLandscape, redirectNavigation),
                NavigationState.EDIT.isVisible(isLandscape, redirectNavigation));
        MenuItemViewState add = new MenuItemViewState(NavigationState.ADD.isEnable(isLandscape, redirectNavigation),
                NavigationState.ADD.isVisible(isLandscape, redirectNavigation));
        MenuItemViewState map = new MenuItemViewState(NavigationState.MAP.isEnable(isLandscape, redirectNavigation),
                NavigationState.MAP.isVisible(isLandscape, redirectNavigation));
        MenuItemViewState search = new MenuItemViewState(NavigationState.SEARCH.isEnable(isLandscape, redirectNavigation),
                NavigationState.SEARCH.isVisible(isLandscape, redirectNavigation));

        // boolean mustNavigate = navigationChanged(navigationState, newNavigation);
        MainViewState mainViewState = new MainViewState(redirectNavigation, home, detail, edit, add, map, search);

        mainViewStateMediatorLiveData.setValue(mainViewState);
    }

}
