package com.example.sportbet.controler.reader;

import android.os.AsyncTask;

import com.example.sportbet.controler.UrlConstants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MyHtmlReader extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String leagueShortCut = strings[0];
        String year = strings[1];
        return ReadHtmlPageAsString(leagueShortCut, year);
    }

    private String ReadHtmlPageAsString(String leagueShortCut, String year) {
        StringBuilder retStr = new StringBuilder();
        try {
            URL url = new URL(UrlConstants.UrlPrefix+leagueShortCut+year);
            InputStream input = url.openStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null) {
                retStr.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr.toString();
    }

    @Override
    protected void onPostExecute(String str) {
    }
}
