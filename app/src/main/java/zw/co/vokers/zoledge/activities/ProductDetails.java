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

import zw.co.vokers.zoledge.db.DbBackend;
import zw.co.vokers.zoledge.R;

/**
 * Created by VinceGee on 1/11/2018.
 */

public class ProductDetails extends AppCompatActivity {
    private TextView productTitle;
    private RelativeLayout relativeLayout;
    private static final String TAG = ProductDetails.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientinfo);

        DbBackend backend = new DbBackend(this);

        ImageView logo = (ImageView) findViewById(R.id.logoView);
        TextView prodSummary = (TextView) findViewById(R.id.actSummary);
        TextView prodInfo = (TextView) findViewById(R.id.actProductInfo);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String prodName = bundle.getString("pname");

        HashMap<String, String> patientDetails = backend.getProductDetails(prodName);
        String dbProductName = patientDetails.get("pname");
        String dbSummary = patientDetails.get("psummary");
        String dbInfo = patientDetails.get("pinfo");
        String dbCategory = patientDetails.get("pcategory");

        Toolbar toolbar = findViewById(R.id.producttoolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_24dp);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(dbProductName);
        toolbar.setTitleTextColor(getResources().getColor(R.color.black));

        productTitle = (TextView) findViewById(R.id.product_name);
        //relativeLayout =(RelativeLayout) findViewById(R.id.call_now);

        /*relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbCell==null){
                    //new NoInternetDialog.Builder(MovieInfo.this).build();
                    Toast.makeText(getApplicationContext(),"Product cell number not provided.", Toast.LENGTH_LONG).show();
                } else {
                    if (PermissionsChecker.requestPermission(ProductDetails.this, "android.permission.CALL_PHONE", 0, 1)){

                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + dbCell));
                        startActivity(callIntent);
                    } else{
                        Toast.makeText(ProductDetails.this,"NO PERMISSION!!!", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });*/

        productTitle.setText(dbProductName);
        prodSummary.setText(dbSummary);
        prodInfo.setText(dbInfo);
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
