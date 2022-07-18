package com.example.sportbet.controler;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

//TODO: Tests
public class TeamIconService {

    //TODO: Background Task
    public Drawable getTeamIcon(String urlStream) {
        Bitmap x;

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(urlStream).openConnection();
            connection.connect();
            InputStream input = connection.getInputStream();
            x = BitmapFactory.decodeStream(input);
            if (x == null) {
                x = tryCreateDrawableWithHttps(urlStream);
            }
            return new BitmapDrawable(Resources.getSystem(), x);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static Bitmap tryCreateDrawableWithHttps(String urlStream) throws IOException {
        HttpURLConnection connection;
        InputStream input;
        Bitmap x;
        String urlHttpsStream = urlStream.replace("http", "https");
        connection = (HttpURLConnection) new URL(urlHttpsStream).openConnection();
        connection.connect();
        input = connection.getInputStream();
        x = BitmapFactory.decodeStream(input);
        return x;
    }

}
