package com.example.savenews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostsAdapter.FragmentButtonListener,
PostsAdapter.FragmentLikeListener, SavesListAdapter.FragmentLikeListener{

    private LockableViewPager pager;
    private PagerAdapter pagerAdapter;
    Fragment f1 = new PostsList();
    Fragment f2 = new SavesList();

    List<Fragment> list = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        list.add(f1);
        list.add(f2);
        pager = findViewById(R.id.pager);
        pager.setSwipable(false);
        pagerAdapter = new SlidePagerAdapter(getSupportFragmentManager(), list);
        pager.setAdapter(pagerAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.home:
                                pager.setCurrentItem(0,false);
                                item.setIcon(R.drawable.ic_home);
                                bottomNavigationView.getMenu().findItem(R.id.save).setIcon(R.drawable.ic_save);
                                bottomNavigationView.getMenu().findItem(R.id.search).setIcon(R.drawable.search);
                                bottomNavigationView.getMenu().findItem(R.id.add).setIcon(R.drawable.add);
                                bottomNavigationView.getMenu().findItem(R.id.account).setIcon(R.drawable.man);
                                break;
                            case R.id.save:
                                pager.setCurrentItem(1,false);
                                item.setIcon(R.drawable.ic_favorite);
                                bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.main);
                                bottomNavigationView.getMenu().findItem(R.id.search).setIcon(R.drawable.search);
                                bottomNavigationView.getMenu().findItem(R.id.add).setIcon(R.drawable.add);
                                bottomNavigationView.getMenu().findItem(R.id.account).setIcon(R.drawable.man);
                                break;
                            case R.id.search:
                                item.setIcon(R.drawable.search_active);
                                bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.main);
                                bottomNavigationView.getMenu().findItem(R.id.save).setIcon(R.drawable.ic_save);
                                bottomNavigationView.getMenu().findItem(R.id.add).setIcon(R.drawable.add);
                                bottomNavigationView.getMenu().findItem(R.id.account).setIcon(R.drawable.man);
                                break;
                            case R.id.add:
                                item.setIcon(R.drawable.add_active);
                                bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.main);
                                bottomNavigationView.getMenu().findItem(R.id.save).setIcon(R.drawable.ic_save);
                                bottomNavigationView.getMenu().findItem(R.id.search).setIcon(R.drawable.search);
                                bottomNavigationView.getMenu().findItem(R.id.account).setIcon(R.drawable.man);
                                break;
                            case R.id.account:
                                item.setIcon(R.drawable.man_active);
                                bottomNavigationView.getMenu().findItem(R.id.home).setIcon(R.drawable.main);
                                bottomNavigationView.getMenu().findItem(R.id.save).setIcon(R.drawable.ic_save);
                                bottomNavigationView.getMenu().findItem(R.id.search).setIcon(R.drawable.search);
                                bottomNavigationView.getMenu().findItem(R.id.add).setIcon(R.drawable.add);
                                break;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void myClick(Posts posts, int option) {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.pager);
        if (option==1)
            ((SavesList)fragment).saveNews(posts);
        else
            ((SavesList)fragment).removeNews(posts);
    }

    @Override
    public void removeItemLike(Posts posts) {
        ((PostsList)f1).removeLike(posts);
        ((SavesList)f2).removeLike(posts);
    }
}
