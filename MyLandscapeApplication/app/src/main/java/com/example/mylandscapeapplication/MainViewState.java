package com.example.mylandscapeapplication;

public class MainViewState {
    private NavigationState navigationState;
    private MenuItemViewState home;
    private MenuItemViewState detail;
    private MenuItemViewState edit;
    private MenuItemViewState add;
    private MenuItemViewState map;
    private MenuItemViewState search;

    public MainViewState(NavigationState navigationState, MenuItemViewState home, MenuItemViewState detail, MenuItemViewState edit, MenuItemViewState add, MenuItemViewState map, MenuItemViewState search) {
        this.navigationState = navigationState;
        this.home = home;
        this.detail = detail;
        this.edit = edit;
        this.add = add;
        this.map = map;
        this.search = search;
    }

    public NavigationState getNavigationState() {
        return navigationState;
    }

     public MenuItemViewState getHome() {
        return home;
    }

    public MenuItemViewState getDetail() {
        return detail;
    }

    public MenuItemViewState getEdit() {
        return edit;
    }

    public MenuItemViewState getAdd() {
        return add;
    }

    public MenuItemViewState getMap() {
        return map;
    }

    public MenuItemViewState getSearch() {
        return search;
    }
}
