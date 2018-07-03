package zw.co.vokers.zoledge.activities;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.adapters.WifiAdapter;
import zw.co.vokers.zoledge.models.WifiModel;

public class QRGenerateActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String SECURITY_WEP = "WEP";
    private static final String SECURITY_WPA = "WPA";
    private static final String SECURITY_NOPASS = "OPEN";
    private static String TAG = "QRGenerateActivity";
    private static String capabilities;
    private Button btn_gen;
    private EditText et_data;
    private Spinner spnrWifiSSid;
    public static String TYPE;
    private ImageView iv_refersh;
    private ArrayList<WifiModel> availableWifiSsid;
    private WifiAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.qr_generate_activity);

        btn_gen = (Button) findViewById(R.id.btn_gen);
        btn_gen.setOnClickListener(this);
        et_data = (EditText) findViewById(R.id.et_data);
        iv_refersh = (ImageView) findViewById(R.id.iv_refersh);
        iv_refersh.setOnClickListener(this);
        spnrWifiSSid = (Spinner) findViewById(R.id.spnr_select_wifi_ssid);


    }

    @Override
    protected void onResume() {
        super.onResume();
        //getting all nearby wifi list
        availableWifiSsid = getAvailableWifiSsids(this);
        adapter = new WifiAdapter(this, R.layout.select_wifi_spinner_item, availableWifiSsid);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //set wifi list to spinner
        spnrWifiSSid.setAdapter(adapter);
        if (availableWifiSsid.size() > 1) {
            spnrWifiSSid.setSelection(1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            // onclick event of generate qr code button
            case R.id.btn_gen:
                if (!TextUtils.isEmpty(et_data.getText().toString()) && et_data.getText().toString().length() >= 6) {
                    //send all information of wifi to QROptionActivity
                    Intent intent = new Intent(QRGenerateActivity.this, QROptionActivity.class);
                    intent.putExtra("strSSID", ((WifiModel) spnrWifiSSid.getSelectedItem()).getSsid());
                    intent.putExtra("strType", ((WifiModel) spnrWifiSSid.getSelectedItem()).getType());
                    intent.putExtra("strPassword", et_data.getText().toString());
                    startActivity(intent);
                } else
                    et_data.setError("Please enter valid password");
                break;

            case R.id.iv_refersh:
                onResume();
                break;

        }
    }

    /*getting all nearby wifi list with type*/
    public static ArrayList<WifiModel> getAvailableWifiSsids(Context context) {
        ArrayList<WifiModel> wifiModelArrayList = new ArrayList<>();

        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            List<ScanResult> scanResult = wifiManager.getScanResults();
            Log.d(TAG, "NetworkUtils: getAvailableWifiSsids: scanResult.size->" + scanResult.size());
            for (ScanResult curScanResultObj : scanResult) {
                try {
                    capabilities = curScanResultObj.capabilities;
                    if (capabilities.contains("WEP")) {
                        TYPE = SECURITY_WEP;
                    } else if (capabilities.contains("WPA")) {
                        TYPE = SECURITY_WPA;
                    } else
                        TYPE = SECURITY_NOPASS;
                    String wifiSsid = curScanResultObj.SSID;

                    if (TextUtils.isEmpty(wifiSsid)) {
                        continue;
                    }

                    if (wifiSsid.charAt(0) == '"') {
                        wifiSsid = wifiSsid.substring(1);
                    }

                    if (wifiSsid.charAt(wifiSsid.length() - 1) == '"') {
                        wifiSsid = wifiSsid.substring(0, wifiSsid.length() - 1);
                    }
                    if (!TextUtils.isEmpty(wifiSsid) && !wifiModelArrayList.contains(wifiSsid)) {
                        wifiModelArrayList.add(new WifiModel(wifiSsid, TYPE));

                    }
                } catch (Exception e) {
                    Log.e(TAG, "NetworkUtils: getAvailableWifiSsids: Exception while removing \"->" + e.getMessage());
                }
            }
        } catch (Exception e) {
            Log.e(TAG, "NetworkUtils: getAvailableWifiSsids: Exception->" + e.getMessage());
        }
        Log.d(TAG, "NetworkUtils: getAvailableWifiSsids: size->" + wifiModelArrayList.size());
        return wifiModelArrayList;
    }

    /**
     * This method is used to get the currently connected Wifi SSID
     *
     * @return
     */
    public static String getCurrentlyConnectedWifiSsid(Context context) {
        String ssid = null;
        try {
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            ssid = wifiInfo.getSSID().toString();
            if (ssid.charAt(0) == '"') {
                ssid = ssid.substring(1);
            }

            if (ssid.charAt(ssid.length() - 1) == '"') {
                ssid = ssid.substring(0, ssid.length() - 1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ssid;
    }


}

