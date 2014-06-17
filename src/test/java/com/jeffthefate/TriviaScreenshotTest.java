package com.jeffthefate;

import junit.framework.TestCase;

import java.io.File;
import java.util.TreeMap;

/**
 * Created by Jeff on 6/17/2014.
 */
public class TriviaScreenshotTest extends TestCase {

    public void testCreateScreenshot() {
        TreeMap<String, Integer> sortedMap = new TreeMap<String, Integer>();
        sortedMap.put("test1", 5);
        sortedMap.put("test2", 3);
        sortedMap.put("test3", 7);
        TriviaScreenshot triviaScreenshot = new TriviaScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "Test", sortedMap, 40, 12, 10, 200);
        System.out.println(triviaScreenshot.getOutputFilename());
    }

}
