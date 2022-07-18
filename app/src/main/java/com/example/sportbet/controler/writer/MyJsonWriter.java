package com.example.sportbet.controler.writer;


import com.example.sportbet.InternalConstants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

//TODO: Tests
public class MyJsonWriter {

    public static void write(String jsonStr) {
        writeToSDFile(jsonStr);
    }

    private static void writeToSDFile(String inpStr) {
        ///storage/emulated/0/downloadTippFile.txt
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        //noinspection ResultOfMethodCallIgnored
        dir.mkdirs();
        File file = new File(dir, InternalConstants.BetFile);

        try {
            FileOutputStream f = new FileOutputStream(file, false);
            f.write(inpStr.getBytes());
            f.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
