package zw.co.vokers.zoledge.activities;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.utils.BarcodeCaptureActivity;

public class WifiScanner extends AppCompatActivity implements View.OnClickListener {
    private static final int RC_BARCODE_CAPTURE = 512;
    private ImageView ivScan; /*scan QR button*/
    private ImageView ivGenerate;/*Generate QR code button*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifiscanner);
        bindView();
    }

    /*bind all view controller*/
    private void bindView() {
        ivGenerate = (ImageView) findViewById(R.id.iv_gen_qr);
        ivGenerate.setOnClickListener(this);
        ivScan = (ImageView) findViewById(R.id.iv_scan_qr);
        ivScan.setOnClickListener(this);
        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE); //added getApplicationContext().
        wifiManager.setWifiEnabled(true);
    }

    /*handle all onclick event*/
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*click event for generate qr code */
            case R.id.iv_gen_qr:
                Intent qrintent = new Intent(this, QRGenerateActivity.class);
                startActivity(qrintent);
                break;
            /*click for scan qr code and  connect wifi*/
            case R.id.iv_scan_qr:
                Intent scanIntent = new Intent(this, BarcodeCaptureActivity.class);
                scanIntent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                scanIntent.putExtra(BarcodeCaptureActivity.UseFlash, false);
                startActivityForResult(scanIntent, RC_BARCODE_CAPTURE);
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /*callback from QR code scanner activity*/
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS_CACHE) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    //getting wifi model from QR code
                    Barcode.WiFi wifi = barcode.wifi;

                    //getting SSID,password,and TYPE from wifi model
                    String ssid = wifi.ssid;
                    String password = wifi.password;
                    int type = wifi.encryptionType;
                    /*call WiFiConnectActivity with parameters SSID,password,TYPE */
                    Intent intent = new Intent(WifiScanner.this, WiFiConnectActivity.class);
                    intent.putExtra("strSSID", ssid);
                    intent.putExtra("strType", type);
                    intent.putExtra("strPassword", password);
                    startActivity(intent);
                } else {
                    Log.d("SCAN", "No barcode captured, intent data is null");
                }
            } else {
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
