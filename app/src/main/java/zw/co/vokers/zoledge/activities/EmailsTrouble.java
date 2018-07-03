package zw.co.vokers.zoledge.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.fragments.AppleMailFragment;
import zw.co.vokers.zoledge.fragments.EmailsGeneralFragment;
import zw.co.vokers.zoledge.fragments.OutlookFragment;
import zw.co.vokers.zoledge.fragments.PonFragment;
import zw.co.vokers.zoledge.fragments.RedLosFragment;
import zw.co.vokers.zoledge.fragments.ReprovisionFragment;
import zw.co.vokers.zoledge.fragments.VoipFragment;
import zw.co.vokers.zoledge.fragments.ZolEmailFragment;
import zw.co.vokers.zoledge.fragments.ZolHostedDomainEmailFrag;
import zw.co.vokers.zoledge.fragments.ZolWebmailFragment;
import zw.co.vokers.zoledge.utils.ExpandableTextView;
import zw.co.vokers.zoledge.utils.HorizontalScrollMenuView;
import zw.co.vokers.zoledge.utils.MenuItem;

public class EmailsTrouble extends AppCompatActivity {

    private ViewPager viewPager;
    HorizontalScrollMenuView horizontal_menu;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emails_trouble);

        Toolbar toolbar = findViewById(R.id.tro_email_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.email_horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.email_pager);

        horizontal_menu.addItem("General", R.drawable.data, true);
        horizontal_menu.addItem("ZOL Domain\nEmails", R.drawable.data);
        horizontal_menu.addItem("ZOL-Hosted\nDomain Emails", R.drawable.data);
        horizontal_menu.addItem("ZOL Webmail", R.drawable.data);
        horizontal_menu.addItem("Outlook Settings", R.drawable.data);
        //horizontal_menu.addItem("Apple Mail", R.drawable.data);
        //horizontal_menu.addItem("Missed Calls", R.drawable.data);
        //horizontal_menu.addItem("Missed Calls", R.drawable.missed_call,2);

        horizontal_menu.showItems();

        EmailsTrouble.PagerAdapter pagerAdapter = new EmailsTrouble.PagerAdapter(getSupportFragmentManager());
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
                    return new EmailsGeneralFragment();
                case 1:
                    return new ZolEmailFragment();
                case 2:
                    return new ZolHostedDomainEmailFrag();
                case 3:
                    return new ZolWebmailFragment();
                case 4:
                    return new OutlookFragment();
                /*case 5:
                    return new AppleMailFragment();*/
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
