package com.example.sportbet.controler.reader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class HtmlClient {
    private final ExecutorService executor
            = Executors.newSingleThreadExecutor();

    public Future<String> ReadHtmlPageAsString(String urlString) {
        return executor.submit(() -> {
            StringBuilder retStr = new StringBuilder();
            try {
                URL url = new URL(urlString);
                URLConnection connection = url.openConnection();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    retStr.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return retStr.toString();
        });
    }
}
