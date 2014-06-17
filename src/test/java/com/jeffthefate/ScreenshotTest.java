package com.jeffthefate;

import junit.framework.TestCase;
import org.junit.Assert;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Unit test for simple App.
 */
public class ScreenshotTest extends TestCase {

    public void testWillTextFit() {
        Screenshot screenshot = new Screenshot("", "", 180) {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Assert.assertTrue("One line doesn't fit in 800 pixels!",
                screenshot.willTextFit(800, g2d, 1));
        Assert.assertTrue("10 lines doesn't fit in 800 pixels!",
                screenshot.willTextFit(800, g2d, 10));
        Assert.assertFalse("100 lines fit in 800 pixels!",
                screenshot.willTextFit(800, g2d, 100));
        Assert.assertFalse("1000 lines fit in 800 pixels!",
                screenshot.willTextFit(800, g2d, 1000));
    }

    public void testAddCenteredStringToImage() {
        Screenshot screenshot = new Screenshot("", "", 180) {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Assert.assertEquals("Height of text drawn is wrong!", 16,
                screenshot.addCenteredStringToImage(0, 0, g2d, "TEST STRING"));
    }

    public void testAddStringToImage() {
        Screenshot screenshot = new Screenshot("", "", 180) {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Assert.assertEquals("Height of text drawn is wrong!", 16,
                screenshot.addStringToImage(0, 0, g2d, "TEST STRING"));
    }

    public void testCropImage() {
        Screenshot screenshot = new Screenshot("", "", 180) {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        BufferedImage croppedImage = screenshot.cropImage(bufferedImage, 0, 0,
                40, 80, 5);
        assertEquals("Cropped width is wrong!", croppedImage.getWidth(), 40);
        assertEquals("Cropped height is wrong!", croppedImage.getHeight(), 85);
    }
    /*
    public void testReallyLongSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nSet Break\n\n#27\nShake Me Like A Monkey\nCrush\nSpaceman ->\nCorn Bread\n#41\nProudest Monkey->\nSatellite\nYou and Me\nJTR\nSweet Up and Down\nDancing Nancies ->\nDrive In Drive Out\nAngel\nRooftop\nBelly Belly Nice\nJimi Thing\n\nEncore:\nSister5||\nAll Along The Watchtower\n\nNotes:\n\n* Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All\n5|| Dave, Carter, Rashawn and Tim\n-> indicates a segue into next song", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }

    public void testLongSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nEncore:\n#27\nShake Me Like A Monkey\nCrush\nSpaceman ->\nCorn Bread\n#41\nProudest Monkey->\nSatellite\nYou and Me\nDancing Nancies ->\nDrive In Drive Out\nBelly Belly Nice\nJimi Thing\n\nEncore:\nSister5||\nAll Along The Watchtower\n\nNotes:\n\n* Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All\n5|| Dave, Carter, Rashawn and Tim\n-> indicates a segue into next song", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }
    
    public void testShortestSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nShow begins at 7pm CDT", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }
    
    public void testShorterSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our Graves~\n\nNotes:\n\n* Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }
    
    public void testShortSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo Damn LuckyÄ\n\nNotes:\n\n* Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }
    
    public void testMediumSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
        		"D:\\setlist.jpg", "D:\\roboto.ttf", "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nEncore:\n#27\nShake Me Like A Monkey\n\nNotes:\n\n* Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All", 60, 180);
        System.out.println(setlistScreenshot.getOutputFilename());
    }
    
    public void testShortTopScores() {
    	HashMap<String, Integer> playerMap = new HashMap<String, Integer>();
    	playerMap.put("player1", 1000);
    	playerMap.put("player2", 900);
    	playerMap.put("player3", 800);
    	TriviaScreenshot triviaScreenshot = new TriviaScreenshot(
    			"D:\\setlist.jpg", "D:\\roboto.ttf", "Top Scores", playerMap,
    			60, 30, 10, 200);
    	System.out.println(triviaScreenshot.getOutputFilename());
    }
    
    public void testLongTopScores() {
    	HashMap<String, Integer> playerMap = new HashMap<String, Integer>();
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
    	TriviaScreenshot triviaScreenshot = new TriviaScreenshot(
    			"D:\\setlist.jpg", "D:\\roboto.ttf", "Top Scores", playerMap,
    			60, 30, 10, 200);
    	System.out.println(triviaScreenshot.getOutputFilename());
    }
    */
}
