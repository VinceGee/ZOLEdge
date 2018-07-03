package zw.co.vokers.zoledge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.fragments.AirspanFragment;
import zw.co.vokers.zoledge.fragments.AppleMailFragment;
import zw.co.vokers.zoledge.fragments.BaseStationsFragment;
import zw.co.vokers.zoledge.fragments.EcowebI600Fragment;
import zw.co.vokers.zoledge.fragments.EmailsGeneralFragment;
import zw.co.vokers.zoledge.fragments.GeneralWimaxFrag;
import zw.co.vokers.zoledge.fragments.GreenPacketIduFrag;
import zw.co.vokers.zoledge.fragments.GreenPacketOduFrag;
import zw.co.vokers.zoledge.fragments.MifiFragment;
import zw.co.vokers.zoledge.fragments.OutlookFragment;
import zw.co.vokers.zoledge.fragments.Tu25Fragment;
import zw.co.vokers.zoledge.fragments.Wimax16DFragment;
import zw.co.vokers.zoledge.fragments.ZolEmailFragment;
import zw.co.vokers.zoledge.fragments.ZolHostedDomainEmailFrag;
import zw.co.vokers.zoledge.fragments.ZolWebmailFragment;
import zw.co.vokers.zoledge.utils.HorizontalScrollMenuView;
import zw.co.vokers.zoledge.utils.MenuItem;

public class TroubleWimax extends AppCompatActivity {

    private ViewPager viewPager;
    HorizontalScrollMenuView horizontal_menu;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wimax_trouble);

        Toolbar toolbar = findViewById(R.id.tro_wimax_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.wimax_horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.wimax_pager);

        horizontal_menu.addItem("General", R.drawable.data, true);
        horizontal_menu.addItem("Airspan", R.drawable.data);
        horizontal_menu.addItem("Greenpacket IDU", R.drawable.data);
        horizontal_menu.addItem("Greenpacket ODU", R.drawable.data);
        horizontal_menu.addItem("Mi-Fi", R.drawable.data);
        horizontal_menu.addItem("Ecoweb I600", R.drawable.data);
        horizontal_menu.addItem("WiMax 16D", R.drawable.data);
        horizontal_menu.addItem("TU25 Dongle", R.drawable.data);
        horizontal_menu.addItem("BaseStations", R.drawable.data);

        horizontal_menu.showItems();

        TroubleWimax.PagerAdapter pagerAdapter = new TroubleWimax.PagerAdapter(getSupportFragmentManager());
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
                    return new GeneralWimaxFrag();
                case 1:
                    return new AirspanFragment();
                case 2:
                    return new GreenPacketIduFrag();
                case 3:
                    return new GreenPacketOduFrag();
                case 4:
                    return new MifiFragment();
                case 5:
                    return new EcowebI600Fragment();
                case 6:
                    return new Wimax16DFragment();
                case 7:
                    return new Tu25Fragment();
                case 8:
                    return new BaseStationsFragment();
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
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_demo1);

        //View rootView = inflate(R.layout.fragment_demo1, container, false);

        ((TextView) findViewById(R.id.sample1).findViewById(R.id.eptitle1)).setText("Deluxe Plan");
        ((TextView) findViewById(R.id.sample2).findViewById(R.id.eptitle2)).setText("Prime Plan");

        ExpandableTextView expTv1 = (ExpandableTextView) findViewById(R.id.sample1)
                .findViewById(R.id.expand_text_view);
        ExpandableTextView expTv2 = (ExpandableTextView) findViewById(R.id.sample2)
                .findViewById(R.id.expand_text_view);

        *//*expTv1.setOnExpandStateChangeListener(new ExpandableTextView.OnExpandStateChangeListener() {
            @Override
            public void onExpandStateChanged(TextView textView, boolean isExpanded) {
                //Toast.makeText(getApplicationContext(), isExpanded ? "Expanded" : "Collapsed", Toast.LENGTH_SHORT).show();
            }
        });*//*

        expTv1.setText(getString(R.string.prime_txt));
        expTv2.setText(getString(R.string.prime_txt));
    }*/
}
