package com.jeffthefate;

import com.jeffthefate.utils.ImageUtil;
import junit.framework.TestCase;

import java.io.File;

public class SetlistScreenshotTest extends TestCase {

    private ImageUtil imageUtil;
    private SetlistScreenshot setlistScreenshot;

    public void setUp() throws Exception {
        super.setUp();
        imageUtil = com.jeffthefate.utils.ImageUtil.instance();
        setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "", 25, 120, 20, "target/" + getName());
    }

    public void testReallyLongSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In" +
            " Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ" +
            "\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nSet Break\n\n#27" +
            "\nShake Me Like A Monkey\nCrush\nSpaceman ->\nCorn " +
            "Bread\n#41\nProudest Monkey->\nSatellite\nYou and Me\nJTR\n" +
            "Sweet Up and Down\nDancing Nancies ->\nDrive In Drive Out\nAngel\n"
            + "Rooftop\nBelly Belly Nice\nJimi Thing\n\nEncore:\nSister5||\n" +
            "All Along The Watchtower\n\nNotes:\n* Carter and Dave\n+ Carter," +
            " Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All\n5|| Dave, " +
            "Carter, Rashawn and Tim\n-> indicates a segue into next song");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistReallyLong.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testLongSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie " +
            "In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ" +
            "\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nEncore:\n#27\n" +
            "Shake Me Like A Monkey\nCrush\nSpaceman ->\nCorn Bread\n#41\n" +
            "Proudest Monkey->\nSatellite\nYou and Me\nDancing Nancies ->\n" +
            "Drive In Drive Out\nBelly Belly Nice\nJimi Thing\n\nEncore:\n" +
            "Sister5||\nAll Along The Watchtower\n\nNotes:\n* Carter and Dave" +
            "\n+ Carter, Dave and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All\n" +
            "5|| Dave, Carter, Rashawn and Tim\n-> indicates a segue into " +
            "next song");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistLong.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShortestSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nShow begins at 7pm CDT");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShortest.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShorterSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In" +
            " Our Graves~\n\nNotes:\n* Carter and Dave\n+ Carter, Dave and " +
            "Tim\n~ Boyd, Carter, Dave and Tim");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShorter.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShortSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie " +
            "In Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo " +
            "Damn LuckyÄ\n\nNotes:\n* Carter and Dave\n+ Carter, Dave and Tim" +
            "\n~ Boyd, Carter, Dave and Tim\nÄ All");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShort.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testMediumSetlist() {
        setlistScreenshot.setText("May 17 2014\nDave Matthews Band\nGexa " +
            "Energy Pavilion\nDallas, TX\n\nRecently*\nGrace Is Gone+\nLie In" +
            " Our Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ\nSo " +
            "Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nEncore:\n#27\nShake " +
            "Me Like A Monkey\n\nNotes:\n* Carter and Dave\n+ Carter, Dave " +
            "and Tim\n~ Boyd, Carter, Dave and Tim\nÄ All");
        setlistScreenshot.createScreenshot();
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistMedium.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

}
