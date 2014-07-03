package com.jeffthefate;

import com.jeffthefate.utils.ImageUtil;
import junit.framework.TestCase;

import java.io.File;
import java.util.TreeMap;

public class TriviaScreenshotTest extends TestCase {

    private ImageUtil imageUtil;
    private TriviaScreenshot triviaScreenshot;

    public void setUp() throws Exception {
        super.setUp();
        imageUtil = ImageUtil.instance();
        triviaScreenshot = new TriviaScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "Top Scores", null, 60, 30, 10, 200, 100,
                "target/" + getName());
    }

    public void testShortTopScores() {
        TreeMap<String, Integer> playerMap = new TreeMap<>();
        playerMap.put("player1", 1000);
        playerMap.put("player2", 900);
        playerMap.put("player3", 800);
        triviaScreenshot.setSortedMap(playerMap);
        triviaScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/triviaShort.jpg",
                triviaScreenshot.getOutputFilename());
        assertTrue("Images are not exact same!", percentDiff <= 0.1);
    }

    public void testLongTopScores() {
        TreeMap<String, Integer> playerMap = new TreeMap<>();
        playerMap.put("player1", 1000);
        playerMap.put("player2", 900);
        playerMap.put("player3", 800);
        playerMap.put("player4", 700);
        playerMap.put("player5", 600);
        playerMap.put("player6", 500);
        playerMap.put("player7", 400);
        playerMap.put("player8", 300);
        playerMap.put("player9", 200);
        playerMap.put("player10", 100);
        triviaScreenshot.setSortedMap(playerMap);
        triviaScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/triviaLong.jpg",
                triviaScreenshot.getOutputFilename());
        assertTrue("Images are not exact same!", percentDiff <= 0.1);
    }

}
