package com.jeffthefate;

import junit.framework.TestCase;
import org.junit.Assert;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

/**
 * Unit test for simple App.
 */
public class ScreenshotTest extends TestCase {

    private com.jeffthefate.utils.ImageUtil imageUtil;

    public void setUp() throws Exception {
        super.setUp();
        imageUtil = com.jeffthefate.utils.ImageUtil.instance();
    }

    public void testWillTextFit() {
        Screenshot screenshot = new Screenshot("", "", 180, 60,
                "target/testWillTextFit") {};
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
        Screenshot screenshot = new Screenshot("", "", 180, 60,
                "target/testAddCenteredStringToImage") {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Assert.assertEquals("Height of text drawn is wrong!", 16,
                screenshot.addCenteredStringToImage(0, 0, g2d, "TEST STRING"));
    }

    public void testAddStringToImage() {
        Screenshot screenshot = new Screenshot("", "", 180, 60,
                "target/testAddStringToImage") {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = bufferedImage.createGraphics();
        Assert.assertEquals("Height of text drawn is wrong!", 16,
                screenshot.addStringToImage(0, 0, g2d, "TEST STRING"));
    }

    public void testCropImage() {
        Screenshot screenshot = new Screenshot("", "", 180, 60,
                "target/testCropImage") {};
        BufferedImage bufferedImage = new BufferedImage(400, 800,
                BufferedImage.TYPE_INT_RGB);
        BufferedImage croppedImage = screenshot.cropImage(bufferedImage, 0, 0,
                40, 80, 5);
        assertEquals("Cropped width is wrong!", croppedImage.getWidth(), 40);
        assertEquals("Cropped height is wrong!", croppedImage.getHeight(), 85);
    }

    public void testAddTimestamp() {
        Screenshot screenshot = new Screenshot(
                new File("src/test/resources/setlist.jpg").getAbsolutePath(),
                new File("src/test/resources/roboto.ttf").getAbsolutePath(),
                180, 60, "target/testAddTimeStamp") {};
        BufferedImage bufferedImage = screenshot.setupImage();
        screenshot.setupGraphics(bufferedImage);
        screenshot.setupFontMetrics(20, bufferedImage.getHeight(), 1);
        int currentHeight = screenshot.getTopOffset() +
                screenshot.addStringToImage(screenshot.getTopOffset(), 100,
                        screenshot.getGraphics(), "TEST STRING");
        screenshot.addTimestamp(20, currentHeight, bufferedImage.getWidth());
        screenshot.setOutputFilename(screenshot.tearDown(bufferedImage,
                bufferedImage.getWidth(), currentHeight, true));
        assertEquals("Timestamp images don't match!", 0.0,
                imageUtil.compareImages("src/test/resources/addTimeStamp.jpg",
                        screenshot.getOutputFilename()));
    }

}
