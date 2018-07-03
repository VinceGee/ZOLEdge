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
import zw.co.vokers.zoledge.fragments.AccountQuotasFrag;
import zw.co.vokers.zoledge.fragments.AirspanFragment;
import zw.co.vokers.zoledge.fragments.BaseStationsFragment;
import zw.co.vokers.zoledge.fragments.DnsRequirementsFrag;
import zw.co.vokers.zoledge.fragments.DnsUsersFrag;
import zw.co.vokers.zoledge.fragments.EcowebI600Fragment;
import zw.co.vokers.zoledge.fragments.GeneralDns;
import zw.co.vokers.zoledge.fragments.GeneralWimaxFrag;
import zw.co.vokers.zoledge.fragments.GreenPacketIduFrag;
import zw.co.vokers.zoledge.fragments.GreenPacketOduFrag;
import zw.co.vokers.zoledge.fragments.MifiFragment;
import zw.co.vokers.zoledge.fragments.Tu25Fragment;
import zw.co.vokers.zoledge.fragments.Wimax16DFragment;
import zw.co.vokers.zoledge.utils.HorizontalScrollMenuView;
import zw.co.vokers.zoledge.utils.MenuItem;

public class TroubleDNSActivity extends AppCompatActivity {

    private ViewPager viewPager;
    HorizontalScrollMenuView horizontal_menu;
    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dns_trouble);

        Toolbar toolbar = findViewById(R.id.tro_dns_toolbar);
        //setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        horizontal_menu = (HorizontalScrollMenuView) findViewById(R.id.dns_horizontal_menu);
        viewPager = (ViewPager) findViewById(R.id.dns_pager);

        horizontal_menu.addItem("General", R.drawable.data, true);
        horizontal_menu.addItem("Requirements", R.drawable.data);
        horizontal_menu.addItem("Users", R.drawable.data);
        horizontal_menu.addItem("Account Quotas", R.drawable.data);

        horizontal_menu.showItems();

        TroubleDNSActivity.PagerAdapter pagerAdapter = new TroubleDNSActivity.PagerAdapter(getSupportFragmentManager());
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
                    return new GeneralDns();
                case 1:
                    return new DnsRequirementsFrag();
                case 2:
                    return new DnsUsersFrag();
                case 3:
                    return new AccountQuotasFrag();

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
