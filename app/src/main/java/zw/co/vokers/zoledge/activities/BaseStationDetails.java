package zw.co.vokers.zoledge.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.db.DbBackend;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class BaseStationDetails extends AppCompatActivity {
    private VinceTextView tbsName, tbsId, tsiteNum, tsiteIden, tsiteNameGet, tcellName, tcellId,
            tbsIdGet,tsectorId,tdistrict,tareaName,tlongitude,tlatitude,tcoverageType,tcenterFrequency,
    tbandwidth,toperatorId;
    private RelativeLayout relativeLayout;
    private static final String TAG = ProductDetails.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bsinfo);

        DbBackend backend = new DbBackend(this);

        ImageView logo = (ImageView) findViewById(R.id.logoView);
        TextView prodSummary = (TextView) findViewById(R.id.actSummary);
        TextView prodInfo = (TextView) findViewById(R.id.actProductInfo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String siteName = bundle.getString("siteName");
        String bsid = bundle.getString("bsid");

        HashMap<String, String> patientDetails = backend.getBsDetails(bsid);
        String siteNum = patientDetails.get("SiteNo");
        String siteIden = patientDetails.get("Site_ID");
        String siteNameGet = patientDetails.get("sitename");
        String cellName = patientDetails.get("CellName");
        String cellId = patientDetails.get("CellID");
        String bsIdGet = patientDetails.get("BSID");
        String sectorId = patientDetails.get("SectorID");
        String district = patientDetails.get("District");
        String areaName = patientDetails.get("AreaName");
        String longitude = patientDetails.get("Longitude");
        String latitude = patientDetails.get("Latitude");
        String coverageType = patientDetails.get("CoverageType");
        String centerFrequency = patientDetails.get("CenterFrequency");
        String bandwidth = patientDetails.get("Bandwidth");
        String operatorId = patientDetails.get("OperatorID");

        Toolbar toolbar = findViewById(R.id.bstoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(siteName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        tbsName = (VinceTextView) findViewById(R.id.bs_name);
        tbsId = (VinceTextView) findViewById(R.id.actBsid);
        tsiteNum = (VinceTextView) findViewById(R.id.actSiteNumber);
        tsiteIden = (VinceTextView) findViewById(R.id.actSiteId);
        //tsiteNameGet = (VinceTextView) findViewById(R.id.bs_name);
        tcellName = (VinceTextView) findViewById(R.id.actCellName);
        tcellId = (VinceTextView) findViewById(R.id.actCellId);
        //tbsIdGet = (VinceTextView) findViewById(R.id.actBsid);
        tsectorId = (VinceTextView) findViewById(R.id.actSectorId);
        tdistrict = (VinceTextView) findViewById(R.id.actDistrict);
        tareaName = (VinceTextView) findViewById(R.id.actAreaName);
        tlongitude = (VinceTextView) findViewById(R.id.actLongitude);
        tlatitude = (VinceTextView) findViewById(R.id.actLatitude);
        tcoverageType = (VinceTextView) findViewById(R.id.actCoverageType);
        tcenterFrequency = (VinceTextView) findViewById(R.id.actCenterFreq);
        tbandwidth = (VinceTextView) findViewById(R.id.actBandwidth);
        toperatorId = (VinceTextView) findViewById(R.id.actOperator);

        tbsName.setText(siteName);
        tbsId.setText(bsid);
        tsiteNum.setText(siteNum);
        tsiteIden.setText(siteIden);
        tcellName.setText(cellName);
        tcellId.setText(cellId);
        tsectorId.setText(sectorId);
        tdistrict.setText(district);
        tareaName.setText(areaName);
        tlongitude.setText(longitude);
        tlatitude.setText(latitude);
        tcoverageType.setText(coverageType);
        tcenterFrequency.setText(centerFrequency);
        tbandwidth.setText(bandwidth);
        toperatorId.setText(operatorId);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        super.finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}

