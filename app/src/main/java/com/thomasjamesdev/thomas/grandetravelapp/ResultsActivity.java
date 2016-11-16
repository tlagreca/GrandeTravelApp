package com.thomasjamesdev.thomas.grandetravelapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by Thomas on 14/11/2016.
 */

public class ResultsActivity extends AppCompatActivity {



    boolean mTwoPane;
    public ArrayList<Package> packages;
    PackageAdapter packageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        packages = new ArrayList<>();

        int orientation = getResources().getConfiguration().orientation;

        mTwoPane = orientation == Configuration.ORIENTATION_LANDSCAPE;

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.package_list);
        packageAdapter = new PackageAdapter(this, packages);
        recyclerView.setAdapter(packageAdapter);

        new GetData(this, packages, packageAdapter).execute();

    }





    public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageViewHolder>{

        private ArrayList<Package> packages;
        private final Activity activity;

        public PackageAdapter(Activity activity, ArrayList<Package> packages) {

            this.packages = packages;
            this.activity = activity;
        }

        @Override
        public void onBindViewHolder(final PackageViewHolder holder, int position) {


            holder.p = packages.get(position);

            holder.textViewTitle.setText(packages.get(position).getPackageTitle());
            holder.textViewLocation.setText(packages.get(position).getPackageLocation());

            holder.view.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){

                    if (mTwoPane) {
                        Bundle arguments = new Bundle();
                        arguments.putString("title", holder.p.getPackageTitle());
                        arguments.putString("location", holder.p.getPackageLocation());
                        arguments.putString("description", holder.p.getPackageDescription());

                        String price = "" + holder.p.getPackagePrice();

                        arguments.putString("price", price);

                        PackageDetailFragment fragment = new PackageDetailFragment();
                        fragment.setArguments(arguments);

                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.package_detail, fragment)
                                .commit();
                    } else {
                        Context context = v.getContext();
                        Intent intent = new Intent(ResultsActivity.this, DetailActivity.class);
                        intent.putExtra("title", holder.p.getPackageTitle());
                        intent.putExtra("location", holder.p.getPackageLocation());
                        intent.putExtra("description", holder.p.getPackageDescription());

                        String price = "$" + holder.p.getPackagePrice();
                        intent.putExtra("price", price);

                        context.startActivity(intent);
                    }

                }
            });

        }

        @Override
        public int getItemCount() {
            return packages.size();
        }


        @Override
        public PackageViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_list_content, parent, false);
            return new PackageViewHolder(itemView);

        }

        class PackageViewHolder extends  RecyclerView.ViewHolder{
            View view;
            TextView textViewTitle;
            TextView textViewLocation;
            Package p;

            PackageViewHolder(View v){
                super(v);

                view = v;
                textViewTitle = (TextView)v.findViewById(R.id.tv_title);
                textViewLocation = (TextView)v.findViewById(R.id.tv_location);



            }
        }
    }



}

