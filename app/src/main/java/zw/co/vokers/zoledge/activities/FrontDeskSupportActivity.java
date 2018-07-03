package zw.co.vokers.zoledge.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;

public class FrontDeskSupportActivity extends AppCompatActivity {
    private TextView txtManual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.front_desk);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtManual = (TextView) findViewById(R.id.frontDeskText);

        txtManual.setText(Html.fromHtml(getString(R.string.frontdesk)));

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

