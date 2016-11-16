package com.thomasjamesdev.thomas.grandetravelapp;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;

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
 * Created by Thomas on 15/11/2016.
 */

public class GetData extends AsyncTask<Void, Void, JSONObject > {

    final String OPEN_GRANDE_TRAVEL_API = "";
    public ArrayList<Package> packages;
    ResultsActivity.PackageAdapter adapter;

    public GetData(Context context, ArrayList<Package> packages, ResultsActivity.PackageAdapter adapter ) {
        super();
        this.adapter = adapter;
        this.packages = packages;
        JsonDummyData(context);
    }

    public void JsonDummyData(Context context){




        try{
//            URL url = new URL(OPEN_GRANDE_TRAVEL_API);
//
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.connect();
//
//            InputStream stream = urlConnection.getInputStream();

//
//
            InputStream stream = context.getResources().openRawResource(R.raw.json_dummy_data);

            StringBuilder builder = new StringBuilder();



            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            String line;
            while( (line = reader.readLine()) != null ){
                builder.append(line);
            }

            String jsonString = builder.toString();
            JSONObject jsonObject = new JSONObject(jsonString);

            extractPackages(jsonObject);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
    private void extractPackages(JSONObject jsonObject) {

        try{
            if(jsonObject != null){

                JSONObject jObjectResult = jsonObject.getJSONObject("packages");

                JSONArray jsonPackages = jObjectResult.getJSONArray("package");

                String title;
                String location;
                String description;
                int price;

                ArrayList<Package> temp_packages = new ArrayList<>();

                for(int i = 0; i < jsonPackages.length(); i++){

                    JSONObject jsonPackage = jsonPackages.getJSONObject(i);


                    title = jsonPackage.getString("PackageTitle");
                    location = jsonPackage.getString("PackageLocation");
                    description = jsonPackage.getString("PackageDescription");
                    price = jsonPackage.getInt("PackagePrice");


                    temp_packages.add(new Package(title, location, description, price));


                }


                packages.clear();
                for(Package p : temp_packages){
                    packages.add(p);
                }
                adapter.notifyDataSetChanged();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        return null;
    }
}
