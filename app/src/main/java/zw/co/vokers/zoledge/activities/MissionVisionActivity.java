package zw.co.vokers.zoledge.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.TextView;

import zw.co.vokers.zoledge.R;
import zw.co.vokers.zoledge.utils.VinceTextView;

public class MissionVisionActivity extends AppCompatActivity {

    public static final String EXTRA_NAME = "cheese_name";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comp_info);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarComp);
        setSupportActionBar(toolbar);

        Intent intent = getIntent();
        final String cheeseName = intent.getStringExtra(EXTRA_NAME);

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.ctl);
        collapsingToolbar.setCollapsedTitleTextColor(Color.WHITE);
        collapsingToolbar.setExpandedTitleTextColor(ColorStateList.valueOf(Color.WHITE));

        collapsingToolbar.setTitle(cheeseName);

        //VinceTextView cash = findViewById(R.id.acthistory);

       // cash.setText(Html.fromHtml(getString(R.string.history)));
        //loadBackdrop();
    }

    /*private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}