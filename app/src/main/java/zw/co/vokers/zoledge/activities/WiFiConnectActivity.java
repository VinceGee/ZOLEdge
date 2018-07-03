package zw.co.vokers.zoledge.activities;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import zw.co.vokers.zoledge.R;

public class WiFiConnectActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvWifiName;
    private TextView tvWifiType;
    private Button btnConnectWifi;
    private String strSSID;
    private int wifiType;
    private String strPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connect_to_wifi_activity);
        Bundle extras = getIntent().getExtras();
        strSSID = extras.getString("strSSID");
        wifiType = extras.getInt("strType");
        strPassword = extras.getString("strPassword");
        bindView();
    }

    /*bind all view conroller*/
    private void bindView() {
        tvWifiName = (TextView) findViewById(R.id.tv_wifi_name);
        tvWifiType = (TextView) findViewById(R.id.tv_wifi_type);
        //check wifi type and print according to it.
        tvWifiName.setText(strSSID);
        if (wifiType == 1)
            tvWifiType.setText("OPEN");
        if (wifiType == 2)
            tvWifiType.setText("WPA");
        if (wifiType == 3)
            tvWifiType.setText("WEP");
        btnConnectWifi = (Button) findViewById(R.id.btn_connect);
        btnConnectWifi.setOnClickListener(this);

    }

    /*onclick of connect button*/
    @Override
    public void onClick(View v) {
        connectToWifi(strSSID, strPassword, wifiType);
    }

    // function for connect wifi using ssid,password, and type
    public void connectToWifi(String ssid, String Password, int TYPE) {
        final String networkSSID = ssid;
        String networkPass = Password;

        WifiConfiguration conf = new WifiConfiguration();
        conf.SSID = "\"" + networkSSID + "\"";

        if (TYPE == 3) {
            conf.wepKeys[0] = networkPass;
            conf.wepTxKeyIndex = 0;
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
            conf.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.WEP40);
        } else if (TYPE == 2) {
            conf.preSharedKey = "\"" + networkPass + "\"";
        } else {
            conf.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.NONE);
        }

        final WifiManager wifiManager = (WifiManager) getSystemService(Context.WIFI_SERVICE);
        //if wifi is disable
        if (!wifiManager.isWifiEnabled()) {
            //first enable wifi
            wifiManager.setWifiEnabled(true);
            wifiManager.addNetwork(conf);

            Toast.makeText(getApplicationContext(), "Connecting to " + networkSSID, Toast.LENGTH_SHORT).show();
            //wait for 3 second to enable wifi
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
                    for (WifiConfiguration i : list) {
                        if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                            wifiManager.disconnect();
                            wifiManager.enableNetwork(i.networkId, true);
                            wifiManager.reconnect();
                            break;
                        }
                    }
                }
            }, 3000);
        }
        //else if wifi is enable
        else {
            //add wifi config to list and connect to wifi
            wifiManager.addNetwork(conf);
            List<WifiConfiguration> list = wifiManager.getConfiguredNetworks();
            for (WifiConfiguration i : list) {
                if (i.SSID != null && i.SSID.equals("\"" + networkSSID + "\"")) {
                    wifiManager.disconnect();
                    wifiManager.enableNetwork(i.networkId, true);
                    wifiManager.reconnect();
                    Toast.makeText(getApplicationContext(), "Connecting to " + networkSSID, Toast.LENGTH_SHORT).show();
                    break;
                }
            }
        }

    }
}

