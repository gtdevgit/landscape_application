package com.example.mylandscapeapplication;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.mylandscapeapplication.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    MenuItem menuItemHome;
    MenuItem menuItemDetail;
    MenuItem menuItemEdit;
    MenuItem menuItemAdd;
    MenuItem menuItemMap;
    MenuItem menuItemSearch;

    MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Tag.TAG, "MainActivity.onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        menuItemHome = menu.findItem(R.id.menu_item_home);
        menuItemDetail = menu.findItem(R.id.menu_item_detail);
        menuItemEdit = menu.findItem(R.id.menu_item_edit);
        menuItemAdd = menu.findItem(R.id.menu_item_add);
        menuItemMap = menu.findItem(R.id.menu_item_map);
        menuItemSearch = menu.findItem(R.id.menu_item_search);

        configureViewModel();
        return true;
    }

    private void configureViewModel(){
        mainViewModel = new ViewModelProvider(this, MainViewModelFactory.getInstance())
                .get(MainViewModel.class);
        mainViewModel.getMainViewStateLiveData().observe(this, new Observer<MainViewState>() {
            @Override
            public void onChanged(MainViewState mainViewState) {
                setMainViewState(mainViewState);
            }
        });

        // When device rotate activity was recreated.
        // inform the ViewModel orientation changed.
        mainViewModel.getIsLandscapeMutableLiveData().setValue(LandscapeHelper.isLandscape());
    }

    /**
     * when press device back button
     */
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.d(Tag.TAG, "MainActivity.onBackPressed() called");
    }

    private void setMainViewState(MainViewState mainViewState) {
        navigateTo(mainViewState.getNavigationState());
        setMenuItemHome(mainViewState.getHome());
        setMenuItemDetail(mainViewState.getDetail());
        setMenuItemEdit(mainViewState.getEdit());
        setMenuItemAdd(mainViewState.getAdd());
        setMenuItemMap(mainViewState.getMap());
        setMenuItemSearch(mainViewState.getSearch());
    }

    private void setMenuItemState(MenuItem menuItem, MenuItemViewState menuItemViewState){
        menuItem.setEnabled(menuItemViewState.isEnabled());
        menuItem.setVisible(menuItemViewState.isVisible());
    }

    private void setMenuItemHome(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemHome, menuItemViewState);
    }

    private void setMenuItemDetail(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemDetail, menuItemViewState);
    }

    private void setMenuItemEdit(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemEdit, menuItemViewState);
    }

    private void setMenuItemAdd(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemAdd, menuItemViewState);
    }

    private void setMenuItemMap(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemMap, menuItemViewState);
    }

    private void setMenuItemSearch(MenuItemViewState menuItemViewState) {
        setMenuItemState(menuItemSearch, menuItemViewState);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_item_home:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.HOME);
                return true;
            case R.id.menu_item_detail:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.DETAIL);
                return true;
            case R.id.menu_item_edit:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.EDIT);
                return true;
            case R.id.menu_item_add:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.ADD);
                return true;
            case R.id.menu_item_map:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.MAP);
                return true;
            case R.id.menu_item_search:
                mainViewModel.getNavigationStateMutableLiveData().setValue(NavigationState.SEARCH);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void navigateTo(NavigationState destination){
        Log.d(Tag.TAG, "navigateTo() called with: destination = [" + destination + "]");
        switch (destination){
            case HOME:
                navigateToHome();
                return;
            case LIST:
                navigateToList();
                return;
            case DETAIL:
                navigateToDetail();
                return;
            case EDIT:
                navigateToEdit();
                return;
            case ADD:
                navigateToAdd();
                return;
            case MAP:
                navigateToMap();
                return;
            case SEARCH:
                navigateToSearch();
                return;
        }
    }

    private void navigateToHome(){
        if (LandscapeHelper.isLandscape())
            navigateToDetail();
        else
            navigateToList();
    }

    private void navigateToList(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.listFragment);
    }

    private void navigateToDetail(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.detailFragment);
    }

    private void navigateToEdit(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.editFragment);
    }

    private void navigateToAdd(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.addFragment);
    }

    private void navigateToMap(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.mapFragment);
    }

    private void navigateToSearch(){
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        navController.navigate(R.id.searchFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}