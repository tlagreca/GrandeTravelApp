package com.thomasjamesdev.thomas.grandetravelapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static android.R.attr.fragment;

/**
 * Created by Thomas on 14/11/2016.
 */

public class PackageDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    String title, location, description, price;

    public PackageDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        title = getArguments().getString("title");
        location = getArguments().getString("location");
        description = getArguments().getString("description");
        price = "$" + getArguments().getInt("price") + ".00";


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.package_detail, container, false);

        // Show the dummy content as text in a TextView.
            ((TextView) rootView.findViewById(R.id.package_detail_title)).setText(title);
            ((TextView) rootView.findViewById(R.id.package_detail_location)).setText(location);
            ((TextView) rootView.findViewById(R.id.package_detail_description)).setText(description);
            ((TextView) rootView.findViewById(R.id.package_detail_price)).setText(price);



        return rootView;
    }

}
