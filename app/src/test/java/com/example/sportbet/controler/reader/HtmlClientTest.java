package com.example.sportbet.controler.reader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HtmlClientTest {


    @Test
    public void testRead() throws InterruptedException, ExecutionException {
        Future<String> future = new HtmlClient().ReadHtmlPageAsString("https://www.openligadb.de/api/getmatchdata/bl1/2022");

        while (!future.isDone()) {
            System.out.println("Reading...");
            Thread.sleep(300);
        }

        String result = future.get();
        assertEquals("[{\"MatchID\":", result.substring(0, 12));
    }

    @Test
    public void testMultipleTreads() throws InterruptedException, ExecutionException {
        HtmlClient htmlClient = new HtmlClient();
        Future<String> future3 = htmlClient.ReadHtmlPageAsString("https://www.openligadb.de/api/getmatchdata/bl1");
        Future<String> future1 = htmlClient.ReadHtmlPageAsString("https://www.openligadb.de/api/getmatchdata/bl1/2022");
        Future<String> future2 = htmlClient.ReadHtmlPageAsString("https://www.openligadb.de/api/getmatchdata/bl2/2022");

        assertNotEquals(future1, future2);
        assertNotEquals(future1, future3);
        assertNotEquals(future2, future3);

        while (!future1.isDone() || !future2.isDone()) {
            if (!future1.isDone()) {
                System.out.println("Reading1...");
            }
            if (!future2.isDone()) {
                System.out.println("Reading2...");
            }
            if (!future3.isDone()) {
                System.out.println("Reading3...");
            }
            Thread.sleep(100);
        }

        String result1 = future1.get();
        String result2 = future2.get();
        String result3 = future3.get();
        assertEquals("[{\"MatchID\":", result1.substring(0, 12));
        assertEquals("[{\"MatchID\":", result2.substring(0, 12));
        assertEquals("[{\"MatchID\":", result3.substring(0, 12));
    }

}