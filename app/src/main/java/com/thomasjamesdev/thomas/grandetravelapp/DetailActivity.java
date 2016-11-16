package com.thomasjamesdev.thomas.grandetravelapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by Thomas on 14/11/2016.
 */

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);


        if (false) {
            Bundle arguments = new Bundle();
            arguments.putString(PackageDetailFragment.ARG_ITEM_ID,
                    getIntent().getStringExtra(PackageDetailFragment.ARG_ITEM_ID));
            PackageDetailFragment fragment = new PackageDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.package_detail, fragment)
                    .commit();
        } else {
            TextView title = (TextView) findViewById(R.id.package_detail_title);
            TextView location = (TextView) findViewById(R.id.package_detail_location);
            TextView description = (TextView)findViewById(R.id.package_detail_description);
            TextView price = (TextView)findViewById(R.id.package_detail_price);
            Intent intent = getIntent();


            title.setText(intent.getStringExtra("title"));
            location.setText(intent.getStringExtra("location"));
            description.setText(intent.getStringExtra("description"));
            price.setText(intent.getStringExtra("price"));

        }


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, ResultsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
