package com.aditya.adityagetweb;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DELL on 22/10/2017.
 */

public class GetHTMLSource extends AsyncTaskLoader<String> {
    private String my_url;

    public GetHTMLSource(Context context, String my_url) {
        super(context);
        this.my_url = my_url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        InputStream in;

        try {
            URL url = new URL(my_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(20000);
            connection.setRequestMethod("GET");
            connection.connect();

            in = connection.getInputStream();

            BufferedReader buff = new BufferedReader(new InputStreamReader(in));
            StringBuilder sb = new StringBuilder();
            String line = "";

            while ((line = buff.readLine()) != null) {
                sb.append(line + "\n");
            }

            buff.close();
            in.close();

            return sb.toString();


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }
}


