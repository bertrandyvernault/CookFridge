package com.example.bertrandyvernault.foodfridge.controller;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.example.bertrandyvernault.foodfridge.R;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView mMainNav;
    private FrameLayout mMainFrame;

    private FridgeFragment mFridgeFragment;
    private AddFragment mAddFragment;
    private AccountFragment mAccountFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMainFrame =(FrameLayout) findViewById(R.id.main_frame);
        mMainNav = (BottomNavigationView) findViewById(R.id.main_nav);

        mFridgeFragment = new FridgeFragment();
        mAddFragment = new AddFragment();
        mAccountFragment = new AccountFragment();

        setFragment(mFridgeFragment);

        mMainNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_fridge :
                        setFragment(mFridgeFragment);
                        return true;

                    case R.id.nav_add :
                        setFragment(mAddFragment);
                        return true;

                    case R.id.nav_account :
                        setFragment(mAccountFragment);
                        return true;

                     default:
                         return false;
                }
            }

        });
    }

    private void setFragment(Fragment fragment) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.main_frame, fragment);
        fragmentTransaction.commit();


    }

    private void save(){
        Toast.makeText(this,R.string.action_save,Toast.LENGTH_LONG).show();
    }

    private void delete(){
        Toast.makeText(this,R.string.action_delete,Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_bar, menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        final MenuItem searchItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView)MenuItemCompat.getActionView(searchItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //votre code ici
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                save();
                return true;
            case R.id.action_delete:
                delete();
                return true;
            case R.id.action_settings:
                /* DO DELETE */
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}


