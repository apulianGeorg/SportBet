package com.example.sportbet.controler;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

//TODO: Tests
public class TeamIconService {

    private final ExecutorService executor
            = Executors.newFixedThreadPool(20);

    public Future<Drawable> getTeamIcon(String urlString) {
        return (urlString.endsWith("svg")) ?
                getTeamIconSvg(urlString) : getTeamIconStandard(urlString);
    }

    private Future<Drawable> getTeamIconStandard(String urlString) {
        return executor.submit(() -> {
            try {
                var url = new URL(urlString);
                var connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                var input = connection.getInputStream();
                var x = BitmapFactory.decodeStream(input);
                //only Bitmap does not work because there are also .svg files
                return new BitmapDrawable(Resources.getSystem(), x);
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        });
    }

    private Future<Drawable> getTeamIconSvg(String urlString) {
        return executor.submit(() -> {
            try {
                var url = new URL(urlString);
                var connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                var input = connection.getInputStream();
                Drawable drawable = Drawable.createFromStream(input, "src");

                int width = drawable.getIntrinsicWidth();
                int height = drawable.getIntrinsicHeight();

                int scaledWidth = width;
                int scaledHeight = height;

                drawable.setBounds(0, 0, scaledWidth, scaledHeight);
                drawable.setVisible(true, true);

                return drawable;
            } catch (Exception e) {
                return null;
            }
        });
    }


}
