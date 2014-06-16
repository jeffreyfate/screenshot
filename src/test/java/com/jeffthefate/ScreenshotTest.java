package com.jeffthefate;

import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ScreenshotTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ScreenshotTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( ScreenshotTest.class );
    }
    
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
}
