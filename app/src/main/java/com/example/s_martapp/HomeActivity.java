package com.example.s_martapp;


import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    MenuItem prevMenuItem;

    HomeFragment homeFragment;
    ChatFragment chatFragment;
    SellFragment sellFragment;
    FavFragment favFragment;
    ProfileFragment profileFragment;

    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(4);


        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.navigation_chats:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.navigation_sell:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.navigation_fav:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                }
                else
                {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        //Disable ViewPager Swipe
        viewPager.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {

                return false;
            }
        });


        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager)
    {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        homeFragment=new HomeFragment();
        chatFragment=new ChatFragment();
        sellFragment=new SellFragment();
        favFragment=new FavFragment();
        profileFragment=new ProfileFragment();

        adapter.addFragment(homeFragment);
        adapter.addFragment(chatFragment);
        adapter.addFragment(sellFragment);
        adapter.addFragment(favFragment);
        adapter.addFragment(profileFragment);

        viewPager.setAdapter(adapter);
    }

}