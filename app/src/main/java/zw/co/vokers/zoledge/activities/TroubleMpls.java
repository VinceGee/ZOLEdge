package zw.co.vokers.zoledge.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.adapters.AbstractWheelTextAdapter;
import zw.co.vokers.zoledge.adapters.ArrayWheelAdapter;
import zw.co.vokers.zoledge.utils.OnWheelChangedListener;
import zw.co.vokers.zoledge.utils.OnWheelScrollListener;
import zw.co.vokers.zoledge.utils.WheelView;

public class TroubleMpls extends AppCompatActivity {
    private boolean scrolling = false;
    public int selectedLight;
    public String hostelRacho;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mpls_trouble);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final WheelView lights_mc = (WheelView) findViewById(R.id.lightsonmc);
        lights_mc.setVisibleItems(3);
        lights_mc.setViewAdapter(new HostelAdapter(this));

        final String solutions[][] = new String[][]{
                new String[]{"6 lights means the Fibre link is normal: If the link is normal, check the splunk for authentication logs (for PPPoe links), For NonPPPoE links check if you can ping client’s gateway "},
                new String[]{"4 lights means there is a fibre break (FDX and FX led OFF), log a fault with LTZ NOC via email (zimbabwenoc@liquidtelecom.com ) quoting the VLAN ID, Physical address and the contact details. VLAN ID details are usually on the notes section of the account in prism. If they are not there, enquire with NOC for NonPPPoe links and from the galaxy servers for pppoe links"},
                new String[]{"3 lights means the UTP cable is not properly connected between the Trendnet media convertor and the terminating device eg Router or Server. Make sure the cable is properly connected."},
                new String[]{"1 light means patch cable is not properly connected"},
        };

        final WheelView light_solution = (WheelView) findViewById(R.id.lights_solution);
        light_solution.setVisibleItems(1);

        lights_mc.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                if (!scrolling) {
                    updateHostels(light_solution, solutions, newValue);
                }
            }
        });

        lights_mc.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {
                scrolling = true;
            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                scrolling = false;
                updateHostels(light_solution, solutions, lights_mc.getCurrentItem());
            }
        });

        lights_mc.setCurrentItem(1);

        selectedLight = lights_mc.getCurrentItem();

    }

    /**
     * Updates the lights_on_mc wheel
     */
    private void updateHostels(WheelView h_name, String mahostels[][], int index) {
        ArrayWheelAdapter<String> adapter = new ArrayWheelAdapter<String>(this, mahostels[index]);
        adapter.setTextSize(18);
        h_name.setViewAdapter(adapter);
        h_name.setCurrentItem(mahostels[index].length / 2);
    }

    /**
     * Adapter for location
     */
    private class HostelAdapter extends AbstractWheelTextAdapter {
        // hostel names
        private String lights_on_mc[] =
                new String[] {"6 Lights on MC", "4 Lights on MC","3 Lights on MC", "1 Light on MC"};

        /**
         * Constructor
         */
        protected HostelAdapter(Context context) {
            super(context, R.layout.hostel_main_item, NO_RESOURCE);

            setItemTextResource(R.id.hostel_location);
        }


        @Override
        public int getItemsCount() {
            return lights_on_mc.length;
        }

        @Override
        protected CharSequence getItemText(int index) {
            return lights_on_mc[index];
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