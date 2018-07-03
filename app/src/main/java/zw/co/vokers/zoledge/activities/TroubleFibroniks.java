package zw.co.vokers.zoledge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.fragments.PonFragment;
import zw.co.vokers.zoledge.fragments.RedLosFragment;
import zw.co.vokers.zoledge.fragments.ReprovisionFragment;
import zw.co.vokers.zoledge.fragments.VoipFragment;
import zw.co.vokers.zoledge.utils.HorizontalScrollMenuView;
import zw.co.vokers.zoledge.utils.MenuItem;

/**
 * Created by VinceGee on 1/18/2018.
 */

public class TroubleFibroniks extends AppCompatActivity {

    private ViewPager viewPager;
    HorizontalScrollMenuView horizontal_menu;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trouble_main);

        Toolbar toolbar = findViewById(R.id.tro_fibro_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.pager);

        horizontal_menu.addItem("Reprovision", R.drawable.data, true);
        horizontal_menu.addItem("Red LOS", R.drawable.data);
        horizontal_menu.addItem("VoIP", R.drawable.data);
        horizontal_menu.addItem("PON Flashing", R.drawable.data);
        //horizontal_menu.addItem("Call Back", R.drawable.data);
        //horizontal_menu.addItem("Missed Calls", R.drawable.data);
        //horizontal_menu.addItem("Missed Calls", R.drawable.missed_call,2);

        horizontal_menu.showItems();

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                horizontal_menu.setItemSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        horizontal_menu.setOnHSMenuClickListener(new HorizontalScrollMenuView.OnHSMenuClickListener() {
            @Override
            public void onHSMClick(MenuItem menuItem, int position) {
                //Toast.makeText(TroubleFibroniks.this, "item " + position, Toast.LENGTH_SHORT).show();

                viewPager.setCurrentItem(position);
            }
        });

    }

    private class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ReprovisionFragment();
                case 1:
                    return new RedLosFragment();
                case 2:
                    return new VoipFragment();
                case 3:
                    return new PonFragment();
                default:
                    break;
            }
            return null;
        }

        @Override
        public int getCount() {
            return horizontal_menu.numItems();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}