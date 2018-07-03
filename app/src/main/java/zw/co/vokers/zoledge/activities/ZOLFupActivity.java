package zw.co.vokers.zoledge.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;

public class ZOLFupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fup);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView cash = findViewById(R.id.casshhh);

        cash.setText(Html.fromHtml(getString(R.string.fup_text)));
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
