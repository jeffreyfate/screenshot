package com.jeffthefate;

import com.jeffthefate.utils.ImageUtil;
import junit.framework.TestCase;

import java.io.File;

/**
 * Created by Jeff on 6/17/2014.
 */
public class SetlistScreenshotTest extends TestCase {

    private ImageUtil imageUtil;

    public void setUp() throws Exception {
        super.setUp();
        imageUtil = com.jeffthefate.utils.ImageUtil.instance();
    }

    public void testReallyLongSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our" +
                        " Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ" +
                        "\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\nSet" +
                        " Break\n\n#27\nShake Me Like A Monkey\nCrush\n" +
                        "Spaceman ->\nCorn Bread\n#41\nProudest Monkey->\n" +
                        "Satellite\nYou and Me\nJTR\nSweet Up and Down\n" +
                        "Dancing Nancies ->\nDrive In Drive Out\nAngel\n" +
                        "Rooftop\nBelly Belly Nice\nJimi Thing\n\nEncore:\n" +
                        "Sister5||\nAll Along The Watchtower\n\nNotes:\n\n* " +
                        "Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, " +
                        "Carter, Dave and Tim\nÄ All\n5|| Dave, Carter, " +
                        "Rashawn and Tim\n-> indicates a segue into next song",
                60, 180, "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistReallyLong.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testLongSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our " +
                        "Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into MeÄ" +
                        "\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\n" +
                        "Encore:\n#27\nShake Me Like A Monkey\nCrush\n" +
                        "Spaceman ->\nCorn Bread\n#41\nProudest Monkey->\n" +
                        "Satellite\nYou and Me\nDancing Nancies ->\nDrive In" +
                        " Drive Out\nBelly Belly Nice\nJimi Thing\n\n" +
                        "Encore:\nSister5||\nAll Along The Watchtower\n\n" +
                        "Notes:\n\n* Carter and Dave\n+ Carter, " +
                        "Dave and Tim\n~ Boyd, Carter, " +
                        "Dave and Tim\nÄ All\n5|| Dave, Carter, " +
                        "Rashawn and Tim\n-> indicates a segue into next song",
                60, 180, "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistLong.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShortestSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nShow begins at 7pm CDT", 60, 180,
                "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShortest.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShorterSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our " +
                        "Graves~\n\nNotes:\n\n* Carter and Dave\n+ Carter, " +
                        "Dave and Tim\n~ Boyd, Carter, Dave and Tim", 60, 180,
                "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShorter.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testShortSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our " +
                        "Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into " +
                        "MeÄ\nSo Damn LuckyÄ\n\nNotes:\n\n* Carter and Dave" +
                        "\n+ Carter, Dave and Tim\n~ Boyd, Carter, " +
                        "Dave and Tim\nÄ All", 60, 180,
                "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistShort.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

    public void testMediumSetlist() {
        SetlistScreenshot setlistScreenshot = new SetlistScreenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                "May 17 2014\nDave Matthews Band\nGexa Energy Pavilion\n" +
                        "Dallas, TX\n\nRecently*\nGrace Is Gone+\nLie In Our " +
                        "Graves~\nStolen Away On 55th & 3rdÄ\nCrash Into " +
                        "MeÄ\nSo Damn LuckyÄ\nSugar ManÄ\nAnts MarchingÄ\n\n" +
                        "Encore:\n#27\nShake Me Like A Monkey\n\nNotes:\n\n*" +
                        " Carter and Dave\n+ Carter, Dave and Tim\n~ Boyd, " +
                        "Carter, Dave and Tim\nÄ All", 60, 180,
                "target/" + getName());
        double percentDiff = imageUtil.compareImages(
                "src/test/resources/setlistMedium.jpg",
                setlistScreenshot.getOutputFilename());
        assertEquals("Images are not exact same!", 0.0, percentDiff);
    }

}
