package com.example.sportbet.controler.reader;

import com.example.sportbet.InternalConstants;

import java.io.File;
import java.io.IOException;

//TODO: Tests
public class MyFileReader {

    public static String readFile() {
        File root = android.os.Environment.getExternalStorageDirectory();
        File dir = new File(root.getAbsolutePath() + "/download");
        File file = new File(dir, InternalConstants.BetFile);
        try {
            java.io.FileReader fileReader = new java.io.FileReader(file);
            char[] chars = new char[(int) file.length()];
            //noinspection ResultOfMethodCallIgnored
            fileReader.read(chars);
            return new String(chars);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return InternalConstants.EmptyStr;
    }
}
